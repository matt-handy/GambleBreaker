package handy.superfun21.strategy;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.cards.Card;
import handy.cards.Shoe;

public class TextbookStrategy extends Strategy {

	private int baseBet;

	public TextbookStrategy(StrategyConfig cfg) {
		baseBet = cfg.getBaseBet();
	}

	public Rules.MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) {
		return determineTextbookMove(rules, hand, dealerCard);
	}

	public static Rules.MOVE determineTextbookMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		//if(rules.getShoeDeckCount() == 2){
			if(hand.canSplit()){
				return determineSplitMove(rules, hand, dealerCard);
			}
			if(hand.cards.size() == 2){
				return determineTwoDeckTwoCardMove(rules, hand, dealerCard);
			}else if(hand.cards.size() == 3){
				if(value == 9){
					if(dealerCard.getBlackjackValue() >= 4 && dealerCard.getBlackjackValue() <= 6){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(value == 10){
					if(dealerCard.getBlackjackValue() >= 10){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if((value == 13 || value == 14) && hand.isSoft()){
					return Rules.MOVE.HIT;
				}else if(value == 15 && hand.isSoft()){
					if(dealerCard.getBlackjackValue() == 6){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(value == 16){
					if(hand.isSoft()){
						if(dealerCard.getBlackjackValue() == 6 ||
								dealerCard.getBlackjackValue() == 5){
							return Rules.MOVE.DOUBLE;
						}else{
							return Rules.MOVE.HIT;
						}
					}else{
						if(dealerCard.getBlackjackValue() >= 9 &&
								rules.canSurrender(hand)){
							return Rules.MOVE.SURRENDER;
						}else if(dealerCard.getBlackjackValue() <= 6){
							return Rules.MOVE.STAY;
						}else{
							return Rules.MOVE.HIT;
						}
					}
				}else if(value == 17 && hand.isSoft()){
					if(dealerCard.getBlackjackValue() == 6 ||
							dealerCard.getBlackjackValue() == 5 ||
							dealerCard.getBlackjackValue() == 4){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(value == 18 && hand.isSoft()){
					if(dealerCard.getBlackjackValue() <= 6 && dealerCard.getBlackjackValue() >= 3){
						return Rules.MOVE.DOUBLE;
					}else if(dealerCard.getBlackjackValue() <= 8 || dealerCard.getBlackjackValue() == 2){
						return Rules.MOVE.STAY;
					}else{
						return Rules.MOVE.HIT;
					}
				}
				return determineTwoDeckTwoCardMove(rules, hand, dealerCard);
			}else if(hand.cards.size() == 4){
				if(value == 9){
					return Rules.MOVE.HIT;
				}else if(value == 10){
					if(dealerCard.getBlackjackValue() >= 3 && dealerCard.getBlackjackValue() <= 6){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(value == 11 || value == 12){
					return Rules.MOVE.HIT;
				}else if(value == 13){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						if(dealerCard.getBlackjackValue() == 5 ||
								dealerCard.getBlackjackValue() == 6){
							return Rules.MOVE.STAY;
						}else{
							return Rules.MOVE.HIT;
						}
					}
				}else if(value == 14){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						if(dealerCard.getBlackjackValue() >= 3 &&
								dealerCard.getBlackjackValue() <= 6){
							return Rules.MOVE.STAY;
						}else{
							return Rules.MOVE.HIT;
						}
					}
				}else if(value == 15 || value == 16){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						if(dealerCard.getBlackjackValue() <= 6){
							return Rules.MOVE.STAY;
						}else{
							return Rules.MOVE.HIT;
						}
					}
				}else if(value == 17 && !hand.isSoft()){
					return Rules.MOVE.HIT;
				}else if(value == 18){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if(value == 19 && hand.isSoft()){
					if(dealerCard.getBlackjackValue() >= 9){
						return Rules.MOVE.HIT;
					}else if(dealerCard.getBlackjackValue() == 6){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.STAY;
					}
				}
				return determineTwoDeckTwoCardMove(rules, hand, dealerCard);
			}else if(hand.cards.size() == 5){
				if(value <= 15){
					return Rules.MOVE.HIT;
				}else if(value == 16){
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
				}else if(value == 17){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						if(dealerCard.getBlackjackValue() <= 7){
							return Rules.MOVE.STAY;
						}else{
							return Rules.MOVE.HIT;
						}
					}
				}else if(value == 18 || value == 19 ||
						value == 20){
					if(hand.isSoft()){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}
				return determineTwoDeckTwoCardMove(rules, hand, dealerCard);
			}else{//6 or more cards in hand
				return Rules.MOVE.STAY;
			}
		//}else{//Assume a 6 deck game
			
		//}
	}
	
	private static Rules.MOVE determineTwoDeckTwoCardMove(Rules rules, Hand hand, Card dealerCard){
		int value = hand.getValue();
		if(value <= 8){
			return Rules.MOVE.HIT;
		}else if(value == 9){
			if(dealerCard.getBlackjackValue() >= 3 && dealerCard.getBlackjackValue() <= 6){
				return Rules.MOVE.DOUBLE;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 10){
			if(dealerCard.getBlackjackValue() == 10){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.DOUBLE;
			}
		}else if(value == 11){
			return Rules.MOVE.DOUBLE;
		}else if(value == 12){
			if(dealerCard.getBlackjackValue() >= 4 && dealerCard.getBlackjackValue() <= 6){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 13){
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
		}else if(value == 14){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6 ||
						dealerCard.getBlackjackValue() == 5){
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
		}else if(value == 15){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6 ||
						dealerCard.getBlackjackValue() == 5){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 11 &&
						rules.canSurrender(hand)){
					return Rules.MOVE.SURRENDER;
				}else if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		}else if(value == 16){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6 ||
						dealerCard.getBlackjackValue() == 5 ||
						dealerCard.getBlackjackValue() == 4){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() >= 10 &&
						rules.canSurrender(hand)){
					return Rules.MOVE.SURRENDER;
				}else if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		}else if(value == 17){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() <= 6 &&
						dealerCard.getBlackjackValue() >= 3){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() < 11 ||
						!rules.canSurrender(hand)){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.SURRENDER;
				}
			}
		}else if(value == 18){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.DOUBLE;
				}else if(dealerCard.getBlackjackValue() <= 8){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else if (value == 19){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.STAY;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else{
			return Rules.MOVE.STAY;
		}
	}

	/*
	 * For this section, inability to DOUBLESPLIT should default to HIT, this is
	 * intentional.
	 */
	public static Rules.MOVE determineSplitMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		if(value == 12 || value == 16){ //A or 8
			return Rules.MOVE.SPLIT;
		}else if(value == 4 ||
				value == 12){//2 or 6
			if(dealerCard.getBlackjackValue() <= 7){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 6){//3
			if(dealerCard.getBlackjackValue() <= 7 && dealerCard.getBlackjackValue() >= 3){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 8){//4
			if(dealerCard.getBlackjackValue() == 5 ||
					dealerCard.getBlackjackValue() == 6){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 10){//5
			if(dealerCard.getBlackjackValue() == 10){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.DOUBLE;
			}
		}else if(value == 14){// 7
			if(dealerCard.getBlackjackValue() <= 8){
				return Rules.MOVE.SPLIT;
			}else{
				return Rules.MOVE.HIT;
			}
		}else if(value == 18){//9
			if(dealerCard.getBlackjackValue() == 10 ||
					dealerCard.getBlackjackValue() == 7){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else{//10s
			return Rules.MOVE.STAY;
		}
	}

	public void resetShoe() {
		// No op
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		// No op
	}

	public int getBet(Shoe shoe, OUTCOME lastOutcome) {
		// Flat betting strategy ignores last hand win
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
