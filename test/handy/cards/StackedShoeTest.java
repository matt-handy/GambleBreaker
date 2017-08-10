package handy.cards;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class StackedShoeTest extends TestCase {

	public void testStackedShoe(){
		List<Card> excludedCards = new ArrayList<Card>();
		excludedCards.add(new Card(SUITE.CLUBS, VALUE.EIGHT));
		excludedCards.add(new Card(SUITE.DIAMOND, VALUE.FIVE));
		
		StackedShoe ss = new StackedShoe(1, excludedCards);
		
		int count = 0;
		int eights = 0;
		int fives = 0;
		
		while(ss.decksRemaining() > 0){
			Card card = ss.poll();
			if(card.value == VALUE.EIGHT){
				eights++;
			}
			if(card.value == VALUE.FIVE){
				fives++;
			}
			count++;
		}
		
		assertTrue(count == 50);
		assertTrue(eights == 3);
		assertTrue(fives == 3);
	}
}
