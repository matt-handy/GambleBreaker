package handy.blackjack.sim.sidebet;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.cards.CardRelationshipUtility;

public class RoyalMatchDescription extends SideBetDescription {

	//El Cortez rules
	
	private static RoyalMatchDescription singleton = null;
	
	private RoyalMatchDescription(){
		
	}
	
	public static RoyalMatchDescription getRoyalMatchDescription(){
		if(singleton == null){
			singleton = new RoyalMatchDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		if(CardRelationshipUtility.suitedPairTens(playerHand.cards) && CardRelationshipUtility.kingAndQueen(playerHand.cards)){
			return 10 * playerBet;
		}else if(CardRelationshipUtility.suitedPairTens(playerHand.cards)){
			return (int) (3 * playerBet);
		}else{
			return -playerBet;
		}
	}
}
