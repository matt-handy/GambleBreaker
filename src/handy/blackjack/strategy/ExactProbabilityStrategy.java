package handy.blackjack.strategy;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.sim.Rules.MOVE;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.cards.Card.VALUE;

public class ExactProbabilityStrategy extends Strategy {

	private int total;
	private int aces;
	private int twos;
	private int threes;
	private int fours;
	private int fives;
	private int sixes;
	private int sevens;
	private int eights;
	private int nines;
	private int tens;

	private int deckCount;

	private int bet;
	private int minBet;

	private boolean useProbBetting;
	
	private float testThreshold = -1;
	private int testDealerShow = -1;
	private int testHoldingTotal = -1;

	public ExactProbabilityStrategy(StrategyConfig cfg, Rules rules) {
		this.deckCount = rules.getShoeDeckCount();
		this.bet = cfg.getBaseBet();
		this.minBet = rules.getMinBet();
		
		this.useProbBetting = cfg.useProbBets();
		
		this.testThreshold = cfg.getTestThreshold();
		this.testDealerShow = cfg.getTestDealerShow();
		this.testHoldingTotal = cfg.getTestHoldingTotal();

		resetShoe();
	}

	public void resetShoe() {
		total = 52 * deckCount;
		aces = 4 * deckCount;
		twos = 4 * deckCount;
		threes = 4 * deckCount;
		fours = 4 * deckCount;
		fives = 4 * deckCount;
		sixes = 4 * deckCount;
		sevens = 4 * deckCount;
		eights = 4 * deckCount;
		nines = 4 * deckCount;
		tens = 16 * deckCount;
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
		total--;

		if (card.value == VALUE.ACE) {
			aces--;
		} else if (card.value == VALUE.TWO) {
			twos--;
		} else if (card.value == VALUE.THREE) {
			threes--;
		} else if (card.value == VALUE.FOUR) {
			fours--;
		} else if (card.value == VALUE.FIVE) {
			fives--;
		} else if (card.value == VALUE.SIX) {
			sixes--;
		} else if (card.value == VALUE.SEVEN) {
			sevens--;
		} else if (card.value == VALUE.EIGHT) {
			eights--;
		} else if (card.value == VALUE.NINE) {
			nines--;
		} else if (card.getBlackjackValue() == 10) {
			tens--;
		}
	}

	public void updateStrategyDealtHand(Hand hand) {
		for (Card card : hand.cards) {
			updateStrategyCard(card);
		}
	}

	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		if(hand.getValue() == testHoldingTotal && !hand.isSoft() &&
				dealerCard.getBlackjackValue() == testDealerShow){
			float bustTotal = 0;
			if(testHoldingTotal == 14){
				bustTotal = eights + nines + tens;
			}
			if(testHoldingTotal == 15){
				bustTotal = sevens + eights + nines + tens;
			}
			if(testHoldingTotal == 16){
				bustTotal = sixes + sevens + eights + nines + tens;
			}
			if(rules.canSurrender(hand) && (bustTotal / total) >= testThreshold){
				return MOVE.SURRENDER;
			}
		}

		//Begin "certain" probs
		/*
		if(hand.getValue() == 15 && !hand.isSoft()){
			float bustTotal = sevens + eights + nines + tens;
			if(dealerCard.getBlackjackValue() == 9){
				if(rules.canSurrender(hand) && (bustTotal / total) >= .57){
					return MOVE.SURRENDER;
				}
			}else if(dealerCard.getBlackjackValue() == 10){
				if(rules.canSurrender(hand) && (bustTotal / total) >= .51){
					return MOVE.SURRENDER;
				}
			}else if(dealerCard.getBlackjackValue() == 11){
				if(rules.canSurrender(hand) && (bustTotal / total) >= .55){
					return MOVE.SURRENDER;
				}
			}
		}
		*/
		return TextbookStrategy.determineTextbookMove(rules, hand, dealerCard);
	}

	public int getBet(Shoe shoe, OUTCOME lastOutcome) {
		if (useProbBetting) {
			int delta = (tens + aces) - (twos + threes + fours + fives + sixes);
			delta = (int) Math.floor(((float) delta) / shoe.decksRemaining());
			if(delta <= 0){
				return minBet;
			}else if(delta < 2){
				return bet;
			}else if(delta < 3){
				return bet * 2;
			}else if(delta < 6){
				return bet * ((int) delta);
			}else{
				return bet * 6;
			}
			/*
			if (delta <= 0) {
				return bet;
			} else {
				return delta * bet;
			}
			*/
		} else {
			return bet;
		}

	}

	public boolean walkAway(Shoe shoe) {
		return false;
	}

}
