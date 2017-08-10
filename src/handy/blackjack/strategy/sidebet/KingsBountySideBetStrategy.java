package handy.blackjack.strategy.sidebet;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class KingsBountySideBetStrategy extends SideBetStrategy {

	//Misc Caesar's props
	
	public enum KINGS_BOUNTY_STRAT {
		NAIVE, TENS
	};

	KINGS_BOUNTY_STRAT strat;

	private int tensLeft;
	private int allLeft;
	private int decks;
	private float requiredTens;

	public KingsBountySideBetStrategy(int baseBet, KINGS_BOUNTY_STRAT strat, int decks, float requiredTens) {
		super(baseBet);

		this.decks = decks;
		this.requiredTens = requiredTens;
		this.strat = strat;
		
		resetShoe();
	}

	public void resetShoe() {
		tensLeft = 16 * decks;
		allLeft = 52 * decks;
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
	}

	public void updateStrategyDealtHand(Hand hand) {
		for (Card card : hand.cards) {
			updateStrategyCard(card);
		}
	}

	public int getBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet) {
		if (strat == KINGS_BOUNTY_STRAT.NAIVE) {
			return baseBet;
		} else {
			//Allow users to specify a threshold tens content to test at. None specified,
			//default to 50% of remaining. This bet stays losing even at high tens counts
			if (requiredTens >= 0) {
				if (((float) tensLeft / (float) allLeft) > requiredTens) {
					return baseBet;
				}
			}else{
				if (((float) tensLeft / (float) allLeft) > .5) {
					return baseBet;
				}
			}
			return 0;
		}
	}

}
