package handy.blackjacksim.strategy;

import java.util.HashSet;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import junit.framework.TestCase;

public class ExactProbTest extends TestCase {
	public static final boolean H17 = true;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 5000;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 8, 0.8f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimRunnerHiLowOneDeckBaseline(){
		Rules localRules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(localRules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation Baseline for Prob, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 55 && ssr.getHourlyReturnAt100GPH() <= 65);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5800 && ssr.getExpectedFinalCashPool() <= 6000);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);	
	}
	
	public void testSimRunnerHiLowOneDeckExactProb(){
		Rules localRules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setProbBets(true);
		SimulationRunner sr = new SimulationRunner(localRules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.EXACT_PROBABILITY, "Exact Prob Strategy Simulation, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 55 && ssr.getHourlyReturnAt100GPH() <= 65);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5800 && ssr.getExpectedFinalCashPool() <= 6000);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);	
	}
}
