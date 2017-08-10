package handy.cards;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class CardRelationshipTest extends TestCase {
	public void testIsFlush(){
		Card dC = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		Card pC1 = new Card(SUITE.CLUBS, VALUE.EIGHT);
		Card pC2 = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		
		assertFalse(CardRelationshipUtility.isFlush(dC, pC1, pC2));
		
		pC1 = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		
		assertTrue(CardRelationshipUtility.isFlush(dC, pC1, pC2));
	}
	
	public void testIsThreeOfAKind(){
		Card dC = new Card(SUITE.SPADES, VALUE.EIGHT);
		Card pC1 = new Card(SUITE.CLUBS, VALUE.SEVEN);
		Card pC2 = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		
		assertFalse(CardRelationshipUtility.isThreeOfAKind(dC, pC1, pC2));
		
		pC1 = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		
		assertTrue(CardRelationshipUtility.isThreeOfAKind(dC, pC1, pC2));
	}
	
	public void testStraightThree(){
		Card dC = new Card(SUITE.SPADES, VALUE.EIGHT);
		Card pC1 = new Card(SUITE.CLUBS, VALUE.SEVEN);
		Card pC2 = new Card(SUITE.DIAMOND, VALUE.EIGHT);
		
		assertFalse(CardRelationshipUtility.isStraight(dC, pC1, pC2));
		
		pC2 = new Card(SUITE.DIAMOND, VALUE.SIX);
		
		assertTrue(CardRelationshipUtility.isStraight(dC, pC1, pC2));
	}
	
	public void testSixSevenEight(){
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(SUITE.SPADES, VALUE.EIGHT));
		cards.add(new Card(SUITE.CLUBS, VALUE.SEVEN));
		cards.add(new Card(SUITE.DIAMOND, VALUE.NINE));
		
		assertFalse(CardRelationshipUtility.allOneEachSixSevenEight(cards));
		
		cards.remove(2);
		cards.add(new Card(SUITE.DIAMOND, VALUE.SIX));
		
		assertTrue(CardRelationshipUtility.allOneEachSixSevenEight(cards));
	}
}
