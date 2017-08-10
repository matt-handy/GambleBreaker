package handy.blackjacksim.strategy.sidebet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.InsuranceDescription;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.strategy.sidebet.InsuranceSideBetStrategy;
import handy.blackjack.strategy.sidebet.InsuranceSideBetStrategy.INSURANCE_STRAT;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;
import junit.framework.TestCase;

public class InsuranceSideBetStrategyTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	
	public void testEndToEndInsuranceSideBet(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.WONG, rules.getShoeDeckCount(), -1));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks and Insurnace", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 29 && ssr.getHourlyReturnAt100GPH() <= 35);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5300 && ssr.getExpectedFinalCashPool() <= 5550);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
		assertTrue(ssr.getGamesBetweenSwitches() >= 35 && ssr.getGamesBetweenSwitches() <= 45);
		assertTrue(ssr.getAverageTablesSwitched() >= 30 && ssr.getAverageTablesSwitched() <= 45);	
	}
	
	public void testEndToEndInsuranceSideBetNoWalk(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.WONG, rules.getShoeDeckCount(), -1));
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Insurnace", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 13 && ssr.getHourlyReturnAt100GPH() <= 22);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5150 && ssr.getExpectedFinalCashPool() <= 5350);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.001);
	}
	
	public void testEndToEndInsuranceSideBetNoWalkTwoDeck(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 2, 0.70f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.WONG, rules.getShoeDeckCount(), -1));
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Insurnace, Two Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 35 && ssr.getHourlyReturnAt100GPH() <= 50);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5500 && ssr.getExpectedFinalCashPool() <= 5900);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
	}
	
	public void testEndToEndInsuranceSideBetOneDeckTextbook(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.EXACT_PROB, rules.getShoeDeckCount(), .35f));
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation with Probabilistic Insurnace, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= -5 && ssr.getHourlyReturnAt100GPH() <= 0);
		assertTrue(ssr.getExpectedFinalCashPool() >= 4900 && ssr.getExpectedFinalCashPool() <= 5000);
		assertTrue(ssr.getOddsOfBankrupcy() == 0);
	}
	
	
	public void testEndToEndBenchmarkSideBetNoWalkOneDeck(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation Benchmark, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 60 && ssr.getHourlyReturnAt100GPH() <= 75);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5900 && ssr.getExpectedFinalCashPool() <= 6200);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
	}
	
	public void testEndToEndInsuranceSideBetNoWalkOneDeck(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.WONG, rules.getShoeDeckCount(), -1));
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Insurnace, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 68 && ssr.getHourlyReturnAt100GPH() <= 85);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6000 && ssr.getExpectedFinalCashPool() <= 6300);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
	}
	
	public void testEndToEndInsuranceSideBetNoWalkOneDeckExactProb(){
		int runs = 30000;
		int runsPerSim = 1500;
		int startingCash = 5000;
		
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 1, 0.65f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(20, INSURANCE_STRAT.EXACT_PROB, rules.getShoeDeckCount(), .35f));
		StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, runs, runsPerSim, startingCash, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Insurnace Prob Strategy, One Deck", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 67 && ssr.getHourlyReturnAt100GPH() <= 80);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6000 && ssr.getExpectedFinalCashPool() <= 6300);
		assertTrue(ssr.getOddsOfBankrupcy() <= 0.01);
	}

}
