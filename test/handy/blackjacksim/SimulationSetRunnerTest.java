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

public class SimulationSetRunnerTest extends TestCase {
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
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -11 && ssr.getHourlyReturnAt100GPH() <= -7);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4850 && ssr.getExpectedFinalCashPool() <= 4875);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 14 && ssr.getHourlyReturnAt100GPH() <= 18);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5200 && ssr.getExpectedFinalCashPool() <= 5300);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.0011);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 25 && ssr.getHourlyReturnAt100GPH() <= 32);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5400 && ssr.getExpectedFinalCashPool() <= 5500);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 35 && ssr.getGamesBetweenSwitches() <= 50);
		assertTrue(ssr.getAverageTablesSwitched() >= 35 && ssr.getAverageTablesSwitched() <= 50);	
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 10 && ssr.getHourlyReturnAt100GPH() <= 15);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5150 && ssr.getExpectedFinalCashPool() <= 5250);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWinWithThreshold(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN_HIGH_START);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Organic Bet Increase on Win, with High Starting Threshold", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 10 && ssr.getHourlyReturnAt100GPH() <= 15);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5150 && ssr.getExpectedFinalCashPool() <= 5250);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 10 && ssr.getHourlyReturnAt100GPH() <= 17);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5150 && ssr.getExpectedFinalCashPool() <= 5300);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerWongHalves(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.WONG_HALVES, "Wong Halves Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 12 && ssr.getHourlyReturnAt100GPH() <= 20);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5200 && ssr.getExpectedFinalCashPool() <= 5350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerWongHalvesWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.WONG_HALVES, "Wong Halves with Walks Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 31 && ssr.getHourlyReturnAt100GPH() <= 40);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5400 && ssr.getExpectedFinalCashPool() <= 5600);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 30 && ssr.getGamesBetweenSwitches() <= 45);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 45);	
	}
}
