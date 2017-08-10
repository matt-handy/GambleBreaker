package handy.common21.sim.sidebet;

import handy.common21.sim.Hand;

public abstract class SideBetDescription {
	//This returns either a negative number on loss, 0 on push, positive for win. Result
	//is direct modifier of player dollar sum
	public abstract int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet);
}
