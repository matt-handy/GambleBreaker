package handy.superfun21.sim;

import java.util.Set;

import handy.common21.sim.sidebet.SideBetDescription;

public class Rules extends handy.common21.sim.Rules {

	public Rules(int shoeDeckCount, float deckPenetration,
			Set<SideBetDescription> sideBets, int minBet) {
		super(true, true, true, shoeDeckCount, deckPenetration, true, sideBets, minBet);
	}
	
	public boolean canDouble(handy.common21.sim.Hand hand){
		return true;
	}

}
