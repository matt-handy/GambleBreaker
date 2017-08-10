package handy.superfun21.sim;

import java.util.Map;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class Player extends handy.common21.sim.Player{
	public Player(Strategy strategy, int dollars, Map<SideBetDescription, SideBetStrategy> sideBetStrategies){
		super(strategy, dollars, sideBetStrategies);
	}
	
	public boolean forfeit(Hand hand, Card dealerCard, Shoe shoe){
		//Assume 2 deck strategy
		if(hand.cards.size() == 3){
			if(hand.getValue() == 15 || hand.getValue() == 17){
				if(dealerCard.getBlackjackValue() == 11){
					return true;
				}
			}else if(hand.getValue() == 16 && dealerCard.getBlackjackValue() >= 9){
				return true;
			}
		}else if(hand.cards.size() == 4){
			if(hand.getValue() == 17 && dealerCard.getBlackjackValue() == 11){
				return true;
			}
		}
		
		return false;
	}
	
	
}
