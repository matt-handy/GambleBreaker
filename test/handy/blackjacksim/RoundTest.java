package handy.blackjacksim;

import handy.blackjack.sim.Hand;
import handy.blackjack.sim.Round;
import handy.cards.Card;
import junit.framework.TestCase;

public class RoundTest extends TestCase {
	public void testRound(){
		Hand playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		Hand dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
	
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.PLAYER_BLACKJACK);
		
		dealerHand.hit(new Card(Card.SUITE.DIAMOND, Card.VALUE.ACE));
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.PLAYER_BLACKJACK);
	
		playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.QUEEN), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
		
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.PUSH);
		
		playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
		
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.DEALER_WIN);
		
		playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
		
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.PLAYER_WIN);
		
		playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		playerHand.hit(new Card(Card.SUITE.SPADES, Card.VALUE.FIVE));
		dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
		
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.DEALER_WIN);
		
		playerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN), new Card(Card.SUITE.CLUBS, Card.VALUE.TEN));
		dealerHand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE), new Card(Card.SUITE.SPADES, Card.VALUE.TEN));
		dealerHand.hit(new Card(Card.SUITE.SPADES, Card.VALUE.FIVE));
		
		assertTrue(Round.getVanillaBlackjackOutcome(playerHand, dealerHand) == Round.OUTCOME.PLAYER_WIN);
	}
}
