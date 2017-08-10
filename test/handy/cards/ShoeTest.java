package handy.cards;

import junit.framework.TestCase;

public class ShoeTest extends TestCase {
	public void testRunShoe(){
		Shoe shoe = new Shoe(4);
		while(shoe.shoeList.size() > 0){
			Card card = shoe.poll();
			//System.out.println(card.suite.toString() + ": " + card.value.toString());
		}
	}

	public void testPenetration(){
		Shoe shoe = new Shoe(4);
		assertTrue(shoe.getDeckPenetration() == 0);
		assertTrue(shoe.decksRemaining() == 4);
		
		for(int i = 0; i < 26; i++){
			shoe.poll();
		}
		
		assertTrue(shoe.getDeckPenetration() == 0.125);
		assertTrue(shoe.decksRemaining() == 3.5);
	}
}
