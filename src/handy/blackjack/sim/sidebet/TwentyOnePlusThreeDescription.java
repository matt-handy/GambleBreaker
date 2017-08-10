package handy.blackjack.sim.sidebet;

import handy.cards.CardRelationshipUtility;
import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;

public class TwentyOnePlusThreeDescription extends SideBetDescription {

	private static TwentyOnePlusThreeDescription singleton = null;
	
	private long totalWin = 0;
	private long totalLoss = 0;
	
	private TwentyOnePlusThreeDescription(){
		
	}
	
	public static TwentyOnePlusThreeDescription getTwentyOnePlusThreeDescription(){
		if(singleton == null){
			singleton = new TwentyOnePlusThreeDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		//Assume 9 to 1 on all
		if(CardRelationshipUtility.isFlush(dealerHand.firstCard(), playerHand.firstCard(), playerHand.cards.get(1)) ||
				CardRelationshipUtility.isStraight(dealerHand.firstCard(), playerHand.firstCard(), playerHand.cards.get(1)) ||
				CardRelationshipUtility.isThreeOfAKind(dealerHand.firstCard(), playerHand.firstCard(), playerHand.cards.get(1))){
			int returnVal = playerBet * 9;
			totalWin += returnVal;
			return returnVal;
		}else{
			int returnVal = -playerBet;
			totalLoss += returnVal;
			return returnVal;
		}
	}

	//Consider punting to SideBetDescription
	public long getTotalWin(){
		return totalWin;
	}
	
	public long getTotalLoss(){
		return -totalLoss;
	}
	
	public void resetStats(){
		totalWin = 0;
		totalLoss = 0;
	}
}
