package handy.cards;

public class Card {
	public enum SUITE {HEART, DIAMOND, SPADES, CLUBS};
	public enum VALUE {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING};
	
	public SUITE suite;
	public VALUE value;
	
	public Card(SUITE suite, VALUE value){
		this.suite = suite;
		this.value = value;
	}
	
	public int getBlackjackValue(){
		if(value == VALUE.ACE){
			return 11;
		}else if(value == VALUE.TWO){
			return 2;
		}else if(value == VALUE.THREE){
			return 3;
		}else if(value == VALUE.FOUR){
			return 4;
		}else if(value == VALUE.FIVE){
			return 5;
		}else if(value == VALUE.SIX){
			return 6;
		}else if(value == VALUE.SEVEN){
			return 7;
		}else if(value == VALUE.EIGHT){
			return 8;
		}else if(value == VALUE.NINE){
			return 9;
		}else{
			return 10;
		}
	}
	
	public String toString(){
		switch (value){
		case ACE:
			return "ACE";
		case TWO:
			return "TWO";
		case THREE:
			return "THREE";
		case FOUR:
			return "FOUR";
		case FIVE:
			return "FIVE";
		case SIX:
			return "SIX";
		case SEVEN:
			return "SEVEN";
		case EIGHT:
			return "EIGHT";
		case NINE:
			return "NINE";
		case TEN:
			return "TEN";
		case JACK:
			return "JACK";
		case QUEEN:
			return "QUEEN";
		case KING:
			return "KING";
		default:
			return "IMPOSSIBLE!!!";
		}
		
	}
}
