package handy.doubleattack.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Card.VALUE;
import handy.cards.Shoe;
import handy.cards.SpanishShoe;
import handy.common21.sim.RoundOutcome;
import handy.doubleattack.sim.sidebet.InsuranceDescription;
import handy.spanish21.sim.Hand;
import handy.spanish21.sim.HandResult;
import handy.spanish21.sim.Rules;

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
				if (h.firstCard().getBlackjackValue() == 11) {
					HandResult result = new HandResult(h, bet);
					hr.add(result);
				} else {
					playHandWorker(player, rules, h, shoe, dealerCard, hr, bet);
				}
			}
		} else { // Double
			hand.doubleDown();
			Card newCard = shoe.poll();
			player.updateStrategyCard(newCard);
			hand.hit(newCard);

			HandResult result = new HandResult(hand, bet * 2);

			if (player.forfeit(hand, dealerCard, shoe)) {
				result.hasForfeited = true;
			}

			hr.add(result);
		}
	}

	@Override
	public RoundOutcome play(Shoe shoe, handy.common21.sim.Rules rules, handy.common21.sim.Player player,
			OUTCOME previousOutcome) throws Exception {
		if (!(player instanceof Player)) {
			throw new Exception("DoubleAttack requires DoubleAttack Player");
		}
		if (!(rules instanceof Rules)) {
			throw new Exception("DoubleAttack requires DoubleAttack Rules");
		}
		if (!(shoe instanceof SpanishShoe)) {
			throw new Exception("DoubleAttack requires SpanishShoe");
		}
		return playRoundMaster((SpanishShoe) shoe, (Rules) rules, (Player) player, previousOutcome);
	}

	public boolean workBlackjackLogic(handy.common21.sim.Player player, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet) {
		// Need blackjack check here
		if (playerHand.isNatural()) { // Player wins with 21
			determineSideBetOutcome(player, dealerHand, playerHand);
			player.dollars += playerBet;
			lastHandOutcome = OUTCOME.PLAYER_BLACKJACK;
		} else if (dealerHand.isNatural()) {
			determineSideBetOutcome(player, dealerHand, playerHand);
			player.dollars -= playerBet;
			lastHandOutcome = OUTCOME.DEALER_WIN;
		} else {
			return true;
		}
		return false;
	}

	public int determineOutcome(OUTCOME outcome, handy.common21.sim.HandResult result,
			handy.common21.sim.Hand dealerHand) {
		if (result.hasSurrendered || ((HandResult) result).hasForfeited) {
			return -((int) result.bet / 2);
		} else if (outcome == OUTCOME.DEALER_WIN) {
			return -result.bet;
		} else if (outcome == OUTCOME.PLAYER_WIN || outcome == OUTCOME.PLAYER_BLACKJACK) {
			return result.bet;
		} else {
			return 0;
		}
	}

	public void setupSideBets(handy.common21.sim.Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet, handy.common21.sim.Player player) {
		if (rules.getSideBets().contains(InsuranceDescription.getInsuranceDescription())
				&& dealerHand.firstCard().value == VALUE.ACE) {
			int insuranceBet = ((Player) player).insuranceBet(shoe, playerHand, dealerHand.firstCard(), playerBet);
			// if(insuranceBet != 0)
			// System.out.println("Insuring: " + insuranceBet + " against " +
			// playerBet);
			sideBetsMap.put(InsuranceDescription.getInsuranceDescription(), insuranceBet);
		}
	}

	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		return getVanillaBlackjackOutcome(playerHand, dealerHand);
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
		if (((Player) player).doubleAttack(shoe, dealerUpCard, (Hand) playerHand)) {
			return playerBet * 2;
		} else {
			return playerBet;
		}
	}

}
