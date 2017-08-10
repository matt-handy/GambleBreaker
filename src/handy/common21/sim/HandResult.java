package handy.common21.sim;

import handy.common21.sim.Hand;

public class HandResult {
	public Hand hand;
	public int bet;
	
	public boolean hasSurrendered = false;
	
	public HandResult(Hand hand, int bet){
		this.hand = hand;
		this.bet = bet;
	}
}
