package handy.blackjack.sim.sidebet;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.cards.CardRelationshipUtility;

public class KingsBountyDescription extends SideBetDescription {

	private static KingsBountyDescription singleton = null;
	
	private KingsBountyDescription(){
		
	}
	
	public static KingsBountyDescription getKingsBountyDescription(){
		if(singleton == null){
			singleton = new KingsBountyDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		if(dealerHand.isNatural() && CardRelationshipUtility.kingOfSpadesPair(playerHand.cards)){
			return 500 * playerBet;
		}else if(CardRelationshipUtility.kingOfSpadesPair(playerHand.cards)){
			return 100 * playerBet;
		}else if(CardRelationshipUtility.suitedPairKings(playerHand.cards)){
			return 50 * playerBet;
		}else if(CardRelationshipUtility.suitedPairQueenOrJack(playerHand.cards)){
			return 20 * playerBet;
		}else if(CardRelationshipUtility.suitedTwenty(playerHand.cards)){
			return 8 * playerBet;
		}else if(CardRelationshipUtility.unsuitedPairKings(playerHand.cards)){
			return 5 * playerBet;
		}else if(CardRelationshipUtility.unsuitedPairTens(playerHand.cards)){
			return 4 * playerBet;
		}else{
			return -playerBet;
		}
	}
}
