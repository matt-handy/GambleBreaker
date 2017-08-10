package handy.doubleattack.sim;

import java.util.Map;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.cards.SpanishShoe;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.sidebet.SideBetStrategy;
import handy.doubleattack.sim.sidebet.InsuranceDescription;
import handy.spanish21.sim.Hand;
import handy.spanish21.strategy.Strategy;

public class Player extends handy.spanish21.sim.Player {
	public Player(Strategy strategy, int dollars, Map<SideBetDescription, SideBetStrategy> sideBetStrategies){
		super(strategy, dollars, sideBetStrategies);
	}
	
	public int insuranceBet(SpanishShoe shoe, Hand playerHand, Card dealerUpCard, int regularBet){
		if(sideBetStrategies.containsKey(InsuranceDescription.getInsuranceDescription())){
			return sideBetStrategies.get(InsuranceDescription.getInsuranceDescription()).getBet(shoe, playerHand, dealerUpCard, regularBet);
		}else{
			return 0;
		}
	}
	
	public boolean doubleAttack(Shoe shoe, Card upCard, Hand myHand){
		if(upCard.getBlackjackValue() >= 2 && upCard.getBlackjackValue() <= 8){
			return true;
		}else{
			return false;
		}
	}
}
