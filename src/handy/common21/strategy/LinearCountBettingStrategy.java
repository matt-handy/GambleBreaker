package handy.common21.strategy;

import handy.blackjack.sim.Round;
import handy.common21.sim.Round.OUTCOME;

public abstract class LinearCountBettingStrategy {
	public enum LINEAR_BET_STRAT {VANILLA, MORE_ON_WIN, MORE_ON_LOSS, TANDEM_WHALE_APPROX, MORE_ON_WIN_HIGH_START};
	protected int minimum;
	protected int baseBet;
	protected LINEAR_BET_STRAT strat;
	protected int lastBet = -1;
	
	private int startingThreshold;
	
	public LinearCountBettingStrategy(int baseBet, LINEAR_BET_STRAT strat, int minimumBet){
		this.minimum = minimumBet;
		this.baseBet = baseBet;
		this.strat = strat;
		
		resetShoe();
	}
	
	public int getBet(int trueCount, Round.OUTCOME lastOutcome){
		if(strat == LINEAR_BET_STRAT.TANDEM_WHALE_APPROX){
			int bet = getTandemWhaleApprox(trueCount);
			lastBet = bet;
			return bet;
		}
		//By default, return the strategy bet
		int desiredBet = getDesiredBet(trueCount);
		int returnBet = desiredBet;
		
		//For each loss, lower the threshold
		if(strat == LINEAR_BET_STRAT.MORE_ON_WIN_HIGH_START &&
				lastOutcome != OUTCOME.PLAYER_BLACKJACK && lastOutcome != OUTCOME.PLAYER_WIN){
			startingThreshold -= baseBet;
		}
		
		//However, return the last bet if the strategy states to bet higher,
		//and we lost the last round
		//Only do this if organic bet changing enabled, otherwise follow strategy
		//If we want to start betting high and ramp down to strategy, check
		//for this first. This behavior is considered a modification on the
		//MORE_ON_WIN paradigm
		if(strat == LINEAR_BET_STRAT.MORE_ON_WIN_HIGH_START &&
				desiredBet < startingThreshold){
			returnBet = startingThreshold;
		}else if(!(lastOutcome == OUTCOME.PLAYER_BLACKJACK || lastOutcome == OUTCOME.PLAYER_WIN) 
				&& desiredBet > lastBet && (strat == LINEAR_BET_STRAT.MORE_ON_WIN || strat == LINEAR_BET_STRAT.MORE_ON_WIN_HIGH_START)){
			//System.out.println("Using last bet");
			returnBet = lastBet;
		}else if((lastOutcome == OUTCOME.PLAYER_BLACKJACK || lastOutcome == OUTCOME.PLAYER_WIN)
				&& desiredBet > lastBet && strat == LINEAR_BET_STRAT.MORE_ON_LOSS){
			returnBet = lastBet;
		}
		
		lastBet = returnBet;
		return returnBet;
	}
	
	protected abstract int getDesiredBet(int trueCount);
	
	protected abstract int getTandemWhaleApprox(int trueCount);
	
	public void resetShoe(){
		if(strat == LINEAR_BET_STRAT.MORE_ON_WIN_HIGH_START){
			startingThreshold = baseBet * 3;
		}
	}
}
