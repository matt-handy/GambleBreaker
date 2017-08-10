package handy.blackjacksim.strategy.sidebet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.blackjack.sim.sidebet.RoyalMatchDescription;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.strategy.sidebet.RoyalMatchSideBetStrategy;
import handy.blackjack.strategy.sidebet.RoyalMatchSideBetStrategy.ROYAL_MATCH_STRAT;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;
import junit.framework.TestCase;

public class RoyalMatchBetStrategyTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	
	public void testEndToEndNaiveRoyalMatchSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 50000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(RoyalMatchDescription.getRoyalMatchDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(RoyalMatchDescription.getRoyalMatchDescription(), new RoyalMatchSideBetStrategy(20, ROYAL_MATCH_STRAT.NAIVE, rules.getShoeDeckCount(), .1f));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Royal Match, Naive", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -1850 && ssr.getHourlyReturnAt100GPH() <= -1820);
		assertTrue(ssr.getExpectedFinalCashPool() >= 22000 && ssr.getExpectedFinalCashPool() <= 23000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
	}
	
	public void testEndToEndTensRoyalMatchSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(RoyalMatchDescription.getRoyalMatchDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(RoyalMatchDescription.getRoyalMatchDescription(), new RoyalMatchSideBetStrategy(20, ROYAL_MATCH_STRAT.TENS, rules.getShoeDeckCount(), -.1f));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Royal Match, Tens", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -15 && ssr.getHourlyReturnAt100GPH() <= -5);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4700 && ssr.getExpectedFinalCashPool() <= 4900);
		assertTrue(ssr.getOddsOfBankrupcy() == 0.000);
	}
	
	public void testEndToEndTensRoyalMatchSideBetBaseline(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		//sbds.add(RoyalMatchDescription.getRoyalMatchDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		//sideBetStrategies.put(RoyalMatchDescription.getRoyalMatchDescription(), new RoyalMatchSideBetStrategy(20, ROYAL_MATCH_STRAT.TENS, rules.getShoeDeckCount(), -.1f));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Royal Match, Baseline", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -30 && ssr.getHourlyReturnAt100GPH() <= 0);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4700 && ssr.getExpectedFinalCashPool() <= 5000);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.000 && ssr.getOddsOfBankrupcy() <= 0.1);
	}
}
