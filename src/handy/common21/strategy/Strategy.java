package handy.common21.strategy;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Round.OUTCOME;
import handy.cards.Card;
import handy.cards.Shoe;

public abstract class Strategy {
	public enum TYPES {TEXTBOOK, HIGHLOW, WONG_HALVES, EXACT_PROBABILITY};
	
	public abstract void resetShoe();
	
	public abstract void updateStrategyDealerHand(Hand dealerHand);
	
	public abstract void updateStrategyCard(Card card);
	
	public abstract void updateStrategyDealtHand(Hand card);
	
	public abstract Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception;
	
	public abstract int getBet(Shoe shoe, OUTCOME lastOutcome);
	
	public abstract boolean walkAway(Shoe shoe);
}
