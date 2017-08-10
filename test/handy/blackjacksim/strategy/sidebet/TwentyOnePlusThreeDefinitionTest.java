package handy.blackjacksim.strategy.sidebet;

import handy.blackjack.sim.Hand;
import handy.blackjack.sim.sidebet.TwentyOnePlusThreeDescription;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class TwentyOnePlusThreeDefinitionTest extends TestCase {
	public void testStraight(){
		Hand dealer = new Hand(new Card(SUITE.CLUBS, VALUE.THREE), new Card(SUITE.HEART, VALUE.FIVE));
		Hand playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.FOUR), new Card(SUITE.CLUBS, VALUE.FIVE));
		
		int returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		playerHand.hit(new Card(SUITE.HEART, VALUE.FIVE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		Hand badHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.DIAMOND, VALUE.THREE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, badHand, 20);
		assertTrue(returnSum == -20);
		
		badHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, badHand, 20);
		assertTrue(returnSum == -20);
		
		playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.FIVE), new Card(SUITE.CLUBS, VALUE.FOUR));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
	}
	
	public void testFlush(){
		Hand dealer = new Hand(new Card(SUITE.CLUBS, VALUE.THREE), new Card(SUITE.HEART, VALUE.FIVE));
		Hand playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.NINE), new Card(SUITE.CLUBS, VALUE.SIX));
		
		int returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		playerHand.hit(new Card(SUITE.HEART, VALUE.FIVE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		Hand badHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.DIAMOND, VALUE.THREE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, badHand, 20);
		assertTrue(returnSum == -20);
	}
	
	public void testThreeOfAKind(){
		Hand dealer = new Hand(new Card(SUITE.CLUBS, VALUE.THREE), new Card(SUITE.CLUBS, VALUE.FIVE));
		Hand playerHand = new Hand(new Card(SUITE.HEART, VALUE.THREE), new Card(SUITE.DIAMOND, VALUE.THREE));
		
		int returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		playerHand.hit(new Card(SUITE.CLUBS, VALUE.FIVE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, playerHand, 20);
		assertTrue(returnSum == 20 * 9);
		
		Hand badHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.DIAMOND, VALUE.THREE));
		
		returnSum = TwentyOnePlusThreeDescription.getTwentyOnePlusThreeDescription().determineReturnOnBetFromRound(dealer, badHand, 20);
		assertTrue(returnSum == -20);
	}
}
