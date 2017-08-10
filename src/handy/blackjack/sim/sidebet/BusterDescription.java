package handy.blackjack.sim.sidebet;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;

public class BusterDescription extends SideBetDescription{

	//Caesar's buster
	private static BusterDescription singleton = null;
	
	private BusterDescription(){
		
	}
	
	public static BusterDescription getBusterDescription(){
		if(singleton == null){
			singleton = new BusterDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		if(dealerHand.isBust()){
			if(dealerHand.cards.size() >= 8){
				return 250 * playerBet;
			}else if(dealerHand.cards.size() == 7){
				return 50 * playerBet;
			}else if(dealerHand.cards.size() == 6){
				return 20 * playerBet;
			}else if(dealerHand.cards.size() == 5){
				return 6 * playerBet;
			}else if(dealerHand.cards.size() == 4){
				return 2 * playerBet;
			}else if(dealerHand.cards.size() == 3){
				return playerBet;
			}
		}
		return -playerBet;
	}

}
