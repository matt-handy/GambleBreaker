package handy.common21.strategy.sidebet;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;

public abstract class SideBetStrategy {
	protected int baseBet;
	
	public SideBetStrategy(int baseBet){
		this.baseBet = baseBet;
	}
	
	public abstract void resetShoe();
	
	public abstract void updateStrategyDealerHand(Hand dealerHand);
	
	public abstract void updateStrategyCard(Card card);
	
	public abstract void updateStrategyDealtHand(Hand card);
	
	//Returns zero for "no bet"
	public abstract int getBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet);
}
