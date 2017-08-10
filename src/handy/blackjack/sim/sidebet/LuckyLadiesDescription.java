package handy.blackjack.sim.sidebet;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.cards.CardRelationshipUtility;

public class LuckyLadiesDescription extends SideBetDescription {

	private static LuckyLadiesDescription singleton = null;
	
	private LuckyLadiesDescription(){
		
	}
	
	public static LuckyLadiesDescription getLuckyLadiesDescription(){
		if(singleton == null){
			singleton = new LuckyLadiesDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		if(dealerHand.isNatural() && CardRelationshipUtility.queenOfHeartsPair(playerHand.cards)){
			return 1000 * playerBet;
		}else if(CardRelationshipUtility.queenOfHeartsPair(playerHand.cards)){
			return 200 * playerBet;
		}else if(CardRelationshipUtility.matchedPairTens(playerHand.cards)){
			return 25 * playerBet;
		}else if(CardRelationshipUtility.suitedPairTens(playerHand.cards)){
			return 10 * playerBet;
		}else if(CardRelationshipUtility.unsuitedPairTens(playerHand.cards)){
			return 4 * playerBet;
		}else{
			return -playerBet;
		}
	}
}
