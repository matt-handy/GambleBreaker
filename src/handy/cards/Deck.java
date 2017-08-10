package handy.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	public List<Card> card = new ArrayList<Card>();
	
	public Deck(){
		for(Card.SUITE suite : Card.SUITE.values()){
			for(Card.VALUE value : Card.VALUE.values()){
				card.add(new Card(suite, value));
			}
		}
		Collections.shuffle(card);
	}
}
