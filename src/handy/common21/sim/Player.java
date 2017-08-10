package handy.common21.sim;

import java.util.Map;

import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.sidebet.SideBetStrategy;
import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;

public class Player {
	public Strategy strategy;
	public int dollars;
	
	protected Map<SideBetDescription, SideBetStrategy> sideBetStrategies;
	
	public Player(Strategy strategy, int dollars, Map<SideBetDescription, SideBetStrategy> sideBetStrategies){
		this.strategy = strategy;
		this.dollars = dollars;
		this.sideBetStrategies = sideBetStrategies;
	}
	
	public Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe)  throws Exception {
		return strategy.determineMove(rules, hand, dealerCard, shoe);
	}
	
	public int getBet(Shoe shoe, Round.OUTCOME lastOutcome){
		return strategy.getBet(shoe, lastOutcome);
	}
	
	public void resetStrategyNewShoe(){
		strategy.resetShoe();
		
		for(SideBetStrategy sbs : sideBetStrategies.values()){
			sbs.resetShoe();
		}
	}
	
	public void updateStrategyDealerHand(Hand dealerHand){
		strategy.updateStrategyDealerHand(dealerHand);
		
		for(SideBetStrategy sbs : sideBetStrategies.values()){
			sbs.updateStrategyDealerHand(dealerHand);
		}
	}
	
	public void updateStrategyPlayerHand(Hand myHand){
		strategy.updateStrategyDealtHand(myHand);
		
		for(SideBetStrategy sbs : sideBetStrategies.values()){
			sbs.updateStrategyDealtHand(myHand);
		}
	}
	
	public void updateStrategyCard(Card card){
		strategy.updateStrategyCard(card);
		
		for(SideBetStrategy sbs : sideBetStrategies.values()){
			sbs.updateStrategyCard(card);
		}
	}
	
	public boolean walkAway(Shoe shoe){
		return strategy.walkAway(shoe);
	}
	
	public int getSideBet(SideBetDescription sbs, Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet){
		if(sideBetStrategies.containsKey(sbs)){
			return sideBetStrategies.get(sbs).getBet(shoe, playerHand, dealerUpCard, regularBet);
		}else{
			return 0;
		}
	}
}
