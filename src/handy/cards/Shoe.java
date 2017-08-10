package handy.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import handy.common21.sim.Rules;

public class Shoe {
	public List<Card> shoeList = new ArrayList<Card>();
	private int deckCount;
	protected int deckSize = 52;
	
	public Shoe(int deckCount){
		for(int idx = 0; idx < deckCount; idx++){
			Deck d = new Deck();
			shoeList.addAll(d.card);	
		}
		Collections.shuffle(shoeList);
		this.deckCount = deckCount;
	}
	
	public Card poll(){
		Card card = shoeList.remove(0);
		if(Rules.DEBUG){
			System.out.println("Off shoe: " + card.suite + " " + card.value);
		}
		return card;
	}
	
	public float getDeckPenetration(){
		return 1 - (((float) shoeList.size()) / ((float)(deckCount * deckSize)));
	}
	
	public float decksRemaining(){
		return shoeList.size() / (float) deckSize;
	}
	
	public int size(){
		return deckCount;
	}
}
