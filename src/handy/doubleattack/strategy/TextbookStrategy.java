package handy.doubleattack.strategy;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.cards.SpanishShoe;
import handy.common21.sim.Hand;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;
import handy.common21.strategy.StrategyConfig;
import handy.spanish21.strategy.Strategy;

public class TextbookStrategy extends Strategy {

	int baseBet;
	
	public TextbookStrategy(StrategyConfig cfg){
		baseBet = cfg.getBaseBet();
	}
	
	public void resetShoe() {
		//No-op, textbook doesn't care
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		//No-op, textbook doesn't care
	}

	public void updateStrategyCard(Card card) {
		//No-op, textbook doesn't care
	}

	public void updateStrategyDealtHand(Hand card) {
		//No-op, textbook doesn't care
	}

	public static MOVE determineTextbookMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) {
		int value = hand.getValue();
		if (hand.canSplit()) {
			return determineSplitMove(rules, hand, dealerCard);
		} else if (value <= 8) {
			return Rules.MOVE.HIT;
		} else if(value == 9){
			if(dealerCard.getBlackjackValue() == 6){
				return Rules.MOVE.DOUBLE;
			}else{
				return Rules.MOVE.HIT;
			}
		} else if(value == 10){
			if(dealerCard.getBlackjackValue() <= 8){
				return Rules.MOVE.DOUBLE;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if (value == 11){
			return Rules.MOVE.DOUBLE;
		} else if(value == 12){
			return Rules.MOVE.HIT;
		} else if(value == 13){
			return Rules.MOVE.HIT;
		} else if(value == 14){
			if(hand.isSoft()){
				return Rules.MOVE.HIT;
			}else{
				if(dealerCard.getBlackjackValue() >= 4 &&
						dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if(value == 15){
			if(hand.isSoft()){
				return Rules.MOVE.HIT;
			}else{
				if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if(value == 16){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if(value == 17){
			if (hand.isSoft()){
				if(dealerCard.getBlackjackValue() >= 4 && dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 11){
					return Rules.MOVE.SURRENDER;
				}else{
					return Rules.MOVE.STAY;
				}
			}
		} else if(value == 18){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() >= 9){
					return Rules.MOVE.HIT;
				}else if(dealerCard.getBlackjackValue() == 5 ||
						dealerCard.getBlackjackValue() == 6){
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
	}

	public boolean forfeit(Hand hand, Card dealerCard, SpanishShoe shoe) {
		if(hand.getValue() == 17 && dealerCard.getBlackjackValue() == 11){
			return true;
		}else{
			return false;
		}
	}

	public int getBet(Shoe shoe, OUTCOME lastOutcome) {
		return baseBet;
	}

	public boolean walkAway(Shoe shoe) {
		return false; // Textbook never does
	}

	public static Rules.MOVE determineSplitMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		
		if(value == 4){//2
			if(dealerCard.getBlackjackValue() < 3 ||
					dealerCard.getBlackjackValue() > 7){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if (value == 6){//3
			if(dealerCard.getBlackjackValue() < 3 ||
					dealerCard.getBlackjackValue() > 8){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 8){//4
			return Rules.MOVE.HIT;
		}else if(value == 10){//5
			if(dealerCard.getBlackjackValue() >= 9){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.DOUBLE;
			}
			
		}else if(value == 12){//6
			if(dealerCard.getBlackjackValue() < 4 &&
					dealerCard.getBlackjackValue() > 6){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 14){//7
			if(dealerCard.getBlackjackValue() > 7){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 16){//8
			return Rules.MOVE.SPLIT;
		}else if(value == 18){//9
			if(dealerCard.getBlackjackValue() <= 3 ||
					dealerCard.getBlackjackValue() == 7 ||
					dealerCard.getBlackjackValue() >= 10){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 20){
			return Rules.MOVE.STAY;
		}else{//Aces
			return Rules.MOVE.SPLIT;
		}
	}

	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		return determineTextbookMove(rules, hand, dealerCard, shoe);
	}
}
