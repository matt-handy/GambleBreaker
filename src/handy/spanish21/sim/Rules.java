package handy.spanish21.sim;

import java.util.Set;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;

public class Rules extends handy.common21.sim.Rules {
	
	public Rules(boolean h17, int shoeDeckCount, float deckPenetration,
			Set<SideBetDescription> sideBets, int minBet){
		super(h17, true, true, shoeDeckCount, deckPenetration, true, sideBets, minBet);
	}
	
	@Override
	public boolean canDouble(Hand hand){
		return true;
	}
}
