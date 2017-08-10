package handy.blackjacksim;

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

public class ModerateDurationTest extends TestCase {
	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	public static final int SAMPLES_PER_SIM = 50000;
	public static final int HANDS_PER_SIM = 3500;
	public static final int STARTING_CASH = 5000;
	
	Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
	SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
	
	public void testSimRunnerHiLowWithWalks(){
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		SimulationRunner sr = new SimulationRunner(rules, SAMPLES_PER_SIM, HANDS_PER_SIM, STARTING_CASH, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks - week long", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 25 && ssr.getHourlyReturnAt100GPH() <= 35);
		assertTrue(ssr.getExpectedFinalCashPool() >= 5900 && ssr.getExpectedFinalCashPool() <= 6200);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.02 && ssr.getOddsOfBankrupcy() <= 0.03);
		assertTrue(ssr.getGamesBetweenSwitches() >= 30 && ssr.getGamesBetweenSwitches() <= 40);
		assertTrue(ssr.getAverageTablesSwitched() >= 90 && ssr.getAverageTablesSwitched() <= 100);	
	}
	
	public void testEndToEndInsuranceSideBetHighLimit(){
		Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
		sbds.add(InsuranceDescription.getInsuranceDescription());
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, sbds, MIN_BET);
		
		Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
		sideBetStrategies.put(InsuranceDescription.getInsuranceDescription(), new InsuranceSideBetStrategy(100, INSURANCE_STRAT.WONG, rules.getShoeDeckCount(), -1));
		StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
		stratCfg.setSideBetStrategy(sideBetStrategies);
		SimulationRunner sr = new SimulationRunner(rules, SAMPLES_PER_SIM, HANDS_PER_SIM, STARTING_CASH, stratCfg, Strategy.TYPES.HIGHLOW, "HiLow Strategy Simulation with Walks and Large Insurnace - week long", simConfig);
		SimulationSetResults ssr = sr.runSimSet();
		ssr.printString();
		
		assertTrue(ssr.getHourlyReturnAt100GPH() >= 28 && ssr.getHourlyReturnAt100GPH() <= 36);
		assertTrue(ssr.getExpectedFinalCashPool() >= 6000 && ssr.getExpectedFinalCashPool() <= 6250);
		assertTrue(ssr.getOddsOfBankrupcy() >= 0.01 && ssr.getOddsOfBankrupcy() <= 0.03);
		assertTrue(ssr.getGamesBetweenSwitches() >= 30 && ssr.getGamesBetweenSwitches() <= 40);
		assertTrue(ssr.getAverageTablesSwitched() >= 90 && ssr.getAverageTablesSwitched() <= 100);	
	}
}
