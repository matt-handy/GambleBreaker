package handy.freebet.sim;

import java.util.HashSet;

import handy.common21.sim.sidebet.SideBetDescription;

public class Rules extends handy.common21.sim.Rules {

	public Rules(int shoeDeckCount, float deckPenetration, int minBet) {
		super(true, true, false, shoeDeckCount, deckPenetration, true, new HashSet<SideBetDescription>(), minBet);
	}

}
