package handy.spanish21.strategy;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;

public abstract class Strategy extends handy.common21.strategy.Strategy{
	public boolean forfeit(Hand hand, Card dealerCard, Shoe shoe) {
		if(hand.getValue() >= 12 && hand.getValue() <= 16){
			if(dealerCard.getBlackjackValue() >= 8){
				return true;
			}
		}else if(hand.getValue() == 17){
			if(dealerCard.getBlackjackValue() == 11){
				return true;
			}
		}
		return false;
	}
}
