package handy.spanish21.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Card.VALUE;
import handy.cards.CardRelationshipUtility;
import handy.cards.Shoe;
import handy.cards.SpanishShoe;
import handy.common21.sim.RoundOutcome;
import handy.common21.sim.sidebet.InsuranceDescription;

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

	public RoundOutcome play(Shoe shoe, handy.common21.sim.Rules rules, handy.common21.sim.Player player,
			OUTCOME previousOutcome) throws Exception {
		if (!(shoe instanceof SpanishShoe)) {
			throw new Exception("Spanish21 requires Spanish Shoe");
		}
		if (!(rules instanceof Rules)) {
			throw new Exception("Spanish21 requires Spanish Shoe");
		}
		if (!(player instanceof Player)) {
			throw new Exception("Spanish21 requires Spanish Shoe");
		}
		return playRoundMaster((SpanishShoe) shoe, (Rules) rules, (Player) player, previousOutcome);
	}

	public boolean workBlackjackLogic(handy.common21.sim.Player player, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet) {
		// Need blackjack check here
		if (playerHand.isNatural()) { // Player wins with 21
			determineSideBetOutcome(player, dealerHand, playerHand);
			player.dollars += (playerBet * 1.5);
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
			if (CardRelationshipUtility.allThreeSevens(result.hand.cards)
					&& CardRelationshipUtility.allThreeSameSuite(result.hand.cards)
					&& dealerHand.firstCard().getBlackjackValue() == 7) {
				if (result.bet > 25) {
					return 5000;
				} else {
					return 1000;
				}
			} else if (result.hand.cards.size() == 5 && result.hand.getValue() == 21) {
				return (int) (result.bet * 1.5);
			} else if (result.hand.cards.size() == 6 && result.hand.getValue() == 21) {
				return result.bet * 2;
			} else if (result.hand.cards.size() >= 7 && result.hand.getValue() == 21) {
				return result.bet * 3;
			} else if (CardRelationshipUtility.allThreeSevens(result.hand.cards)
					&& CardRelationshipUtility.allThreeSameSpades(result.hand.cards)) {
				return result.bet * 3;
			} else if (CardRelationshipUtility.allThreeSevens(result.hand.cards)
					&& CardRelationshipUtility.allThreeSameSuite(result.hand.cards)) {
				return result.bet * 2;
			} else if (CardRelationshipUtility.allThreeSevens(result.hand.cards)) {
				return (int) (result.bet * 1.5);
			} else if (CardRelationshipUtility.allOneEachSixSevenEight(result.hand.cards)
					&& CardRelationshipUtility.allThreeSameSpades(result.hand.cards)) {
				return result.bet * 3;
			} else if (CardRelationshipUtility.allOneEachSixSevenEight(result.hand.cards)
					&& CardRelationshipUtility.allThreeSameSuite(result.hand.cards)) {
				return result.bet * 2;
			} else if (CardRelationshipUtility.allOneEachSixSevenEight(result.hand.cards)) {
				return (int) (result.bet * 1.5);
			} else {
				return result.bet;
			}
		} else {
			return 0;
		}
	}

	public void setupSideBets(handy.common21.sim.Rules rules, Shoe shoe, handy.common21.sim.Hand playerHand,
			handy.common21.sim.Hand dealerHand, int playerBet, handy.common21.sim.Player player) {
		if (rules.getSideBets().contains(InsuranceDescription.getInsuranceDescription())
				&& dealerHand.firstCard().value == VALUE.ACE) {
			int insuranceBet = ((Player) player).insuranceBet(shoe, (Hand) playerHand, dealerHand.firstCard(),
					playerBet);
			// if(insuranceBet != 0)
			// System.out.println("Insuring: " + insuranceBet + " against " +
			// playerBet);
			sideBetsMap.put(InsuranceDescription.getInsuranceDescription(), insuranceBet);
		}
	}

	public OUTCOME getOutcome(handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand) {
		if (playerHand.isBust()) {
			return OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			return OUTCOME.PLAYER_BLACKJACK;
		} else if (dealerHand.isNatural()) {
			return OUTCOME.DEALER_WIN;
		} else if (dealerHand.isBust()) {
			return OUTCOME.PLAYER_WIN;
		} else if (playerHand.getValue() == 21) {
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

	@Override
	public int playerBetMod(handy.common21.sim.Player player, Shoe shoe, Card dealerUpCard,
			handy.common21.sim.Hand playerHand, int playerBet) {
		//No op
		return playerBet;
	}

}
