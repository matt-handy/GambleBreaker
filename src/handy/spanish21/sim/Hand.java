package handy.spanish21.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;

public class Hand extends handy.common21.sim.Hand{

	private int split = 0;
	
	public Hand(Card card, Card card2){
		super(card, card2);
	}
	
	private Hand(Card card){
		super(card);
	}
	
	public List<Hand> split(){
		if(cards.size() != 2){
			return null;
		}
		List<Hand> split = new ArrayList<Hand>();
		Hand first = new Hand(cards.get(0));
		first.split = this.split + 1;
		split.add(first);
		Hand second = new Hand(cards.get(1));
		second.split = this.split + 1;
		split.add(second);
		return split;
	}
	
	public boolean canSplit(){
		if(cards.size() == 2 && cards.get(0).getBlackjackValue() == cards.get(1).getBlackjackValue()
				&& this.split < 4){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean hasBeenSplit() {
		return split > 0;
	}
	
		
}
