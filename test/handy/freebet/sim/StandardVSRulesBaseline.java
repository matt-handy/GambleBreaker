package handy.freebet.sim;

import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import junit.framework.TestCase;
import handy.common21.sim.SimulationRunner;

public class StandardVSRulesBaseline extends TestCase {
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 7500;
	
	Rules rules = new Rules(8, 0.87f, MIN_BET);
	SimConfig simConfig = new SimConfig(GAME_TYPE.FREEBET);
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Freebet Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();

		assertTrue(ssr.getHourlyReturnAt100GPH() >= -55 && ssr.getHourlyReturnAt100GPH() <= -45);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6500 && ssr.getExpectedFinalCashPool() <= 7000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Freebet HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -45 && ssr.getHourlyReturnAt100GPH() <= -40);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6600 && ssr.getExpectedFinalCashPool() <= 7000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWhale(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash * 100, stratCfg, Strategy.TYPES.HIGHLOW, "Freebet HiLow Strategy Simulation, with Whale", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -240 && ssr.getHourlyReturnAt100GPH() <= -200);
		assertTrue(ssr.getExpectedFinalCashPool() >= 745000 && ssr.getExpectedFinalCashPool() <= 750000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.000);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Freebet HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -40 && ssr.getHourlyReturnAt100GPH() <= -34);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6800 && ssr.getExpectedFinalCashPool() <= 7250);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.00);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Freebet HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -45 && ssr.getHourlyReturnAt100GPH() <= -37);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6700 && ssr.getExpectedFinalCashPool() <= 7000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Freebet HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -45 && ssr.getHourlyReturnAt100GPH() <= -38);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6700 && ssr.getExpectedFinalCashPool() <= 7000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() >= 51 && ssr.getGamesBetweenSwitches() <= 57);
		assertTrue(ssr.getAverageTablesSwitched() >= 25 && ssr.getAverageTablesSwitched() <= 34);
		
	}
	
	
	
}
