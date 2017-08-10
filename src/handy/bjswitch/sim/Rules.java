package handy.bjswitch.sim;

import java.util.Set;

import handy.common21.sim.sidebet.SideBetDescription;

public class Rules extends handy.common21.sim.Rules {

	public Rules(boolean canSurrender, int shoeDeckCount, float deckPenetration,
			Set<SideBetDescription> sideBets, int minBet) {
		super(true, true, canSurrender, shoeDeckCount, deckPenetration, 
				true, sideBets, minBet);
	}

}
