package handy.blackjack.strategy.sidebet;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.common21.strategy.sidebet.SideBetStrategy;
import handy.cards.Shoe;

public class TwentyOnePlusThreeSideBetStrategy extends SideBetStrategy {

	public enum TWENTY_ONE_PLUS_THREE_STRAT {NAIVE, RATIO};
	TWENTY_ONE_PLUS_THREE_STRAT strat;
	
	int hearts = 0;
	int diamonds = 0;
	int clubs = 0;
	int spades = 0;
	
	public TwentyOnePlusThreeSideBetStrategy(int baseBet, TWENTY_ONE_PLUS_THREE_STRAT strat) {
		super(baseBet);
		this.strat = strat;
	}

	public void resetShoe() {
		hearts = 0;
		diamonds = 0;
		clubs = 0;
		spades = 0;
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		int idx = 0;
		for(Card card : dealerHand.cards){
			//We've already seen the first card and put it in strategy
			if(idx != 0){
				updateStrategyCard(card);
			}
			idx++;
		}
	}

	public void updateStrategyCard(Card card) {
		if(card.suite == SUITE.CLUBS){
			clubs++;
		}else if(card.suite == SUITE.DIAMOND){
			diamonds++;
		}else if(card.suite == SUITE.HEART){
			hearts++;
		}else{
			spades++;
		}
	}

	public void updateStrategyDealtHand(Hand hand) {
		for(Card card : hand.cards){
			updateStrategyCard(card);
		}
	}

	public int getBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet) {
		if(strat == TWENTY_ONE_PLUS_THREE_STRAT.NAIVE){
			return baseBet;
		}else{
			if(shoe.getDeckPenetration() > .2 && bestRatio() <= .1){
				return baseBet;
			}
			return 0;
		}
	}
	
	private float bestRatio(){
		float currentBest = 1;
		float allCards = hearts + diamonds + clubs + spades;
		float diamondRatio = diamonds / allCards;
		float heartRatio = hearts / allCards;
		float spadesRatio = spades / allCards;
		float clubsRatio = clubs / allCards;
		
		if(diamondRatio <= currentBest)
			currentBest = diamondRatio;
		if(heartRatio <= currentBest)
			currentBest = heartRatio;
		if(spadesRatio <= currentBest)
			currentBest = spadesRatio;
		if(clubsRatio <= currentBest)
			currentBest = clubsRatio;
		
		return currentBest;
	}

}
