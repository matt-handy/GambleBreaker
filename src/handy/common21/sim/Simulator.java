package handy.common21.sim;

import handy.cards.Shoe;
import handy.cards.SpanishShoe;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.sim.SimConfig.GAME_TYPE;

public class Simulator {
	private Rules rules;
	private Player player;
	private SimConfig simConfig;
	private int rounds;
	private int tableSwitches = 0;
	
	public Simulator(Rules rules, Player player, int rounds, SimConfig simConfig){
		this.rules = rules;
		this.player = player;
		this.rounds = rounds;
		this.simConfig = simConfig;
	}
	
	public int runSim(){
		Shoe shoe = getShoe(simConfig);
		int roundCount;
		
		//Player didn't really win, but they feel like a winner starting and will increase bets
		OUTCOME previousOutcome = OUTCOME.PLAYER_WIN;
		
		for(roundCount = 0; roundCount < rounds; roundCount++){
			RoundOutcome outcome = null;
			try {
				outcome = getRound(simConfig).play(shoe, rules, player, previousOutcome);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Improper configuration for specified game type");
				System.exit(0);
			}
			previousOutcome = outcome.getOutcome();
			
			if(outcome.isShouldWalk()){
				tableSwitches++;
				shoe = getShoe(simConfig);
				player.resetStrategyNewShoe();
			}
			
			if(player.dollars <= 0){
				break;
			}
			
			if(shoe.getDeckPenetration() >= rules.getDeckPenetration()){
				shoe = getShoe(simConfig);
				player.resetStrategyNewShoe();
			}
		}
		
		if(roundCount != rounds){
			//System.out.println("You went broke at round: " + roundCount);
		}
		return player.dollars;
	}
	
	public int getTableSwitches(){
		return tableSwitches;
	}
	
	private Round getRound(SimConfig simConfig){
		if(simConfig.getGameType() == GAME_TYPE.BLACKJACK){
			return new handy.blackjack.sim.Round();
		}else if(simConfig.getGameType() == GAME_TYPE.BJSWITCH){
			return new handy.bjswitch.sim.Round();
		}else if(simConfig.getGameType() == GAME_TYPE.SUPERFUN21){
			return new handy.superfun21.sim.Round();
		}else if(simConfig.getGameType() == GAME_TYPE.SPANISH21){
			return new handy.spanish21.sim.Round();
		}else if(simConfig.getGameType() == GAME_TYPE.DOUBLEATTACK){
			return new handy.doubleattack.sim.Round();	
		}else{//Freebet
			return new handy.freebet.sim.Round();
		}
	}
	
	private Shoe getShoe(SimConfig simConfig){
		if(simConfig.getGameType() == GAME_TYPE.DOUBLEATTACK || simConfig.getGameType() == GAME_TYPE.SPANISH21){
			return new SpanishShoe(rules.getShoeDeckCount());
		}else{
			return new Shoe(rules.getShoeDeckCount());
		}
	}
}
