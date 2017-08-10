package handy.spanish21.strategy;

public class LinearCountBettingStrategy extends handy.common21.strategy.LinearCountBettingStrategy{
	public LinearCountBettingStrategy(int baseBet, LINEAR_BET_STRAT strat, int minimumBet){
		super(baseBet, strat, minimumBet);
	}
	
	protected int getDesiredBet(int trueCount){
		if(trueCount < -2){
			return minimum;
		}else if(trueCount == -2){
			return baseBet;
		}else{
			return (int) (baseBet * 1.5 * (trueCount + 3));
		}
	}
	
	protected int getTandemWhaleApprox(int trueCount){
		if(trueCount <= -1){
			return baseBet;
		}else{
			return baseBet * 30;
		}
	}
}
