package handy.blackjacksim;

import java.util.HashSet;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.LinearCountBettingStrategy.LINEAR_BET_STRAT;
import handy.common21.stats.SimulationSetResults;
import junit.framework.TestCase;

public class StandardVSRulesBaseline extends TestCase {
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
	
	public void testSimSetRunnerTextbook(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -20 && ssr.getHourlyReturnAt100GPH() <= -10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4700 && ssr.getExpectedFinalCashPool() <= 4875);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLow(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 5 && ssr.getHourlyReturnAt100GPH() <= 8);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5080 && ssr.getExpectedFinalCashPool() <= 5150);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnWin(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_WIN);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Organic Bet Increase on Win, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 3 && ssr.getHourlyReturnAt100GPH() <= 7);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5050 && ssr.getExpectedFinalCashPool() <= 5120);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowOrganicBetIncreaseOnLoss(){
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setLinearBettingStrategy(LINEAR_BET_STRAT.MORE_ON_LOSS);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Organic Bet Increase On Loss, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 4 && ssr.getHourlyReturnAt100GPH() <= 10);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5000 && ssr.getExpectedFinalCashPool() <= 5150);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
		assertTrue(ssr.getGamesBetweenSwitches() == -1);
		assertTrue(ssr.getAverageTablesSwitched() == 0);
	}
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 13 && ssr.getHourlyReturnAt100GPH() <= 20);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5180 && ssr.getExpectedFinalCashPool() <= 5350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 50 && ssr.getGamesBetweenSwitches() <= 60);
		assertTrue(ssr.getAverageTablesSwitched() >= 15 && ssr.getAverageTablesSwitched() <= 30);	
	}
	
	
	public void testSimRunnerHiLowWithWalksDeeperPen(){
		Rules localRules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 8, 0.85f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(localRules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks, Deeper Pen, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 16 && ssr.getHourlyReturnAt100GPH() <= 24);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5250 && ssr.getExpectedFinalCashPool() <= 5400);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 50 && ssr.getGamesBetweenSwitches() <= 60);
		assertTrue(ssr.getAverageTablesSwitched() >= 20 && ssr.getAverageTablesSwitched() <= 30);	
	}
	
	public void testSimRunnerHiLowTwoDeck(){
		Rules localRules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 2, 0.7f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(localRules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation, Two Deck, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 30 && ssr.getHourlyReturnAt100GPH() <= 40);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5450 && ssr.getExpectedFinalCashPool() <= 5650);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);
	}
	
	public void testSimRunnerHiLowOneDeck(){
		Rules localRules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(localRules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation, One Deck, SV Rules", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 57 && ssr.getHourlyReturnAt100GPH() <= 70);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5850 && ssr.getExpectedFinalCashPool() <= 6100);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.001 && ssr.getOddsOfBankrupcy() <= 0.01);	
	}
}
