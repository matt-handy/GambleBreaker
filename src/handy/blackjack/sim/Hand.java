package handy.blackjack.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;

public class Hand  extends handy.common21.sim.Hand {

	protected boolean split = false;
	
	public Hand(Card card, Card card2){
		super(card, card2);
	}
	
	public Hand(Card card){
		super(card);
	}
	
	public List<Hand> split(){
		if(cards.size() != 2){
			return null;
		}
		List<Hand> split = new ArrayList<Hand>();
		Hand first = new Hand(cards.get(0));
		first.split = true;
		split.add(first);
		Hand second = new Hand(cards.get(1));
		second.split = true;
		split.add(second);
		return split;
	}
	
	public boolean canSplit(){
		if(cards.size() == 2 && cards.get(0).getBlackjackValue() == cards.get(1).getBlackjackValue()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean hasBeenSplit(){
		return split;
	}
}
