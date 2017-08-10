package handy.blackjack.sim.sets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import handy.common21.sim.SimulationRunner;
import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.Rules;
import handy.common21.sim.SimConfig;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;

public class ProbabilityStrategyTestSet {

	public static void main(String[] args) {
		Set<Thread> threadSet = new HashSet<Thread>();
		Thread t1 = new Thread(){
			public void run(){
				testBaseline();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		 t1 = new Thread(){
			public void run(){
				testBaselineBet();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testFourteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testFifteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testSixteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		/*
		t1 = new Thread(){
			public void run(){
				testTwelve();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testThirteen();
			}
		};
		t1.start();
		threadSet.add(t1);

		t1 = new Thread(){
			public void run(){
				testFourteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testFifteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testSixteen();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testTwelveBuster();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testThirteenBuster();
			}
		};
		t1.start();
		threadSet.add(t1);

		t1 = new Thread(){
			public void run(){
				testFourteenBuster();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testFifteenBuster();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testSixteenBuster();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testTwelveSurrender();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testThirteenSurrender();
			}
		};
		t1.start();
		threadSet.add(t1);

		t1 = new Thread(){
			public void run(){
				testFourteenSurrender();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testFifteenSurrender();
			}
		};
		t1.start();
		threadSet.add(t1);
		
		t1 = new Thread(){
			public void run(){
				testSixteenSurrender();
			}
		};
		t1.start();
		threadSet.add(t1);
		*/
		for(Thread t : threadSet){
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 500;
	public static final Rules PERMISSIVE_RULES = new Rules(false, true, true, 1, 0.7f, true,
			new HashSet<SideBetDescription>(), MIN_BET);
	public static final int SIMS = 30000;
	public static final int HANDS_PER_SIM = 10000;

	public static void testBaseline() {
		try {
			File file = new File("baseline.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Benchmark", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testBaselineBet() {
		try {
			File file = new File("baselineBet.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setProbBets(true);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Bet Test", simConfig);
				SimulationSetResults ssr = sr.runSimSet();
				
				writer.write("Expected pot: " + ssr.getExpectedFinalCashPool() + "\r\n");
				writer.write("Expected hourly: " + ssr.getHourlyReturnAt100GPH() + "\r\n");
				writer.write("Standard Deviation: " + ssr.getStdDevFinalPot() + "\r\n");
				writer.write("Positive percentage: " + ssr.getPercentagePositive() + "\r\n");
				writer.flush();
			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testSixteen() {
		for(int dealerCard = 7; dealerCard <= 11; dealerCard++){
		try {
			File file = new File("sixteen" + dealerCard + ".txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.30f; threshold < .8f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setThresholdExperiment(threshold, dealerCard, 16);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Sixteen Threshold: " + threshold + " for dealer: " + dealerCard, simConfig);
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
	
	public static void testFifteen() {
		for(int dealerCard = 7; dealerCard <= 11; dealerCard++){
		try {
			File file = new File("fifteen" + dealerCard + ".txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.30f; threshold < .8f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setThresholdExperiment(threshold, dealerCard, 15);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fifteen Threshold: " + threshold + " for dealer: " + dealerCard, simConfig);
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
	
	public static void testFourteen() {
		for(int dealerCard = 7; dealerCard <= 11; dealerCard++){
		try {
			File file = new File("fourteen" + dealerCard + ".txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.30f; threshold < .8f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setThresholdExperiment(threshold, dealerCard, 14);
				SimConfig simConfig = new SimConfig(GAME_TYPE.BLACKJACK);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fourteen Threshold: " + threshold + " for dealer: " + dealerCard, simConfig);
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
	/*
	public static void testTwelve() {
		try {
			File file = new File("twelve.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustTwelve(threshold);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Twelve Threshold: " + threshold);
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
	
	public static void testTwelveBuster() {
		try {
			File file = new File("twelveBuster.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustTwelve(threshold);
				stratCfg.setTestBusterHits(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Twelve Threshold: " + threshold);
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
	
	public static void testTwelveSurrender() {
		try {
			File file = new File("twelveSurrender.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustTwelve(threshold);
				stratCfg.setTestSurrender(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Twelve Threshold: " + threshold);
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
	
	public static void testThirteen() {
		try {
			File file = new File("thirteen.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustThirteen(threshold);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Thirteen Threshold: " + threshold);
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
	
	public static void testThirteenBuster() {
		try {
			File file = new File("thirteenBuster.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustThirteen(threshold);
				stratCfg.setTestBusterHits(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Thirteen Threshold: " + threshold);
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
	
	public static void testThirteenSurrender() {
		try {
			File file = new File("thirteenSurrender.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustThirteen(threshold);
				stratCfg.setTestSurrender(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Thirteen Threshold: " + threshold);
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
	
	public static void testFourteen() {
		try {
			File file = new File("fourteen.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFourteen(threshold);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fourteen Threshold: " + threshold);
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
	
	public static void testFourteenBuster() {
		try {
			File file = new File("fourteenBuster.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFourteen(threshold);
				stratCfg.setTestBusterHits(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fourteen Threshold: " + threshold);
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
	
	public static void testFourteenSurrender() {
		try {
			File file = new File("fourteenSurrender.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFourteen(threshold);
				stratCfg.setTestSurrender(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fourteen Threshold: " + threshold);
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
	
	public static void testFifteen() {
		try {
			File file = new File("fifteen.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFifteen(threshold);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fifteen Threshold: " + threshold);
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
	
	public static void testFifteenBuster() {
		try {
			File file = new File("fifteenBuster.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFifteen(threshold);
				stratCfg.setTestBusterHits(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fifteen Threshold: " + threshold);
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
	
	public static void testFifteenSurrender() {
		try {
			File file = new File("fifteenSurrender.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustFifteen(threshold);
				stratCfg.setTestSurrender(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Fifteen Threshold: " + threshold);
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
	
	public static void testSixteen() {
		try {
			File file = new File("sixteen.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustSixteen(threshold);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Sixteen Threshold: " + threshold);
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
	
	public static void testSixteenBuster() {
		try {
			File file = new File("sixteenBuster.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustSixteen(threshold);
				stratCfg.setTestBusterHits(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Sixteen Threshold: " + threshold);
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
	
	public static void testSixteenSurrender() {
		try {
			File file = new File("sixteenSurrender.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);

			for (float threshold = 0.01f; threshold < 1f; threshold += 0.01f) {
				int startingCash = 500000;

				StrategyConfig stratCfg = new StrategyConfig(false, -2, BASE_BET);
				stratCfg.setBustSixteen(threshold);
				stratCfg.setTestSurrender(true);
				SimulationRunner sr = new SimulationRunner(PERMISSIVE_RULES, SIMS, HANDS_PER_SIM, startingCash, stratCfg,
						Strategy.TYPES.EXACT_PROBABILITY,
						"Probability Strategy Simulation Benchmark, One Deck, Sixteen Threshold: " + threshold);
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
	*/
}
