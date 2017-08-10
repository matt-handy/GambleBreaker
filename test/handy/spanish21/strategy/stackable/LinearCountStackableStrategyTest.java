package handy.spanish21.strategy.stackable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import handy.spanish21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.SimulationRunner;
import handy.spanish21.strategy.Strategy;
import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;
import handy.spanish21.strategy.stackable.BasicIndexEightDeck;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.StrategyConfig;
import junit.framework.TestCase;

public class LinearCountStackableStrategyTest extends TestCase {
	public static final boolean H17 = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 5000;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.SPANISH21);
	Rules rules = new Rules(H17, 8, 0.85f, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimRunnerHiLowWithWalksBaseline(){
		StrategyConfig stratCfg = new StrategyConfig(true, -5, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish 21 HiLow Strategy Simulation with Walks, baseline for stackable", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 7 && ssr.getHourlyReturnAt100GPH() <= 10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5100 && ssr.getExpectedFinalCashPool() <= 5150);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.02 && ssr.getOddsOfBankrupcy() <= 0.03);
		assertTrue(ssr.getGamesBetweenSwitches() >= 45 && ssr.getGamesBetweenSwitches() <= 50);
		assertTrue(ssr.getAverageTablesSwitched() >= 29 && ssr.getAverageTablesSwitched() <= 34);	
	}
	
	public void testSimRunnerHiLowWithWalksBasicIndex(){
		List<LinearCountStackableStrategy> lcsss = new ArrayList<LinearCountStackableStrategy>();
		lcsss.add(new BasicIndexEightDeck());
		
		StrategyConfig stratCfg = new StrategyConfig(true, -5, BASE_BET);
		stratCfg.setLinearCountStackableStrategies(lcsss);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish 21 HiLow Strategy Simulation with Walks and Basic Index", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 0 && ssr.getHourlyReturnAt100GPH() <= 2);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5000 && ssr.getExpectedFinalCashPool() <= 5100);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.02 && ssr.getOddsOfBankrupcy() <= 0.032);
		assertTrue(ssr.getGamesBetweenSwitches() >= 45 && ssr.getGamesBetweenSwitches() <= 50);
		assertTrue(ssr.getAverageTablesSwitched() >= 29 && ssr.getAverageTablesSwitched() <= 34);	
	}
}
