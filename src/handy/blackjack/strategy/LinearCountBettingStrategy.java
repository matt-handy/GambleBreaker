package handy.blackjack.strategy;

public class LinearCountBettingStrategy extends handy.common21.strategy.LinearCountBettingStrategy{
	public LinearCountBettingStrategy(int baseBet, LINEAR_BET_STRAT strat, int minimumBet){
		super(baseBet, strat, minimumBet);
	}
	
	protected int getDesiredBet(int trueCount){
		if(trueCount <= 0){
			return minimum;
		}else if(trueCount < 2){
			return baseBet;
		}else if(trueCount < 3){
			return baseBet * 2;
		}else if(trueCount < 6){
			return baseBet * ((int) trueCount);
		}else{
			return baseBet * 6;
		}
	}
	
	protected int getTandemWhaleApprox(int trueCount){
		if(trueCount <= 1){
			return baseBet;
		}else{
			return baseBet * 30;
		}
	}
	
}
