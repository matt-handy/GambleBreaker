package handy.blackjack.strategy;

public class FlatBettingStrategy {
	private int bet;
	
	public FlatBettingStrategy(int bet){
		this.bet = bet;
	}
	
	public int getBet(){
		return bet;
	}
}
