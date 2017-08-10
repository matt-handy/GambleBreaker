package handy.freebet.sim;

import handy.common21.sim.Hand;

public class HandResult extends handy.common21.sim.HandResult {
	public int loseBet;
	
	public HandResult(Hand hand, int winBet, int loseBet){
		super(hand, winBet);
		this.loseBet = loseBet;
	}
}
