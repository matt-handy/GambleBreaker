package handy.common21.sim;

import java.util.Set;

import handy.common21.sim.Hand;
import handy.common21.sim.sidebet.SideBetDescription;

public class Rules {
	
	private boolean h17;
	private boolean canDouble;
	private boolean canSurrender;
	private boolean canDoublesplit;
	private int shoeDeckCount;
	private float deckPenetration;
	private int minBet;
	
	private Set<SideBetDescription> sideBets;
	
	public enum MOVE {HIT, STAY, SPLIT, DOUBLE, SURRENDER, DOUBLESPLIT, SPLITACE};
	
	public static final boolean DEBUG = false;
	
	public Rules(boolean h17, boolean canDouble, boolean canSurrender,
			int shoeDeckCount, float deckPenetration, boolean canDoublesplit,
			Set<SideBetDescription> sideBets, int minBet){
		this.h17 = h17;
		this.canDouble = canDouble;
		this.canSurrender= canSurrender;
		this.shoeDeckCount = shoeDeckCount;
		this.deckPenetration = deckPenetration;
		this.canDoublesplit = canDoublesplit;
		this.sideBets = sideBets;
		this.minBet = minBet;
	}
	
	public boolean isH17(){
		return h17;
	}
	
	public boolean canDouble(Hand hand){
		if(canDoublesplit()){
			return hand.cards.size() == 2;
		}else{
			return canDouble && (hand.cards.size() == 2) && !hand.hasBeenSplit();
		}
	}
	
	public boolean canSurrender(Hand hand){
		return canSurrender  && (hand.cards.size() == 2);
	}
	
	public boolean canDoublesplit(){
		return canDoublesplit;
	}
	
	public int getShoeDeckCount(){
		return shoeDeckCount;
	}
	
	public float getDeckPenetration(){
		return deckPenetration;
	}
	
	public Set<SideBetDescription> getSideBets(){
		return sideBets;
	}
	
	public int getMinBet(){
		return minBet;
	}
}
