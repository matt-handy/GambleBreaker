package handy.freebet.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.Player;
import handy.common21.sim.RoundOutcome;

public class Round extends handy.common21.sim.Round {
	
	public static void playHandWorker(Player player, Rules rules, Hand hand, Shoe shoe, Card dealerCard,
			List<handy.common21.sim.HandResult> hr, int bet) throws Exception {
		Rules.MOVE nextMove = player.determineMove(rules, hand, dealerCard, shoe);
		if (nextMove == Rules.MOVE.STAY) {
			if(hand.isFree()){
				hr.add(new HandResult(hand, bet, 0));
			}else{
				hr.add(new HandResult(hand, bet, bet));
			}
		} else if (nextMove == Rules.MOVE.HIT) {
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			playHandWorker(player, rules, hand, shoe, dealerCard, hr, bet);
		} else if (nextMove == Rules.MOVE.SPLIT) {
			for (Hand h : hand.split()) {
				Card newCard = shoe.poll();
				player.updateStrategyCard(newCard);
				h.hit(newCard);
				playHandWorker(player, rules, h, shoe, dealerCard, hr, bet);
			}
		} else {//Double, surrender and doublesplit not allowed
			int originalValue = hand.getValue();
			hand.doubleDown();
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			
			int baseBet = bet;
			if(hand.isFree()){
				baseBet = 0;
			}
			
			if(originalValue >= 9 && originalValue <= 11){
				hr.add(new HandResult(hand, bet * 2, baseBet));
			}else{
				hr.add(new HandResult(hand, bet * 2, baseBet * 2));
			}
			
		}
	}


	public RoundOutcome play(Shoe shoe, handy.common21.sim.Rules rules, Player player, OUTCOME previousOutcome)
			throws Exception {
		if(!(rules instanceof Rules)){
			throw new Exception("FreeBet requires DoubleAttack Rules");
		}
		return playRoundMaster(shoe, (Rules) rules, player, previousOutcome);
	}


	@Override
	public boolean workBlackjackLogic(Player player, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet) {
		return vanillaBlackjackLogic(player, playerHand, dealerHand, playerBet);
	}


	@Override
	public int determineOutcome(OUTCOME outcome, handy.common21.sim.HandResult result, handy.common21.sim.Hand dealerHand) {
		if (outcome == OUTCOME.DEALER_WIN) {
			return -((HandResult)result).loseBet;
		} else if (outcome == OUTCOME.PLAYER_WIN || outcome == OUTCOME.PLAYER_BLACKJACK) {
			return result.bet;
		} else {
			return 0;
		}
	}


	public void setupSideBets(handy.common21.sim.Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet, Player player) {
		// No op
		
	}


	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		if (playerHand.isBust()) {
			return OUTCOME.DEALER_WIN;
		} else if (dealerHand.isNatural() && playerHand.isNatural()) {
			return OUTCOME.PUSH;
		} else if (dealerHand.isNatural()) {
			return OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			return OUTCOME.PLAYER_BLACKJACK;
		} else if(!playerHand.isNatural() && dealerHand.getValue() == 22){
			return OUTCOME.PUSH;
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


	public List<handy.common21.sim.HandResult> playHand(Player player, handy.common21.sim.Rules rules,
			handy.common21.sim.Hand hand, Shoe shoe, Card dealerCard, int bet) throws Exception {
		List<handy.common21.sim.HandResult> hr = new ArrayList<handy.common21.sim.HandResult>();
		playHandWorker(player, (Rules)rules, (Hand) hand, shoe, dealerCard, hr, bet);
		return hr;
	}


	public handy.common21.sim.Hand getHand(Card card1, Card card2) {
		return new Hand(card1, card2);
	}

	public int playerBetMod(Player player, Shoe shoe, Card dealerUpCard, handy.common21.sim.Hand playerHand,
			int playerBet) {
		// No op
		return playerBet;
	}
	
	
}
