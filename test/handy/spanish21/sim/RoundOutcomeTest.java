package handy.spanish21.sim;

import handy.common21.sim.Round.OUTCOME;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class RoundOutcomeTest extends TestCase {
	public static int BASE_BET = 10;
	public static int THREE_SECONDS_WIN = 15;
	public static int DOUBLE_WIN = 20;
	public static int TRIPLE_WIN = 30;
	public static int PUSH_BET = 0;
	
	public static int BIG_BONUS = 5000;
	public static int LESS_BIG_BONUS = 1000;
	
	public void testRoundOutcome(){
		Round round = new Round();
		//20 / 20 push
		Hand dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		Hand playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		HandResult result = new HandResult(playerHand, BASE_BET);
		
		OUTCOME outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PUSH);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == PUSH_BET);
		
		//21 / 21 win
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		playerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		result = new HandResult(playerHand, BASE_BET);
		
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == BASE_BET);
		
		//20 / 21 loss
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		result = new HandResult(playerHand, BASE_BET);
			
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.DEALER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == -BASE_BET);
	}
	
	public void testSevensOutcome(){
		Round round = new Round();
		//3 Spade Sevens, triple win
		Hand dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		Hand playerHand = new Hand(new Card(SUITE.SPADES, VALUE.SEVEN), new Card(SUITE.SPADES, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.SPADES, VALUE.SEVEN));
		HandResult result = new HandResult(playerHand, BASE_BET);
				
		OUTCOME outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == TRIPLE_WIN);
		
		//3 suited Sevens, double win
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.HEART, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.HEART, VALUE.SEVEN));
		result = new HandResult(playerHand, BASE_BET);
						
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == DOUBLE_WIN);
		
		//3  Sevens, 3/2 win
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.HEART, VALUE.SEVEN), new Card(SUITE.SPADES, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.CLUBS, VALUE.SEVEN));
		result = new HandResult(playerHand, BASE_BET);
						
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == THREE_SECONDS_WIN);
	}
	
	public void testSixSevenEightOutcome(){
		Round round = new Round();
		//Spade Six Seven Eight, triple win
		Hand dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		Hand playerHand = new Hand(new Card(SUITE.SPADES, VALUE.SEVEN), new Card(SUITE.SPADES, VALUE.SIX));
		playerHand.hit(new Card(SUITE.SPADES, VALUE.EIGHT));
		HandResult result = new HandResult(playerHand, BASE_BET);
				
		OUTCOME outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == TRIPLE_WIN);
		
		//Suited Six Seven Eight, double win
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.HEART, VALUE.EIGHT), new Card(SUITE.HEART, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.HEART, VALUE.SIX));
		result = new HandResult(playerHand, BASE_BET);
						
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == DOUBLE_WIN);
		
		//Six Seven Eight, 3/2 win
		dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.TEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		playerHand = new Hand(new Card(SUITE.HEART, VALUE.SIX), new Card(SUITE.SPADES, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.CLUBS, VALUE.EIGHT));
		result = new HandResult(playerHand, BASE_BET);
						
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == THREE_SECONDS_WIN);
	}
	
	public void testBonusOutcome(){
		Round round = new Round();
		//bonus on less than $25 bet
		Hand dealerHand = new Hand(new Card(SUITE.CLUBS, VALUE.SEVEN), new Card(SUITE.DIAMOND, VALUE.TEN));
		dealerHand.hit(new Card(SUITE.HEART, VALUE.ACE));
		Hand playerHand = new Hand(new Card(SUITE.SPADES, VALUE.SEVEN), new Card(SUITE.SPADES, VALUE.SEVEN));
		playerHand.hit(new Card(SUITE.SPADES, VALUE.SEVEN));
		HandResult result = new HandResult(playerHand, BASE_BET);
				
		OUTCOME outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == LESS_BIG_BONUS);
		
		
		//bonus on more than $25 bet
		result = new HandResult(playerHand, BASE_BET * 3);
		outcome = round.getOutcome(playerHand, dealerHand);
		assertTrue(outcome == OUTCOME.PLAYER_WIN);
		assertTrue(round.determineOutcome(outcome, result, dealerHand) == BIG_BONUS);
	}
}
