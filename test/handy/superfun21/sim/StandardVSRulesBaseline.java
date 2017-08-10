package handy.superfun21.sim;

import java.util.HashSet;

import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.superfun21.sim.Rules;
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
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.SUPERFUN21);
	Rules rules = new Rules(2, 0.7f, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Superfun21 Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();

		assertTrue(ssr.getHourlyReturnAt100GPH() >= -37 && ssr.getHourlyReturnAt100GPH() <= -31);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6850 && ssr.getExpectedFinalCashPool() <= 7100);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Superfun21 HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -10 && ssr.getHourlyReturnAt100GPH() <= -6);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7250 && ssr.getExpectedFinalCashPool() <= 7450);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWhale(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash * 100, stratCfg, Strategy.TYPES.HIGHLOW, "Superfun21 HiLow Strategy Simulation, with Whale", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 7 && ssr.getHourlyReturnAt100GPH() <= 13);
		assertTrue(ssr.getExpectedFinalCashPool() >= 750000 && ssr.getExpectedFinalCashPool() <= 752000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.000);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Superfun21 HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -10 && ssr.getHourlyReturnAt100GPH() <= -6);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7250 && ssr.getExpectedFinalCashPool() <= 7450);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Superfun21 HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -10 && ssr.getHourlyReturnAt100GPH() <= -6);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7250 && ssr.getExpectedFinalCashPool() <= 7450);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Superfun21 HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 3 && ssr.getHourlyReturnAt100GPH() <= 9);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7500 && ssr.getExpectedFinalCashPool() <= 7700);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() >= 5 && ssr.getGamesBetweenSwitches() <= 9);
		assertTrue(ssr.getAverageTablesSwitched() >= 180 && ssr.getAverageTablesSwitched() <= 200);
	}
	
	
	
}
