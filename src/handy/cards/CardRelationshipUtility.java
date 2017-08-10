package handy.cards;

import java.util.List;

import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;

public class CardRelationshipUtility {
	public static boolean allThreeSevens(List<Card> cards){
		if(cards.size() == 3 && 
				cards.get(0).getBlackjackValue() == 7 &&
				cards.get(1).getBlackjackValue() == 7 &&
				cards.get(2).getBlackjackValue() == 7){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean allOneEachSixSevenEight(List<Card> cards){
		boolean haveSix = false;
		boolean haveSeven = false;
		boolean haveEight = false;
		
		for(Card card : cards){
			if(card.getBlackjackValue() == 6){
				haveSix = true;
			}else if(card.getBlackjackValue() == 7){
				haveSeven = true;
			}else if(card.getBlackjackValue() == 8){
				haveEight = true;
			}
		}
		
		if(cards.size() == 3 && 
				haveSix &&
				haveSeven &&
				haveEight){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean allThreeSameSuite(List<Card> cards){
		if(cards.size() == 3 && 
				cards.get(0).suite == cards.get(1).suite &&
				cards.get(0).suite == cards.get(2).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean allThreeSameSpades(List<Card> cards){
		if(cards.size() == 3 && 
				cards.get(0).suite == SUITE.SPADES &&
				cards.get(1).suite == SUITE.SPADES &&
				cards.get(2).suite == SUITE.SPADES){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean queenOfHeartsPair(List<Card> cards){
		if(cards.size() == 2 &&
				cards.get(0).suite == SUITE.HEART && cards.get(0).value == VALUE.QUEEN &&
				cards.get(1).suite == SUITE.HEART && cards.get(1).value == VALUE.QUEEN){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean matchedPairTens(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).getBlackjackValue() == 10 &&
				cards.get(0).value == cards.get(1).value &&
				cards.get(0).suite == cards.get(1).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean suitedPairTens(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).getBlackjackValue() == 10 &&
				cards.get(1).getBlackjackValue() == 10 &&
				cards.get(0).suite == cards.get(1).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean unsuitedPairTens(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).getBlackjackValue() == 10 &&
				cards.get(1).value == cards.get(0).value){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean kingOfSpadesPair(List<Card> cards){
		if(cards.size() == 2 &&
				cards.get(0).suite == SUITE.SPADES && cards.get(0).value == VALUE.KING &&
				cards.get(1).suite == SUITE.SPADES && cards.get(1).value == VALUE.KING){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean suitedTwenty(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).getBlackjackValue() + cards.get(1).getBlackjackValue() == 20 &&
				cards.get(0).suite == cards.get(1).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean suitedPairKings(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).value == VALUE.KING &&
				cards.get(1).value == VALUE.KING &&
				cards.get(0).suite == cards.get(1).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean suitedPairQueenOrJack(List<Card> cards){
		if(cards.size() == 2 && 
				(cards.get(0).value == VALUE.QUEEN || cards.get(0).value == VALUE.JACK)&&
				(cards.get(1).value == VALUE.QUEEN || cards.get(1).value == VALUE.JACK)&&
				cards.get(0).suite == cards.get(1).suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean unsuitedPairKings(List<Card> cards){
		if(cards.size() == 2 && 
				cards.get(0).value == VALUE.KING &&
				cards.get(1).value == cards.get(0).value){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean kingAndQueen(List<Card> cards){
		if(cards.size() == 2 &&
				(cards.get(0).value == VALUE.KING && cards.get(1).value == VALUE.QUEEN) ||
				(cards.get(0).value == VALUE.QUEEN && cards.get(1).value == VALUE.KING)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isFlush(Card dealerUp, Card playerOne, Card playerTwo){
		if(dealerUp.suite == playerOne.suite &&
				playerOne.suite == playerTwo.suite){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isThreeOfAKind(Card dealerUp, Card playerOne, Card playerTwo){
		if(dealerUp.value == playerOne.value &&
				playerOne.value == playerTwo.value){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isStraight(Card dealerUp, Card playerOne, Card playerTwo){
		if((dealerUp.getBlackjackValue() == playerOne.getBlackjackValue() + 1 &&
				dealerUp.getBlackjackValue() == playerTwo.getBlackjackValue() + 2) ||
				(playerOne.getBlackjackValue() == dealerUp.getBlackjackValue() + 1 &&
				playerOne.getBlackjackValue() == playerTwo.getBlackjackValue() + 2) ||
				(playerTwo.getBlackjackValue() == playerOne.getBlackjackValue() + 1 &&
				playerTwo.getBlackjackValue() == dealerUp.getBlackjackValue() + 2) ||
				(dealerUp.getBlackjackValue() == playerOne.getBlackjackValue() - 1 &&
				dealerUp.getBlackjackValue() == playerTwo.getBlackjackValue() - 2) ||
				(playerOne.getBlackjackValue() == dealerUp.getBlackjackValue() - 1 &&
				playerOne.getBlackjackValue() == playerTwo.getBlackjackValue() - 2) ||
				(playerTwo.getBlackjackValue() == playerOne.getBlackjackValue() - 1 &&
				playerTwo.getBlackjackValue() == dealerUp.getBlackjackValue() - 2)){
			return true;
		}else{
			return false;
		}
	}
}
