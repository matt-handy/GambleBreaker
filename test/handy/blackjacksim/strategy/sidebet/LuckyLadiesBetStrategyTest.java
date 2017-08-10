package handy.blackjacksim.strategy.sidebet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.blackjack.sim.sidebet.LuckyLadiesDescription;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.strategy.sidebet.LuckyLadiesSideBetStrategy;
import handy.blackjack.strategy.sidebet.LuckyLadiesSideBetStrategy.LUCKY_LADIES_STRAT;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;
import junit.framework.TestCase;

public class LuckyLadiesBetStrategyTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	
	public void testEndToEndNaiveLuckyLadiesSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 10000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(LuckyLadiesDescription.getLuckyLadiesDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(LuckyLadiesDescription.getLuckyLadiesDescription(), new LuckyLadiesSideBetStrategy(20, LUCKY_LADIES_STRAT.NAIVE, rules.getShoeDeckCount(), -1f));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Lucky Ladies, Naive", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -700 && ssr.getHourlyReturnAt100GPH() <= -260);
		assertTrue(ssr.getExpectedFinalCashPool() >= 0 && ssr.getExpectedFinalCashPool() <= 1050);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.7);
	}
	
	public void testEndToEndTensCountLuckyLadiesSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 10000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(LuckyLadiesDescription.getLuckyLadiesDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(LuckyLadiesDescription.getLuckyLadiesDescription(), new LuckyLadiesSideBetStrategy(20, LUCKY_LADIES_STRAT.TENS, rules.getShoeDeckCount(), -.1f));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Lucky Ladies, Tens Count", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -20 && ssr.getHourlyReturnAt100GPH() <= -5);
		assertTrue(ssr.getExpectedFinalCashPool() >= 9800 && ssr.getExpectedFinalCashPool() <= 10000);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.00);
	
	}
	
}
