package handy.blackjack.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Card.VALUE;
import handy.cards.Shoe;
import handy.common21.sim.HandResult;
import handy.common21.sim.Player;
import handy.common21.sim.RoundOutcome;
import handy.common21.sim.Rules;
import handy.common21.sim.sidebet.InsuranceDescription;
import handy.common21.sim.sidebet.SideBetDescription;

public class Round extends handy.common21.sim.Round {

	public boolean workBlackjackLogic(Player player, handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand, int playerBet) {
		return vanillaBlackjackLogic(player, playerHand, dealerHand, playerBet);
	}

	public int determineOutcome(OUTCOME outcome, HandResult result, handy.common21.sim.Hand dealerHand) {
		if (result.hasSurrendered) {
			return -((int) result.bet / 2);
		} else if (outcome == OUTCOME.DEALER_WIN) {
			return -result.bet;
		} else if (outcome == OUTCOME.PLAYER_WIN || outcome == OUTCOME.PLAYER_BLACKJACK) {
			return result.bet;
		} else {
			return 0;// Push
		}
	}

	public void setupSideBets(Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand, int playerBet, Player player) {
		for (SideBetDescription sbs : rules.getSideBets()) {
			if ((sbs instanceof InsuranceDescription && dealerHand.firstCard().value == VALUE.ACE)
					|| !(sbs instanceof InsuranceDescription)) {
				int sideBet = player.getSideBet(sbs, shoe, playerHand, dealerHand.firstCard(), playerBet);
				sideBetsMap.put(sbs, sideBet);
			}
		}
	}

	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		return getVanillaBlackjackOutcome(playerHand, dealerHand);
	}

	public List<HandResult> playHand(Player player, Rules rules, handy.common21.sim.Hand hand, Shoe shoe, Card dealerCard, int bet)
			throws Exception {

		List<HandResult> hr = new ArrayList<HandResult>();
		playHandWorker(player, rules, (Hand) hand, shoe, dealerCard, hr, bet);
		return hr;
	}

	public void playHandWorker(Player player, Rules rules, Hand hand, Shoe shoe, Card dealerCard, List<HandResult> hr,
			int bet) throws Exception {
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
		} else if (nextMove == Rules.MOVE.DOUBLESPLIT) {
			for (Hand h : hand.split()) {
				hand.doubleDown();
				Card newCard = shoe.poll();
				player.updateStrategyCard(newCard);
				h.hit(newCard);
				hr.add(new HandResult(hand, bet * 2));
			}
		} else if (nextMove == Rules.MOVE.SPLITACE) {
			for (Hand h : hand.split()) {
				Card newCard = shoe.poll();
				player.updateStrategyCard(newCard);
				h.hit(newCard);
				hr.add(new HandResult(h, bet));
			}
		} else {
			hand.doubleDown();
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);
			hr.add(new HandResult(hand, bet * 2));
		}
	}

	public RoundOutcome play(Shoe shoe, Rules rules, Player player, OUTCOME previousOutcome) throws Exception {
		RoundOutcome outcome = new RoundOutcome(OUTCOME.PUSH, false);
		try {
			// TODO: Find better way of dealing with empty shoes
			outcome = playRoundMaster(shoe, rules, player, previousOutcome);
		} catch (IndexOutOfBoundsException e) {

		}
		return outcome;
	}

	public handy.common21.sim.Hand getHand(Card card1, Card card2) {
		return new Hand(card1, card2);
	}

	@Override
	public int playerBetMod(Player player, Shoe shoe, Card dealerUpCard, handy.common21.sim.Hand playerHand,
			int playerBet) {
		//No op
		return playerBet;
	}

}
