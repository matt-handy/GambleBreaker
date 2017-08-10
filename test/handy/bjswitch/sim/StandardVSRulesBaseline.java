package handy.bjswitch.sim;

import java.util.HashSet;

import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.bjswitch.sim.Rules;
import handy.common21.sim.SimulationRunner;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import junit.framework.TestCase;

public class StandardVSRulesBaseline extends TestCase {
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 7500;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BJSWITCH);
	Rules rules = new Rules(true, 8, 0.87f, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "BJSwitch Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();

		assertTrue(ssr.getHourlyReturnAt100GPH() >= -20 && ssr.getHourlyReturnAt100GPH() <= -16);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7150 && ssr.getExpectedFinalCashPool() <= 7350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "BJSwitch HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 27 && ssr.getHourlyReturnAt100GPH() <= 31);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7800 && ssr.getExpectedFinalCashPool() <= 8100);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerWong(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.WONG_HALVES, "BJSwitch Wong Halves Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 27 && ssr.getHourlyReturnAt100GPH() <= 31);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7800 && ssr.getExpectedFinalCashPool() <= 8100);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWhale(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash * 100, stratCfg, Strategy.TYPES.HIGHLOW, "BJSwitch HiLow Strategy Simulation, with Whale", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 245 && ssr.getHourlyReturnAt100GPH() <= 285);
		assertTrue(ssr.getExpectedFinalCashPool() >= 751500 && ssr.getExpectedFinalCashPool() <= 750000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.000);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "BJSwitch HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 20 && ssr.getHourlyReturnAt100GPH() <= 25);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7750 && ssr.getExpectedFinalCashPool() <= 7950);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "BJSwitch HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 22 && ssr.getHourlyReturnAt100GPH() <= 27);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7750 && ssr.getExpectedFinalCashPool() <= 8000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "BJSwitch HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 50 && ssr.getHourlyReturnAt100GPH() <= 55);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8200 && ssr.getExpectedFinalCashPool() <= 8375);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() >= 31 && ssr.getGamesBetweenSwitches() <= 36);
		assertTrue(ssr.getAverageTablesSwitched() >= 41 && ssr.getAverageTablesSwitched() <= 47);
		
	}
	
	
	
}
