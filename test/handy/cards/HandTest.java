package handy.cards;

import handy.blackjack.sim.Hand;
import junit.framework.TestCase;

public class HandTest extends TestCase {
	public void testSoftHand(){
		Hand myHand = new Hand(new Card(Card.SUITE.HEART, Card.VALUE.ACE), new Card(Card.SUITE.CLUBS, Card.VALUE.EIGHT));
		assertTrue(myHand.getValue() == 19);
		assertTrue(myHand.isSoft());
		assertFalse(myHand.isBust());
		
		myHand.hit(new Card(Card.SUITE.DIAMOND, Card.VALUE.TEN));
		assertTrue(myHand.getValue() == 19);
		assertFalse(myHand.isSoft());
		assertFalse(myHand.isBust());
		
		myHand.hit(new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));
		assertTrue(myHand.getValue() == 22);
		assertFalse(myHand.isSoft());
		assertTrue(myHand.isBust());
		
		myHand = new Hand(new Card(Card.SUITE.HEART, Card.VALUE.ACE), new Card(Card.SUITE.CLUBS, Card.VALUE.ACE));
		assertTrue(myHand.getValue() == 12);
		assertTrue(myHand.isSoft());
		assertFalse(myHand.isBust());
		myHand.hit(new Card(Card.SUITE.DIAMOND, Card.VALUE.ACE));
		assertTrue(myHand.getValue() == 13);
		assertTrue(myHand.isSoft());
		assertFalse(myHand.isBust());
	}
	
	public void testNatural(){
		Hand myHand = new Hand(new Card(Card.SUITE.HEART, Card.VALUE.ACE), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		assertTrue(myHand.getValue() == 21);
		assertTrue(myHand.isSoft());
		assertFalse(myHand.isBust());
		assertTrue(myHand.isNatural());
		
		myHand = new Hand(new Card(Card.SUITE.HEART, Card.VALUE.ACE), new Card(Card.SUITE.CLUBS, Card.VALUE.SEVEN));
		myHand.hit(new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));
		assertTrue(myHand.getValue() == 21);
		assertTrue(myHand.isSoft());
		assertFalse(myHand.isBust());
		assertFalse(myHand.isNatural());
	}
}
