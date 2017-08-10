package handy.blackjack.strategy.stackable;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;

public class FabFourStrategy extends LinearCountStackableStrategy {

	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, int trueCount) {
		int value = hand.getValue();
		if (rules.canSurrender(hand) && !hand.isSoft()) {
			if (value == 14 && dealerCard.getBlackjackValue() == 10 && trueCount >= 4) {
				return MOVE.SURRENDER;
			} else if (value == 15 && dealerCard.getBlackjackValue() == 10 && trueCount >= 0) {
				return MOVE.SURRENDER;
			} else if (value == 15 && dealerCard.getBlackjackValue() == 9 && trueCount >= 2) {
				return MOVE.SURRENDER;
			} else if (value == 15 && dealerCard.getBlackjackValue() == 11 && trueCount >= 0 && rules.isH17()) {
				return MOVE.SURRENDER;
			} else if (value == 15 && dealerCard.getBlackjackValue() == 11 && trueCount >= 2 && !rules.isH17()) {
				return MOVE.SURRENDER;
			}
		}
		return null;
	}

}
