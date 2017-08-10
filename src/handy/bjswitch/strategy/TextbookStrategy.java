package handy.bjswitch.strategy;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.cards.Card;
import handy.cards.Shoe;

public class TextbookStrategy extends Strategy {

	private int baseBet;

	public TextbookStrategy(StrategyConfig cfg){
		baseBet = cfg.getBaseBet();
	}
	
	public Rules.MOVE determineMove (Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		return determineTextbookMove(rules, hand, dealerCard);
	}

	public static Rules.MOVE determineTextbookMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		if (hand.canSplit() && shouldSplit(hand)) {
			return determineSplitMove(rules, hand, dealerCard);
		} else if (value <= 8) {
			return Rules.MOVE.HIT;
		} else if (value == 9) {
			if(dealerCard.getBlackjackValue() == 6 && rules.canDouble(hand)){
				return Rules.MOVE.DOUBLE;
			}else{
				return Rules.MOVE.HIT;
			}
		} else if (value == 10) {
			if (dealerCard.getBlackjackValue() > 8) {
				return Rules.MOVE.HIT;
			} else {
				if (rules.canDouble(hand)) {
					return Rules.MOVE.DOUBLE;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 11) {
			if (dealerCard.getBlackjackValue() > 9) {
				return Rules.MOVE.HIT;
			} else {
				if (rules.canDouble(hand)) {
					return Rules.MOVE.DOUBLE;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 12) {
			if (dealerCard.getBlackjackValue() < 5 || dealerCard.getBlackjackValue() > 6) {
				return Rules.MOVE.HIT;
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value == 13) {
			if(hand.isSoft()){
				return Rules.MOVE.HIT;
			}else{
				if(dealerCard.getBlackjackValue() > 2 && dealerCard.getBlackjackValue() < 7){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 14 || value == 15) {
			if(hand.isSoft()){
				return Rules.MOVE.HIT;
			}else{
				if(dealerCard.getBlackjackValue() < 7){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 16) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() != 6) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else {
				if(dealerCard.getBlackjackValue() < 7){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 17) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() != 6 &&
						dealerCard.getBlackjackValue() != 5) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value == 18) {
			if (hand.isSoft()) {
				if(dealerCard.getBlackjackValue() > 8){
					return Rules.MOVE.HIT;
				}else if(dealerCard.getBlackjackValue() == 5 ||
						dealerCard.getBlackjackValue() == 6){
					if(rules.canDouble(hand)){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.STAY;
					}
				}else{
					return Rules.MOVE.STAY;
				}
			} else {
				return Rules.MOVE.STAY;
			}
		} else {
			return Rules.MOVE.STAY;
		}
	}

	public static boolean shouldSplit(Hand h) {
		//Note - checking 12 also catches A-A
		if (h.getValue() == 4 || h.getValue() == 6  || h.getValue() == 12 || h.getValue() == 14
				|| h.getValue() == 16 || h.getValue() == 18) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * For this section, inability to DOUBLESPLIT should default to HIT, this is
	 * intentional.
	 */
	public static Rules.MOVE determineSplitMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		if (hand.cards.get(0).value == Card.VALUE.ACE) {
			return Rules.MOVE.SPLIT;
		} else if (value == 4 || value == 6) {
			if (dealerCard.getBlackjackValue() > 7 ||
					dealerCard.getBlackjackValue() < 5) {
				return Rules.MOVE.HIT;
			} else {
				return Rules.MOVE.SPLIT;
			}
		} else if(value == 12){
			if(dealerCard.getBlackjackValue() > 3 &&
					dealerCard.getBlackjackValue() < 7){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		} else if(value == 14){
			if(dealerCard.getBlackjackValue() > 7){
				return Rules.MOVE.HIT;
			}else if(dealerCard.getBlackjackValue() > 2){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.STAY;
			}
		} else if(value == 16){
			if(dealerCard.getBlackjackValue() > 9){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else{ //18, 9-9, only condition remaining
			if(dealerCard.getBlackjackValue() == 4 ||
					dealerCard.getBlackjackValue() == 5 ||
					dealerCard.getBlackjackValue() == 6 ||
					dealerCard.getBlackjackValue() == 8 || 
					dealerCard.getBlackjackValue() == 9){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.STAY;
			}
					
		}
	}

	public void resetShoe() {
		// No op
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		// No op
	}

	public int getBet(Shoe shoe, OUTCOME lastOutcome) {
		//Flat betting strategy ignores last hand win
		return baseBet;
	}

	public void updateStrategyCard(Card card) {
		// No op
	}

	public void updateStrategyDealtHand(Hand card) {
		// No op
	}

	public boolean walkAway(Shoe shoe) {
		return false;
	}

}
