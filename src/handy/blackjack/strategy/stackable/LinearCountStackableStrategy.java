package handy.blackjack.strategy.stackable;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.common21.sim.Rules;

public abstract class LinearCountStackableStrategy {
	public abstract Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, int trueCount);
}
