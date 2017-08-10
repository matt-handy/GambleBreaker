package handy.bjswitch.sim;

import java.util.ArrayList;
import java.util.List;

import handy.bjswitch.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.HandResult;
import handy.common21.sim.RoundOutcome;
import handy.common21.sim.Rules;

public class Round extends handy.common21.sim.Round {

	public RoundOutcome playRound(Shoe shoe, Rules rules, Player player, OUTCOME previousOutcome) {
		int playerBetOne = player.getBet(shoe, previousOutcome);
		int playerBetTwo = playerBetOne;
		
		Hand dealerHand = new Hand(shoe.poll(), shoe.poll());
		Hand playerHandOne = new Hand(shoe.poll(), shoe.poll());
		Hand playerHandTwo = new Hand(shoe.poll(), shoe.poll());

		player.updateStrategyPlayerHand(playerHandOne);
		player.updateStrategyPlayerHand(playerHandTwo);
		player.updateStrategyCard(dealerHand.firstCard());

		if(dealerHand.isNatural()){
			if(!playerHandOne.isNatural()){
				player.dollars -= playerBetOne;
			}
			if(!playerHandTwo.isNatural()){
				player.dollars -= playerBetTwo;
			}
			return new RoundOutcome(OUTCOME.DEALER_WIN, player.walkAway(shoe));
		}
		
		if(Player.shouldSwitch(playerHandOne, playerHandTwo, dealerHand.firstCard())){
			if(Rules.DEBUG){
				System.out.println("Switching!");
				System.out.println("Player Hand 1:");
				for (Card card : playerHandOne.cards) {
					System.out.println("Card: " + card.suite + " " + card.value);
				}
				System.out.println("Player Hand 2:");
				for (Card card : playerHandTwo.cards) {
					System.out.println("Card: " + card.suite + " " + card.value);
				}
			}
			Hand.switchHands(playerHandOne, playerHandTwo);
			if(Rules.DEBUG){
				System.out.println("Switched!");
				System.out.println("Player Hand 1:");
				for (Card card : playerHandOne.cards) {
					System.out.println("Card: " + card.suite + " " + card.value);
				}
				System.out.println("Player Hand 2:");
				for (Card card : playerHandTwo.cards) {
					System.out.println("Card: " + card.suite + " " + card.value);
				}
			}
		}
		
		List<HandResult> hr = new ArrayList<HandResult>();
		
		try {
			List<HandResult> hr1 = playHand(player, rules, playerHandOne, shoe, dealerHand.firstCard(), playerBetOne);
			hr.addAll(hr1);
			List<HandResult> hr2 = playHand(player, rules, playerHandTwo, shoe, dealerHand.firstCard(), playerBetTwo);
			hr.addAll(hr2);
		} catch (Exception e) {
			e.printStackTrace();
			// Default to push if some exceptional state occurs
			System.out.println("Error occured in hand play");
			e.printStackTrace();
			return new RoundOutcome(OUTCOME.PUSH, player.walkAway(shoe));
		}
				
		resolveHands(hr, player, dealerHand, shoe, rules);
		
		// Why do we only return the last hand outcome, and not aggregate? Split
		// hands happen
		// rarely, and its hard to establish what the "normal" baseline response
		// is. Just
		// assume the player is emotionally responding to the last hand played.
		return new RoundOutcome(lastHandOutcome, player.walkAway(shoe));
	}

	public static void playHandWorker(Player player, Rules rules, Hand hand, Shoe shoe, Card dealerCard,
			List<HandResult> hr, int bet) throws Exception {
		Rules.MOVE nextMove = player.determineMove(rules, hand, dealerCard, shoe);
		if (nextMove == Rules.MOVE.STAY) {
			hr.add(new HandResult(hand, bet));
		} else if (nextMove == Rules.MOVE.HIT) {
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			playHandWorker(player, rules, hand, shoe, dealerCard, hr, bet);
		} else if (nextMove == Rules.MOVE.SURRENDER) {
			HandResult result = new HandResult(hand, bet);
			result.hasSurrendered = true;
			hr.add(result);
		} else if (nextMove == Rules.MOVE.SPLIT) {
			for (Hand h : hand.splitSwitchHand()) {
				Card newCard = shoe.poll();
				player.updateStrategyCard(newCard);
				h.hit(newCard);
				playHandWorker(player, rules, h, shoe, dealerCard, hr, bet);
			}
		} else {
			if(nextMove != Rules.MOVE.DOUBLE){
				System.out.println("ERROR - non-DOUBLE left over");
			}
			hand.doubleDown();
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			hr.add(new HandResult(hand, bet * 2));
		}
	}

	public RoundOutcome play(Shoe shoe, Rules rules, handy.common21.sim.Player player, OUTCOME previousOutcome)
			throws Exception {
		if(!(player instanceof Player)){
			throw new Exception("Requires BJ Switch Player for BJ Switch Round");
		}
		return playRound(shoe, rules, (Player)player, previousOutcome);
	}

	@Override
	public boolean workBlackjackLogic(handy.common21.sim.Player player, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet) {
		// TODO Auto-generated method stub
		return false;
	}

	public int determineOutcome(OUTCOME outcome, HandResult result, handy.common21.sim.Hand dealerHand) {
		return vanillaBlackjackWinLoss(outcome, result, dealerHand);
	}

	public void setupSideBets(Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet, handy.common21.sim.Player player) {
		//No op
	}

	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		if (playerHand.isBust()) {
			return OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			return OUTCOME.PLAYER_BLACKJACK;
		} else if(dealerHand.getValue() == 22){//Already checked for player natural
			return OUTCOME.PUSH;
		} else if (dealerHand.isBust()) {
			//Already checked for 22
			return OUTCOME.PLAYER_WIN;
		} else if (dealerHand.getValue() > playerHand.getValue()) {
			return OUTCOME.DEALER_WIN;
		} else if (dealerHand.getValue() < playerHand.getValue()) {
			return OUTCOME.PLAYER_WIN;
		} else if (dealerHand.getValue() == playerHand.getValue()) {
			return OUTCOME.PUSH;
		} else {
			System.out.println("This should never happen");
			return OUTCOME.DEALER_WIN;
		}
	}

	public List<HandResult> playHand(handy.common21.sim.Player player, Rules rules, handy.common21.sim.Hand hand,
			Shoe shoe, Card dealerCard, int bet) throws Exception {
		List<HandResult> hr = new ArrayList<HandResult>();
		playHandWorker((Player) player, rules, (Hand) hand, shoe, dealerCard, hr, bet);
		return hr;
	}

	public handy.common21.sim.Hand getHand(Card card1, Card card2) {
		return new Hand(card1, card2);
	}

	public int playerBetMod(handy.common21.sim.Player player, Shoe shoe, Card dealerUpCard,
			handy.common21.sim.Hand playerHand, int playerBet) {
		// No op
		return playerBet;
	}
}
