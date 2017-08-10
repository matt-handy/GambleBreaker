package handy.common21.sim;

public class SimConfig {
	public enum GAME_TYPE {BLACKJACK, FREEBET, SUPERFUN21, BJSWITCH, DOUBLEATTACK, SPANISH21}
	
	private GAME_TYPE gameType;
	
	public SimConfig(GAME_TYPE gameType){
		this.gameType = gameType;
	}

	public GAME_TYPE getGameType() {
		return gameType;
	}
	
	
}
