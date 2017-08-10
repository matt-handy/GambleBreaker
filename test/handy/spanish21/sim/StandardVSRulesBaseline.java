package handy.spanish21.sim;

import java.util.HashSet;

import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.spanish21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.SimulationRunner;
import handy.spanish21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import junit.framework.TestCase;

public class StandardVSRulesBaseline extends TestCase {
	public static final boolean H17 = true;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	int runs = 30000;
	int runsPerSim = 1500;
	int startingCash = 7500;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.SPANISH21);
	Rules rules = new Rules(H17, 8, 0.87f, new HashSet<SideBetDescription>(), MIN_BET);
	Rules s17Rules = new Rules(false, 8, 0.87f, new HashSet<SideBetDescription>(), MIN_BET);
	Rules sixDeckrules = new Rules(H17, 6, 0.81f, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Spanish21 Textbook Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -25 && ssr.getHourlyReturnAt100GPH() <= -15);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7100 && ssr.getExpectedFinalCashPool() <= 7250);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 6 && ssr.getHourlyReturnAt100GPH() <= 12);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7575 && ssr.getExpectedFinalCashPool() <= 7725);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowSixDeck(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(sixDeckrules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation, 6 Deck H17", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 8 && ssr.getHourlyReturnAt100GPH() <= 12);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7550 && ssr.getExpectedFinalCashPool() <= 7750);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimSetRunnerTextbookS17(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(s17Rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Spanish21 Textbook Strategy Simulation, S17", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -15 && ssr.getHourlyReturnAt100GPH() <= -10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7200 && ssr.getExpectedFinalCashPool() <= 7400);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowS17(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(s17Rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation, S17", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 15 && ssr.getHourlyReturnAt100GPH() <= 21);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7650 && ssr.getExpectedFinalCashPool() <= 7900);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowS17WithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -5, BASE_BET);
		SimulationRunner sr = new SimulationRunner(s17Rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation with walks, S17", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 29 && ssr.getHourlyReturnAt100GPH() <= 36);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7850 && ssr.getExpectedFinalCashPool() <= 8050);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.002);
		assertTrue(ssr.getGamesBetweenSwitches() >= 42 && ssr.getGamesBetweenSwitches() <= 48);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 35);
	}
	
	public void testSimRunnerHiLowWhale(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.TANDEM_WHALE_APPROX);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash * 100, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation, with Whale", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 50 && ssr.getHourlyReturnAt100GPH() <= 56);
		assertTrue(ssr.getExpectedFinalCashPool() >= 750000 && ssr.getExpectedFinalCashPool() <= 752000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation with Organic Bet Increase on Win", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 4 && ssr.getHourlyReturnAt100GPH() <= 10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7520 && ssr.getExpectedFinalCashPool() <= 7700);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation with Organic Bet Increase On Loss", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
	
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 4 && ssr.getHourlyReturnAt100GPH() <= 8);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7550 && ssr.getExpectedFinalCashPool() <= 7650);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.002);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -5, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "Spanish21 HiLow Strategy Simulation with Walks", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 20 && ssr.getHourlyReturnAt100GPH() <= 28);
		assertTrue(ssr.getExpectedFinalCashPool() >= 7750 && ssr.getExpectedFinalCashPool() <= 7950);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.003);
		assertTrue(ssr.getGamesBetweenSwitches() >= 42 && ssr.getGamesBetweenSwitches() <= 48);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 35);
	}
	
	
	
}
