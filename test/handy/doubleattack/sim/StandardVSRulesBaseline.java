package handy.doubleattack.sim;

import java.util.HashSet;

import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.doubleattack.sim.Rules;
import handy.common21.sim.SimulationRunner;
import handy.spanish21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import junit.framework.TestCase;

public class StandardVSRulesBaseline extends TestCase {
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 7500;

	SimConfig simConfig = new SimConfig(GAME_TYPE.DOUBLEATTACK);
	Rules rules = new Rules(8, 0.87f, new HashSet<SideBetDescription>(), MIN_BET);
	Rules sixDeckrules = new Rules(6, 0.81f, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Doubleattack Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();

		assertTrue(ssr.getHourlyReturnAt100GPH() >= -16 && ssr.getHourlyReturnAt100GPH() <= -10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7200 && ssr.getExpectedFinalCashPool() <= 7350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 45 && ssr.getHourlyReturnAt100GPH() <= 55);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8100 && ssr.getExpectedFinalCashPool() <= 8350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.02);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowSixDeck(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(sixDeckrules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation, 6 Deck H17", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 45 && ssr.getHourlyReturnAt100GPH() <= 55);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8100 && ssr.getExpectedFinalCashPool() <= 8350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.02);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWhale(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash * 100, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation, with Whale", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 200 && ssr.getHourlyReturnAt100GPH() <= 225);
		assertTrue(ssr.getExpectedFinalCashPool() >= 750000 && ssr.getExpectedFinalCashPool() <= 755000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.000);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 36 && ssr.getHourlyReturnAt100GPH() <= 46);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8000 && ssr.getExpectedFinalCashPool() <= 8250);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 42 && ssr.getHourlyReturnAt100GPH() <= 49);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8000 && ssr.getExpectedFinalCashPool() <= 8250);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.02);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -5, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Doubleattack HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 73 && ssr.getHourlyReturnAt100GPH() <= 884);
		assertTrue(ssr.getExpectedFinalCashPool() >= 8550 && ssr.getExpectedFinalCashPool() <= 8750);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.03);
		assertTrue(ssr.getGamesBetweenSwitches() >= 44 && ssr.getGamesBetweenSwitches() <= 47);
		assertTrue(ssr.getAverageTablesSwitched() >= 31 && ssr.getAverageTablesSwitched() <= 34);
		
	}
	
	
	
}
