package handy.blackjack.strategy.sidebet;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class BusterSideBetStrategy extends SideBetStrategy {

	//Misc Caesar's props
	
	public enum BUSTER_STRAT {
		NAIVE, TENS, BUSTER_CARDS
	};

	BUSTER_STRAT strat;

	private int tensLeft;
	private int subSixLeft;
	private int allLeft;
	private int decks;
	private float threshold;

	public BusterSideBetStrategy(int baseBet, BUSTER_STRAT strat, int decks, float requiredTens) {
		super(baseBet);

		this.decks = decks;
		this.threshold = requiredTens;
		this.strat = strat;
		
		resetShoe();
	}

	public void resetShoe() {
		tensLeft = 16 * decks;
		allLeft = 52 * decks;
		subSixLeft = 20 * decks;
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		int idx = 0;
		for (Card card : dealerHand.cards) {
			// We've already seen the first card and put it in strategy
			if (idx != 0) {
				updateStrategyCard(card);
			}
			idx++;
		}
	}

	public void updateStrategyCard(Card card) {
		allLeft--;

		if (card.getBlackjackValue() == 10) {
			tensLeft--;
		}
		if(card.getBlackjackValue() <= 6 || card.getBlackjackValue() == 11){
			subSixLeft--;
		}
	}

	public void updateStrategyDealtHand(Hand hand) {
		for (Card card : hand.cards) {
			updateStrategyCard(card);
		}
	}

	public int getBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet) {
		if (strat == BUSTER_STRAT.NAIVE) {
			return baseBet;
		} else if (strat == BUSTER_STRAT.TENS){
			//Allow users to specify a threshold tens content to test at. None specified,
			//default to 50% of remaining. This bet stays losing until unreasonably high
			//counts
			if (threshold >= 0) {
				if (((float) tensLeft / (float) allLeft) > threshold) {
					return baseBet;
				}
			}else{
				if (((float) tensLeft / (float) allLeft) > .5) {
					return baseBet;
				}
			}
			return 0;
		} else { //Sub Six
			//Same story for sub-six
			if (threshold >= 0) {
				if (((float) subSixLeft / (float) allLeft) > threshold) {
					return baseBet;
				}
			}else{
				if (((float) subSixLeft / (float) allLeft) > .5) {
					return baseBet;
				}
			}
			return 0;
		}
	}

}
