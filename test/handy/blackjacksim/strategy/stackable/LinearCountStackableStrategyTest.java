package handy.blackjacksim.strategy.stackable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.strategy.stackable.FabFourStrategy;
import handy.blackjack.strategy.stackable.IllustriousEighteen;
import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import junit.framework.TestCase;

public class LinearCountStackableStrategyTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 5000;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimRunnerHiLowWithWalksAndFab4(){
		List<LinearCountStackableStrategy> lcsss = new ArrayList<LinearCountStackableStrategy>();
		lcsss.add(new FabFourStrategy());
		//lcsss.add(new IllustriousEighteen());
		
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setLinearCountStackableStrategies(lcsss);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks and Fab4", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 21 && ssr.getHourlyReturnAt100GPH() <= 33);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5225 && ssr.getExpectedFinalCashPool() <= 5490);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 30 && ssr.getGamesBetweenSwitches() <= 45);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 45);	
	}
	
	public void testSimRunnerHiLowWithWalksBaseline(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks, baseline for stackable", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 26 && ssr.getHourlyReturnAt100GPH() <= 33);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5325 && ssr.getExpectedFinalCashPool() <= 5590);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 35 && ssr.getGamesBetweenSwitches() <= 45);
		assertTrue(ssr.getAverageTablesSwitched() >= 35 && ssr.getAverageTablesSwitched() <= 45);	
	}
	
	public void testSimRunnerHiLowWithWalksFab4AndI18(){
		List<LinearCountStackableStrategy> lcsss = new ArrayList<LinearCountStackableStrategy>();
		lcsss.add(new FabFourStrategy());
		lcsss.add(new IllustriousEighteen());
		
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setLinearCountStackableStrategies(lcsss);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks, Fab4, and I18", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 30 && ssr.getHourlyReturnAt100GPH() <= 33);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5450 && ssr.getExpectedFinalCashPool() <= 5510);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 30 && ssr.getGamesBetweenSwitches() <= 45);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 45);	
	}
}
