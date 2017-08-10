package handy.doubleattack.strategy;

import java.util.ArrayList;
import java.util.List;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.strategy.StrategyConfig;
import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.spanish21.strategy.LinearCountBettingStrategy;
import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;

public abstract class LinearCountingStrategy extends handy.spanish21.strategy.Strategy {
	protected LinearCountBettingStrategy fbs;
	protected double count = 0;

	protected boolean willWalk;
	protected int walkCountThreshold;
	
	private Rules rules;
	
	private List<LinearCountStackableStrategy> lcss = new ArrayList<LinearCountStackableStrategy>();

	public LinearCountingStrategy(StrategyConfig cfg, Rules rules) {
		fbs = new LinearCountBettingStrategy(cfg.getBaseBet(), cfg.getLinearBettingStrategy(), rules.getMinBet());
		this.willWalk = cfg.isWillWalk();
		this.walkCountThreshold = cfg.getWalkThresholdSingleCount();
		this.rules = rules;
		
		if (cfg.getLinearCountStackableStrategies() != null) {
			this.lcss = cfg.getLinearCountStackableStrategies();
		}
		
		resetShoe();
	}

	public void resetShoe() {
		count = -4 * rules.getShoeDeckCount();
	}

	protected abstract void updateCount(Card card);

	public void updateStrategyDealerHand(Hand dealerHand) {
		int idx = 0;
		for (Card card : dealerHand.cards) {
			// We've already seen the first card and put it in strategy
			if (idx != 0) {
				updateCount(card);
			}
			idx++;
		}
	}

	public void updateStrategyCard(Card card) {
		updateCount(card);
	}

	public void updateStrategyDealtHand(Hand hand) {
		for (Card card : hand.cards) {
			updateCount(card);
		}
	}

	public int getBet(Shoe shoe, OUTCOME lastOutcome) {
		return fbs.getBet(getTrueCount(shoe), lastOutcome);
	}

	public int getAbsoluteCount() {
		return (int) count;
	}

	public int getTrueCount(Shoe shoe) {
		// Make sure we get the floor, not a rounding or truncation
		//int trueCount = (int) Math.floor(count / shoe.decksRemaining());
		int trueCount = (int) (count / shoe.decksRemaining());
		return trueCount;
	}

	public boolean walkAway(Shoe shoe) {
		if (willWalk && getTrueCount(shoe) <= walkCountThreshold) {
			return true;
		} else {
			return false;
		}
	}
	
	public Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) {
		for (LinearCountStackableStrategy lcss : this.lcss) {
			Rules.MOVE move = lcss.determineMove(rules, hand, dealerCard, getTrueCount(shoe));
			if (move != null) {
				return move;
			}
		}
		
		return TextbookStrategy.determineTextbookMove(rules, hand, dealerCard, shoe);
	}
}
