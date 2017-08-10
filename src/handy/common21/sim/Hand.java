package handy.common21.sim;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;

public abstract class Hand {

	public List<Card> cards = new ArrayList<Card>();
	
	protected boolean isSoft;
	protected int total;
	protected Card firstCard;
	protected boolean doubledBet = false;
	
	public Hand(Card card, Card card2){
		this.cards.add(card);
		firstCard = card;
		this.cards.add(card2);
		update();
	}
	
	protected Hand(Card card){
		this.cards.add(card);
		firstCard = card;
		update();
	}
	
	public boolean hasDoubled(){
		return doubledBet;
	}
	
	public void doubleDown(){
		doubledBet = true;
	}
	
	public Card firstCard(){
		return firstCard;
	}
	
	protected void update(){
		int aceCount = 0;
		total = 0;
		isSoft = false;
		for(Card card : cards){
			total += card.getBlackjackValue();
			if(card.value == Card.VALUE.ACE){
				aceCount++;
			}
		}
		
		while(aceCount > 0){
			if(total > 21){
				total -= 10;
			}else{
				isSoft = true;
			}
			aceCount--;
		}
	}
	
	public int getValue(){
		return total;
	}
	
	public boolean isBust(){
		if(getValue() > 21){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isSoft(){
		return isSoft;
	}
	
	public boolean isNatural(){
		if(getValue() == 21 && cards.size() == 2){
			if((cards.get(0).value == Card.VALUE.ACE && (cards.get(1).value == Card.VALUE.TEN || cards.get(1).value == Card.VALUE.JACK || cards.get(1).value == Card.VALUE.QUEEN || cards.get(1).value == Card.VALUE.KING)) ||
					(cards.get(1).value == Card.VALUE.ACE && (cards.get(0).value == Card.VALUE.TEN || cards.get(0).value == Card.VALUE.JACK || cards.get(0).value == Card.VALUE.QUEEN || cards.get(0).value == Card.VALUE.KING))){
				return true;
			}
		}
		return false;
	}
	
	public void hit(Card card){
		cards.add(card);
		update();
	}
	
	public abstract boolean hasBeenSplit();
	
	public abstract boolean canSplit();
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Hand: ");
		for(Card card : cards){
			sb.append(card.value + " ");
		}
		return sb.toString();
	}
}
