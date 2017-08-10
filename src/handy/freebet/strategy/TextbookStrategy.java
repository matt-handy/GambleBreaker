package handy.freebet.strategy;

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
	
	public Rules.MOVE determineMove (Rules rules, Hand hand, Card dealerCard, Shoe shoe) {
		return determineTextbookMove(rules, hand, dealerCard);
	}

	public static Rules.MOVE determineTextbookMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		handy.freebet.sim.Hand castHand = (handy.freebet.sim.Hand) hand;
		if(!castHand.isFree()){
			if(value == 14){
				if(dealerCard.getBlackjackValue() <= 6 && !hand.isSoft()){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}else if(value == 15){
				if(dealerCard.getBlackjackValue() <= 6 && !hand.isSoft()){
					return Rules.MOVE.STAY;
				}else if(!hand.isSoft() &&  
						dealerCard.getBlackjackValue() >= 10 && rules.canSurrender(hand)){
					return Rules.MOVE.SURRENDER;
				}else{
					return Rules.MOVE.HIT;
				}
			} else if(value == 16){
				if(!hand.isSoft()){
					if(dealerCard.getBlackjackValue() <= 6){
						return Rules.MOVE.STAY;
					}else if(!hand.isSoft() &&  
							dealerCard.getBlackjackValue() >= 9 && rules.canSurrender(hand)){
						return Rules.MOVE.SURRENDER;
					}else{
						return Rules.MOVE.HIT;
					}
				}else{
					if(dealerCard.getBlackjackValue() == 6){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}
			} else if(value == 17){
				if(!hand.isSoft()){
					if(dealerCard.getBlackjackValue() != 11 || !rules.canSurrender(hand)){
						return Rules.MOVE.STAY;
					}else{
						return Rules.MOVE.SURRENDER;
					}
				}else{
					if(dealerCard.getBlackjackValue() == 6 ||
							dealerCard.getBlackjackValue() == 5){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}
			} else if(value == 18){
				if(!hand.isSoft()){
					return Rules.MOVE.STAY;
				}else{
					if(dealerCard.getBlackjackValue() >= 9){
						return Rules.MOVE.HIT;
					}else if(dealerCard.getBlackjackValue() == 6 ||
							dealerCard.getBlackjackValue() == 5){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.STAY;
					}
				}
			} else if(value >= 19 && value <= 21){
				return Rules.MOVE.STAY;
			}
		}
		
		if (hand.canSplit()) {
			return determineSplitMove(rules, hand, dealerCard);
		} else if (value <= 8) {
			return Rules.MOVE.HIT;
		} else if (value >= 9 && value <= 11) {
			return Rules.MOVE.DOUBLE;
		} else if (value == 12) {
			if((dealerCard.getBlackjackValue() == 5 ||
					dealerCard.getBlackjackValue() == 6) && !hand.isSoft()){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.HIT;
			}
		}  else if (value == 13 || value == 14){
			if((dealerCard.getBlackjackValue() >= 3 ||
					dealerCard.getBlackjackValue() <= 6) && !hand.isSoft()){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.HIT;
			}
		} else if(value == 15){
			if(dealerCard.getBlackjackValue() <= 6 && !hand.isSoft()){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.HIT;
			}
		} else if(value == 16){
			if(!hand.isSoft()){
				if(dealerCard.getBlackjackValue() <= 6){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 6){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if(value == 17){
			if(!hand.isSoft()){
				if(dealerCard.getBlackjackValue() <= 6 ||
						dealerCard.getBlackjackValue() == 10){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 6 ||
						dealerCard.getBlackjackValue() == 5){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		}else if(value == 18){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() <= 6 &&
						dealerCard.getBlackjackValue() >= 4){
					return Rules.MOVE.DOUBLE;
				} else if(dealerCard.getBlackjackValue() == 7){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else if(value == 19){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6 ||
						dealerCard.getBlackjackValue() == 5){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else if(value == 20){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else{//Just 21
			return Rules.MOVE.STAY;
		}
		
	}

	/*
	 * For this section, inability to DOUBLESPLIT should default to HIT, this is
	 * intentional.
	 */
	public static Rules.MOVE determineSplitMove(Rules rules, Hand hand, Card dealerCard) {
		int value = hand.getValue();
		if(value == 20){
			return Rules.MOVE.STAY;
		}else if(value == 10){ //Fives
			return Rules.MOVE.DOUBLE;
		}else{
			return Rules.MOVE.SPLIT;
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
