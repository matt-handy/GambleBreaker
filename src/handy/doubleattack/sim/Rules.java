package handy.doubleattack.sim;

import java.util.Set;

import handy.common21.sim.sidebet.SideBetDescription;

public class Rules extends handy.spanish21.sim.Rules {

	public Rules(int shoeDeckCount, float deckPenetration,
			Set<SideBetDescription> sideBets, int minBet) {
		super(false, shoeDeckCount, deckPenetration, sideBets, minBet);
	}

}
