package handy.bjswitch.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;

public class Hand extends handy.blackjack.sim.Hand {

	private boolean hasBeenSwitched = false;
	
	public Hand(Card card, Card card2) {
		super(card, card2);
	}
	
	protected Hand(Card card){
		super(card);
	}

	public static void switchHands(Hand handOne, Hand handTwo){
		Card handOneCardTwo = handOne.cards.remove(1);
		Card handTwoCardTwo = handTwo.cards.remove(1);
		handOne.cards.add(handTwoCardTwo);
		handTwo.cards.add(handOneCardTwo);
		
		handOne.hasBeenSwitched = true;
		handTwo.hasBeenSwitched = true;
		
		handOne.update();
		handTwo.update();
	}
	
	@Override
	public boolean isNatural(){
		if(hasBeenSwitched){
			return false;
		}else{
			return super.isNatural();
		}
	}
	
	public List<Hand> splitSwitchHand(){
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
}
