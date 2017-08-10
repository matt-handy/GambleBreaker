package handy.cards;

import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class SpanishShoeTest extends TestCase {
	public void testSpanishShoe(){
		SpanishShoe ss = new SpanishShoe(8);
		int targetCount = 48 * 8;
		int actualCount = 0;
		while(ss.decksRemaining() > 0){
			Card card = ss.poll();
			actualCount++;
			if(card.value == VALUE.TEN){
				assertTrue(false);//Shouldn't have tens in here
			}
		}
		
		assertEquals(targetCount, actualCount);
	}
}
