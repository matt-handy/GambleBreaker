package handy.spanish21.sim;

import java.util.Map;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.InsuranceDescription;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.sidebet.SideBetStrategy;
import handy.spanish21.strategy.Strategy;

public class Player extends handy.common21.sim.Player{
	protected handy.spanish21.strategy.Strategy strat;
	
	public Player(Strategy strategy, int dollars, Map<SideBetDescription, SideBetStrategy> sideBetStrategies){
		super(strategy, dollars, sideBetStrategies);
		strat = strategy;
	}
	
	public int insuranceBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet){
		if(sideBetStrategies.containsKey(InsuranceDescription.getInsuranceDescription())){
			return sideBetStrategies.get(InsuranceDescription.getInsuranceDescription()).getBet(shoe, playerHand, dealerUpCard, regularBet);
		}else{
			return 0;
		}
	}
	
	public boolean forfeit(Hand hand, Card dealerCard, Shoe shoe){
		return strat.forfeit(hand, dealerCard, shoe);
	}
	
	
}
