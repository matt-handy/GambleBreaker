package handy.blackjack.sim.sets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.blackjack.sim.sidebet.BusterDescription;
import handy.blackjack.sim.sidebet.KingsBountyDescription;
import handy.blackjack.sim.sidebet.LuckyLadiesDescription;
import handy.blackjack.sim.sidebet.RoyalMatchDescription;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.blackjack.strategy.sidebet.RoyalMatchSideBetStrategy;
import handy.blackjack.strategy.sidebet.BusterSideBetStrategy;
import handy.blackjack.strategy.sidebet.KingsBountySideBetStrategy;
import handy.blackjack.strategy.sidebet.KingsBountySideBetStrategy.KINGS_BOUNTY_STRAT;
import handy.blackjack.strategy.sidebet.LuckyLadiesSideBetStrategy;
import handy.blackjack.strategy.sidebet.LuckyLadiesSideBetStrategy.LUCKY_LADIES_STRAT;
import handy.blackjack.strategy.sidebet.RoyalMatchSideBetStrategy.ROYAL_MATCH_STRAT;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class TensSideChecker {

	public static final int MIN_BET = 10;
	public static final int BASE_BET = 500;
	public static final int SIMS = 30000;
	public static final int HANDS_PER_SIM = 10000;
	
	public static void main(String[] args) {
		Set<Thread> threadSet = new HashSet<Thread>();
		Thread t1 = new Thread(){
			public void run(){
				testRoyalMatch();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		 t1 = new Thread(){
			public void run(){
				testKingsBounty();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testLuckyLadies();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testBusterTens();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testBusterSubSix();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		for(Thread t : threadSet){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void testRoyalMatch() {
		try {
			File file = new File("royalMatchThreshold.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.29f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
				sbds.add(RoyalMatchDescription.getRoyalMatchDescription());
				Rules rules = new Rules(false, true, true, 1, 0.7f, true, sbds, MIN_BET);
				
				Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
				sideBetStrategies.put(RoyalMatchDescription.getRoyalMatchDescription(), new RoyalMatchSideBetStrategy(25, ROYAL_MATCH_STRAT.TENS, rules.getShoeDeckCount(), threshold));
				StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
				stratCfg.setSideBetStrategy(sideBetStrategies);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(rules, SIMS, HANDS_PER_SIM, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Royal Match, Tens", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Threshold: " + threshold + "\r\n");
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testKingsBounty() {
		try {
			File file = new File("kingsBountyThreshold.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.29f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
				sbds.add(KingsBountyDescription.getKingsBountyDescription());
				Rules rules = new Rules(false, true, true, 1, 0.7f, true, sbds, MIN_BET);
				
				Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
				sideBetStrategies.put(KingsBountyDescription.getKingsBountyDescription(), new KingsBountySideBetStrategy(25, KINGS_BOUNTY_STRAT.TENS, rules.getShoeDeckCount(), threshold));
				StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
				stratCfg.setSideBetStrategy(sideBetStrategies);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(rules, SIMS, HANDS_PER_SIM, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Kings Bounty, Tens", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Threshold: " + threshold + "\r\n");
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testLuckyLadies() {
		try {
			File file = new File("luckyLadiesThreshold.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.29f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
				sbds.add(LuckyLadiesDescription.getLuckyLadiesDescription());
				Rules rules = new Rules(false, true, true, 1, 0.7f, true, sbds, MIN_BET);
				
				Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
				sideBetStrategies.put(LuckyLadiesDescription.getLuckyLadiesDescription(), new LuckyLadiesSideBetStrategy(25, LUCKY_LADIES_STRAT.TENS, rules.getShoeDeckCount(), threshold));
				StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
				stratCfg.setSideBetStrategy(sideBetStrategies);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(rules, SIMS, HANDS_PER_SIM, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Kings Bounty, Tens", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Threshold: " + threshold + "\r\n");
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testBusterTens() {
		try {
			File file = new File("busterTensThreshold.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.29f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
				sbds.add(BusterDescription.getBusterDescription());
				Rules rules = new Rules(false, true, true, 1, 0.7f, true, sbds, MIN_BET);
				
				Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
				sideBetStrategies.put(BusterDescription.getBusterDescription(), new BusterSideBetStrategy(25, BusterSideBetStrategy.BUSTER_STRAT.TENS, rules.getShoeDeckCount(), threshold));
				StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
				stratCfg.setSideBetStrategy(sideBetStrategies);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(rules, SIMS, HANDS_PER_SIM, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Kings Bounty, Tens", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Threshold: " + threshold + "\r\n");
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testBusterSubSix() {
		try {
			File file = new File("busterSubSixThreshold.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.29f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				Set<SideBetDescription> sbds = new HashSet<SideBetDescription>();
				sbds.add(BusterDescription.getBusterDescription());
				Rules rules = new Rules(false, true, true, 1, 0.7f, true, sbds, MIN_BET);
				
				Map<SideBetDescription, SideBetStrategy> sideBetStrategies = new HashMap<SideBetDescription, SideBetStrategy>();
				sideBetStrategies.put(BusterDescription.getBusterDescription(), new BusterSideBetStrategy(25, BusterSideBetStrategy.BUSTER_STRAT.BUSTER_CARDS, rules.getShoeDeckCount(), threshold));
				StrategyConfig stratCfg = new StrategyConfig(true, -2, BASE_BET);
				stratCfg.setSideBetStrategy(sideBetStrategies);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(rules, SIMS, HANDS_PER_SIM, startingCash, stratCfg, Strategy.TYPES.TEXTBOOK, "Textbook Strategy Simulation Kings Bounty, Tens", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Threshold: " + threshold + "\r\n");
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
