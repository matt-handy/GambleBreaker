package handy.superfun21.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Shoe;
import handy.common21.sim.RoundOutcome;
import handy.superfun21.sim.Rules;

public class Round extends handy.common21.sim.Round {

	public static void playHandWorker(Player player, Rules rules, Hand hand, Shoe shoe, Card dealerCard,
			List<handy.common21.sim.HandResult> hr, int bet) throws Exception {
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
			for (Hand h : hand.split()) {
				Card newCard = shoe.poll();
				player.updateStrategyCard(newCard);
				h.hit(newCard);
				playHandWorker(player, rules, h, shoe, dealerCard, hr, bet);
			}
		} else { //Double down
			hand.doubleDown();
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			
			HandResult result = new HandResult(hand, bet * 2);
			
			if(player.forfeit(hand, dealerCard, shoe)){
				result.hasForfeited = true;
			}
			
			hr.add(result);
		}
	}

	public RoundOutcome play(Shoe shoe, handy.common21.sim.Rules rules, handy.common21.sim.Player player,
			OUTCOME previousOutcome) throws Exception {
		if(!(player instanceof Player)){
			throw new Exception("SuperFun21 requires SuperFun21 Player");
		}
		if(!(rules instanceof Rules)){
			throw new Exception("SuperFun21 requires SuperFun21 Rules");
		}
		return playRoundMaster(shoe, (Rules) rules, (Player) player, previousOutcome);
	}

	public boolean workBlackjackLogic(handy.common21.sim.Player player, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet) {
		// Need blackjack check here
				if (playerHand.isNatural()) {
					if(playerHand.cards.get(0).suite == SUITE.DIAMOND &&
							playerHand.cards.get(1).suite == SUITE.DIAMOND){
						player.dollars += 2 * playerBet;
					}else{
						player.dollars += playerBet;
					}
					lastHandOutcome = OUTCOME.PLAYER_BLACKJACK;
				} else if (dealerHand.isNatural()) {
					player.dollars -= playerBet;
					lastHandOutcome = OUTCOME.DEALER_WIN;
				} else {
					return true;
				}
				return false;
	}

	public int determineOutcome(OUTCOME outcome, handy.common21.sim.HandResult result, handy.common21.sim.Hand dealerHand) {
		if (result.hasSurrendered || ((HandResult)result).hasForfeited) {
			return -((int) result.bet / 2);
		} else if (outcome == OUTCOME.DEALER_WIN) {
			return -result.bet;
		} else if (outcome == OUTCOME.PLAYER_WIN || outcome == OUTCOME.PLAYER_BLACKJACK) {
			if(result.hand.cards.size() == 5 && result.hand.getValue() == 21 &&
					!result.hand.hasDoubled()){
				return result.bet * 2;
			}else{
				return result.bet;
			}
		} else {
			return 0;
		}
	}

	public void setupSideBets(handy.common21.sim.Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet, handy.common21.sim.Player player) {
		//No-op
	}

	public void determineSideBetOutcome(handy.common21.sim.Player player, handy.common21.sim.Hand dealerHand,
			handy.common21.sim.Hand playerHand) {
		//No-op
	}

	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		if (playerHand.isBust()) {
			return OUTCOME.DEALER_WIN;
		} else if(playerHand.cards.size() == 6 && playerHand.getValue() <= 20 &&
				!playerHand.hasDoubled()){
			return OUTCOME.PLAYER_WIN;
		} else if(playerHand.cards.size() == 5 && playerHand.getValue() == 21 &&
				!playerHand.hasDoubled()){
			return OUTCOME.PLAYER_WIN;
		} else if (dealerHand.isNatural()) {
			return OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			return OUTCOME.PLAYER_BLACKJACK;
		} else if (dealerHand.isBust()) {
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

	public List<handy.common21.sim.HandResult> playHand(handy.common21.sim.Player player,
			handy.common21.sim.Rules rules, handy.common21.sim.Hand hand, Shoe shoe, Card dealerCard, int bet)
			throws Exception {
		List<handy.common21.sim.HandResult> hr = new ArrayList<handy.common21.sim.HandResult>();
		playHandWorker((Player) player, (Rules) rules, (Hand) hand, shoe, dealerCard, hr, bet);
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
