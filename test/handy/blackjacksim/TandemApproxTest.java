package handy.blackjacksim;

import java.util.HashSet;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import handy.common21.stats.SimulationSetResults;
import junit.framework.TestCase;

public class TandemApproxTest extends TestCase {

	public static final boolean H17 = true;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 25000;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 8, 0.85f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);

	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Tandem Whale Approximation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 90 && ssr.getHourlyReturnAt100GPH() <= 130);
		assertTrue(ssr.getExpectedFinalCashPool() >= 26000 && ssr.getExpectedFinalCashPool() <= 27000);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.019 && ssr.getOddsOfBankrupcy() <= 0.03);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
}
