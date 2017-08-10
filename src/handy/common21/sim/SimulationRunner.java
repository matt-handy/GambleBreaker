package handy.common21.sim;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import handy.common21.sim.SimConfig.GAME_TYPE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.stats.SimulationSetResults;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class SimulationRunner {
	private Rules rules;
	private int rounds;
	private int handsPerSim;
	private int startingPlayerCash;
	private StrategyConfig stratCfg;
	private Strategy.TYPES stratType;
	private String simSetName;
	private SimConfig simConfig;

	public SimulationRunner(Rules rules, int rounds, int handsPerSim, int startingPlayerCash, StrategyConfig stratCfg,
			Strategy.TYPES stratType, String simSetName, SimConfig simConfig) {
		this.rules = rules;
		this.rounds = rounds;
		this.handsPerSim = handsPerSim;
		this.startingPlayerCash = startingPlayerCash;
		this.stratCfg = stratCfg;
		this.stratType = stratType;
		this.simSetName = simSetName;
		this.simConfig = simConfig;
	}

	public SimulationSetResults runSimSet() {
		long totalPool = 0;
		int bankrupts = 0;
		int totalTableSwitches = 0;
		Set<Integer> outcomes = new HashSet<Integer>();
		for (int idx = 0; idx < rounds; idx++) {
			Strategy strat = null;

			try {
				strat = getStrategy();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Invalid configuration");
			}

			Player player = null;
			try {
				player = getPlayer(simConfig, strat, startingPlayerCash, stratCfg.getSideBetStrategies());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Wrong configuration specified");
				System.exit(1);
			}
			Simulator sim = new Simulator(rules, player, handsPerSim, simConfig);

			int simTotal = sim.runSim();
			outcomes.add(simTotal);
			totalTableSwitches += sim.getTableSwitches();
			if (simTotal > 0) {
				totalPool += simTotal;
			} else {
				bankrupts++;
			}
		}

		return new SimulationSetResults(totalPool, bankrupts, totalTableSwitches, simSetName, rounds, handsPerSim,
				startingPlayerCash, outcomes);
	}

	private Player getPlayer(SimConfig simConfig, Strategy strat, int startingPlayerCash,
			Map<SideBetDescription, SideBetStrategy> sbs) throws Exception{
		if(simConfig.getGameType() == GAME_TYPE.SUPERFUN21){
			return new handy.superfun21.sim.Player(strat, startingPlayerCash, stratCfg.getSideBetStrategies());
		}else if(simConfig.getGameType() == GAME_TYPE.BJSWITCH){
			return new handy.bjswitch.sim.Player(strat, startingPlayerCash);
		}else if(simConfig.getGameType() == GAME_TYPE.SPANISH21){
			if(!(strat instanceof handy.spanish21.strategy.Strategy)){
				throw new Exception("Spanish21 reqiures Spanish Strategy");
			}
			return new handy.spanish21.sim.Player((handy.spanish21.strategy.Strategy)strat, startingPlayerCash, stratCfg.getSideBetStrategies());
		}else if(simConfig.getGameType() == GAME_TYPE.DOUBLEATTACK){
			if(!(strat instanceof handy.spanish21.strategy.Strategy)){
				throw new Exception("Doubleattack reqiures Spanish Strategy");
			}
			return new handy.doubleattack.sim.Player((handy.spanish21.strategy.Strategy)strat, startingPlayerCash, stratCfg.getSideBetStrategies());	
		}else{//Blackjack and Freebet use default player
			return new Player(strat, startingPlayerCash, stratCfg.getSideBetStrategies());
		}	
	}

	private Strategy getStrategy() throws Exception {
		if (simConfig.getGameType() == GAME_TYPE.BLACKJACK) {
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.blackjack.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.WONG_HALVES) {
				return new handy.blackjack.strategy.WongHalvesCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.blackjack.strategy.TextbookStrategy(stratCfg);
			} else {// Strategy.TYPES.EXACT_PROBABILITY
				return new handy.blackjack.strategy.ExactProbabilityStrategy(stratCfg, rules);
			} // Can't happen, its a closed enum
		} else if (simConfig.getGameType() == GAME_TYPE.SUPERFUN21){
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.superfun21.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.superfun21.strategy.TextbookStrategy(stratCfg);
			} else {
				throw new Exception("Invalid Strategy for Superfun21: " + stratType);
			}
		} else if (simConfig.getGameType() == GAME_TYPE.BJSWITCH){
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.bjswitch.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.bjswitch.strategy.TextbookStrategy(stratCfg);
			} else if (stratType == Strategy.TYPES.WONG_HALVES) {
				return new handy.bjswitch.strategy.WongHalvesCountingStrategy(stratCfg, rules);	
			} else {
				throw new Exception("Invalid Strategy for BJ Switch: " + stratType);
			}
		} else if (simConfig.getGameType() == GAME_TYPE.DOUBLEATTACK){
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.doubleattack.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.doubleattack.strategy.TextbookStrategy(stratCfg);
			} else {
				throw new Exception("Invalid Strategy for BJ Switch: " + stratType);
			}
		} else if (simConfig.getGameType() == GAME_TYPE.SPANISH21){
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.spanish21.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.spanish21.strategy.TextbookStrategy(stratCfg);
			} else {
				throw new Exception("Invalid Strategy for BJ Switch: " + stratType);
			}
		} else {// FREEBET
			if (stratType == Strategy.TYPES.HIGHLOW) {
				return new handy.freebet.strategy.HighLowCountingStrategy(stratCfg, rules);
			} else if (stratType == Strategy.TYPES.TEXTBOOK) {
				return new handy.freebet.strategy.TextbookStrategy(stratCfg);
			} else {
				throw new Exception("Invalid Strategy for Freebet: " + stratType);
			}
		}
	}
}
