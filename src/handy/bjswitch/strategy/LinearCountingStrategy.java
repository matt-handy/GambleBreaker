package handy.bjswitch.strategy;

import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.strategy.StrategyConfig;

public abstract class LinearCountingStrategy extends handy.blackjack.strategy.LinearCountingStrategy {
	public LinearCountingStrategy(StrategyConfig cfg, Rules rules) {
		super(cfg, rules);
	}

	public Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		for (LinearCountStackableStrategy lcss : this.lcss) {
			Rules.MOVE move = lcss.determineMove(rules, hand, dealerCard, getTrueCount(shoe));
			if (move != null) {
				return move;
			}
		}
		
		return TextbookStrategy.determineTextbookMove(rules, hand, dealerCard);
	}
}
