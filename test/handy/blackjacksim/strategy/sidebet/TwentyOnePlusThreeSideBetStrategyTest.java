package handy.blackjacksim.strategy.sidebet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.sim.sidebet.TwentyOnePlusThreeDescription;
import handy.blackjack.strategy.sidebet.TwentyOnePlusThreeSideBetStrategy;
import handy.blackjack.strategy.sidebet.TwentyOnePlusThreeSideBetStrategy.TWENTY_ONE_PLUS_THREE_STRAT;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;
import junit.framework.TestCase;

public class TwentyOnePlusThreeSideBetStrategyTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	
	public void testEndToEndNaiveTwentyOnePlusThreeSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().resetStats();
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription(), new TwentyOnePlusThreeSideBetStrategy(20, TWENTY_ONE_PLUS_THREE_STRAT.NAIVE));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation 21+3, Naive", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		System.out.println("Total Gain: " + TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalWin());
		System.out.println("Total Loss: " + TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalLoss());
		System.out.println("Win/Loss Ratio: " + (TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalWin() / ((double)TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalLoss())));
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -210 && ssr.getHourlyReturnAt100GPH() <= -155);
		assertTrue(ssr.getExpectedFinalCashPool() >= 1700 && ssr.getExpectedFinalCashPool() <= 2450);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.25 && ssr.getOddsOfBankrupcy() <= 0.32);
	}
	
	public void testEndToEndSimpleRatioTwentyOnePlusThreeSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().resetStats();
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription(), new TwentyOnePlusThreeSideBetStrategy(20, TWENTY_ONE_PLUS_THREE_STRAT.RATIO));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation 21+3, Simple Ratio", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		System.out.println("Total Gain: " + TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalWin());
		System.out.println("Total Loss: " + TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalLoss());
		System.out.println("Win/Loss Ratio: " + (TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalWin() / ((double)TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().getTotalLoss())));
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -12 && ssr.getHourlyReturnAt100GPH() <= -8);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4800 && ssr.getExpectedFinalCashPool() <= 4870);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
	}
}
