package handy.common21.sim;

import handy.common21.sim.Round.OUTCOME;

public class RoundOutcome {
	private OUTCOME outcome;
	private boolean shouldWalk;
	
	public RoundOutcome(OUTCOME outcome, boolean shouldWalk){
		this.outcome = outcome;
		this.shouldWalk = shouldWalk;
	}

	public OUTCOME getOutcome() {
		return outcome;
	}

	public boolean isShouldWalk() {
		return shouldWalk;
	}
}
