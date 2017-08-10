package handy.cards;

import java.util.HashSet;
import java.util.Set;

import handy.cards.Card.VALUE;

public class SpanishShoe extends Shoe {

	public SpanishShoe(int deckCount) {
		super(deckCount);
		Set<Card> popSet = new HashSet<Card>();
		for(Card card : shoeList){
			if(card.value == VALUE.TEN){
				popSet.add(card);
			}
		}
		
		for(Card card : popSet){
			shoeList.remove(card);
		}
		
		deckSize = 48;
	}

}
