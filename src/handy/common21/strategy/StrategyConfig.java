package handy.common21.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class StrategyConfig {
	private boolean willWalk;
	private int walkThresholdSingleCount;
	private int baseBet;
	private Map<SideBetDescription, SideBetStrategy> sideBetStrategies;
	
	private List<LinearCountStackableStrategy> lcsss = null;
	
	private boolean probBets = false;
	
	private float testThreshold = -1;
	private int testDealerShow = -1;
	private int testHoldingTotal = -1;
	
	private LINEAR_BET_STRAT strat = LINEAR_BET_STRAT.VANILLA;
	
	public StrategyConfig(boolean willWalk, int walkThresholdSingleCount, int baseBet){
		this.willWalk = willWalk;
		this.walkThresholdSingleCount = walkThresholdSingleCount;
		this.baseBet = baseBet;
		sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
	}
	
	public void setSideBetStrategy(Map<SideBetDescription, SideBetStrategy> sideBetStrategies){
		this.sideBetStrategies = sideBetStrategies;
	}
	
	public void setLinearCountStackableStrategies(List<LinearCountStackableStrategy> lcsss){
		this.lcsss = lcsss;
	}
	
	public List<LinearCountStackableStrategy> getLinearCountStackableStrategies(){
		return lcsss;
	}

	public boolean isWillWalk() {
		return willWalk;
	}

	public int getWalkThresholdSingleCount() {
		return walkThresholdSingleCount;
	}

	public int getBaseBet() {
		return baseBet;
	}
	
	public Map<SideBetDescription, SideBetStrategy> getSideBetStrategies(){
		return sideBetStrategies;
	}
	
	public LINEAR_BET_STRAT getLinearBettingStrategy(){
		return strat;
	}
	
	public void setLinearBettingStrategy(LINEAR_BET_STRAT strat){
		this.strat = strat;
	}

	
	public boolean useProbBets() {
		return probBets;
	}

	public void setProbBets(boolean probBets) {
		this.probBets = probBets;
	}
	
	public void setThresholdExperiment(float testThreshold, int testDealerShow, int testHoldingTotal){
		this.testThreshold = testThreshold;
		this.testDealerShow = testDealerShow;
		this.testHoldingTotal = testHoldingTotal;
	}

	public float getTestThreshold() {
		return testThreshold;
	}

	public int getTestDealerShow() {
		return testDealerShow;
	}

	public int getTestHoldingTotal() {
		return testHoldingTotal;
	}
	
}
