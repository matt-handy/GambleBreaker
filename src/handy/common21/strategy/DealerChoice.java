package handy.common21.strategy;

import handy.common21.sim.Hand;
import handy.cards.Shoe;

public class DealerChoice {
	public static Hand playDealerHand(Shoe shoe, Hand hand, boolean hit17){
		if(hit17){
			while(hand.getValue() <= 16 || (hand.getValue() == 17 && hand.isSoft())){
				hand.hit(shoe.poll());
			}
		}else{
			while(hand.getValue() <= 16){
				hand.hit(shoe.poll());
			}
		}
		return hand;
	}
}
