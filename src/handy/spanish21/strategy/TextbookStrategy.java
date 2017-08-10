package handy.spanish21.strategy;

import handy.cards.Card;
import handy.cards.Card.VALUE;
import handy.cards.Shoe;
import handy.common21.sim.Hand;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;
import handy.common21.strategy.StrategyConfig;

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

	public static MOVE determineMove(Rules rules, Hand hand, Card dealerCard) {
		//TODO: 6-7-8 possibly mods
		int value = hand.getValue();
		if (hand.canSplit() && hand.cards.get(0).getBlackjackValue() != 4
				&& hand.cards.get(0).getBlackjackValue() != 5
				&& hand.cards.get(0).getBlackjackValue() != 10) {
			return determineSplitMove(rules, hand, dealerCard);
		} else if (value <= 9) {
			if(!rules.isH17() && dealerCard.getBlackjackValue() == 6){
				if(hand.cards.size() <= 3){ //D4
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else if(dealerCard.getBlackjackValue() == 6){
				return Rules.MOVE.DOUBLE;
			}
			return Rules.MOVE.HIT;
		} else if (value == 10) {
			if (dealerCard.getBlackjackValue() < 9) {
				if(dealerCard.getBlackjackValue() <= 3){
					if(hand.cards.size() <= 4){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 7){
					if(hand.cards.size() <= 3){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 8){
					if(hand.cards.size() <= 2){
						return Rules.MOVE.DOUBLE;
					}else{
						return Rules.MOVE.HIT;
					}
				}else{
					return Rules.MOVE.DOUBLE;
				}
			} else {
				return Rules.MOVE.HIT;
			}
		} else if(value == 11){
			if(dealerCard.getBlackjackValue() >= 10){
				if(hand.cards.size() <= 2){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else if(dealerCard.getBlackjackValue() >= 3 &&
					dealerCard.getBlackjackValue() <= 6){
				if(hand.cards.size() <= 4){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(hand.cards.size() <= 3){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}
			
		} else if(value == 12){
			return Rules.MOVE.HIT;
		} else if(value == 13){
			if(hand.isSoft()){
				return Rules.MOVE.HIT;
			}else{
				if(dealerCard.getBlackjackValue() == 6 &&
						hand.cards.size() <= 3 &&
						rules.isH17()){
					return Rules.MOVE.STAY;
				}else{
					return Rules.MOVE.HIT;
				}
			}
		} else if(value == 14){
			if(!hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 4 &&
						hand.cards.size() <= 4){
					return Rules.MOVE.STAY;
				} else if(dealerCard.getBlackjackValue() == 5 &&
						hand.cards.size() <= 5){
					return Rules.MOVE.STAY;
				} else if(dealerCard.getBlackjackValue() == 6 &&
						hand.cards.size() <= 6){
					return Rules.MOVE.STAY;
				}
			}
			return Rules.MOVE.HIT;
		}else if(value == 15){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 6 && hand.cards.size() <= 3 &&
						rules.isH17()){
					return Rules.MOVE.DOUBLE;
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() >= 7){
					return Rules.MOVE.HIT;
				}else if(dealerCard.getBlackjackValue() == 6){
					if(hand.cards.size() >= 6 && !rules.isH17()){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if (dealerCard.getBlackjackValue() == 5){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if (dealerCard.getBlackjackValue() == 4){
					if((hand.cards.size() >= 6 && rules.isH17()) ||
							(hand.cards.size() >= 5 && !rules.isH17())){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if (dealerCard.getBlackjackValue() == 3){
					if(hand.cards.size() >= 5){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else{
					if(hand.cards.size() >= 4){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}
			}
		}else if(value == 16){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 5 && rules.isH17()){
					if(hand.cards.size() >= 3){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else if(dealerCard.getBlackjackValue() == 6){
					if(hand.cards.size() >= 4){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 11){
					if(hand.cards.size() <= 2 && rules.isH17()){
						return Rules.MOVE.SURRENDER;
					}else{
						return Rules.MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() >= 7){
					return Rules.MOVE.HIT;
				}else if(dealerCard.getBlackjackValue() >= 5){
					return Rules.MOVE.STAY;
				}else{
					if(hand.cards.size() >= 5 && dealerCard.getBlackjackValue() == 2 &&
							!rules.isH17()){
						return Rules.MOVE.HIT;
					}else if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}
			}
		}else if(value == 17){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 4){
					if(hand.cards.size() >= 3){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else if(dealerCard.getBlackjackValue() == 5){
					if(hand.cards.size() >= 4){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else if(dealerCard.getBlackjackValue() == 6){
					if(hand.cards.size() >= 5){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else{
					return Rules.MOVE.HIT;
				}
			}else{
				if(dealerCard.getBlackjackValue() == 11){
					if(hand.cards.size() >= 3){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.SURRENDER;
					}
				}else if(dealerCard.getBlackjackValue() >= 8){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else{
					return Rules.MOVE.STAY;
				}
			}
		}else if(value == 18){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() >= 9){
					return Rules.MOVE.HIT;
				}else if(dealerCard.getBlackjackValue() == 2 ||
						dealerCard.getBlackjackValue() == 3 ||
						dealerCard.getBlackjackValue() == 8){
					if(hand.cards.size() >= 4){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if(dealerCard.getBlackjackValue() == 7){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if(dealerCard.getBlackjackValue() == 6){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else if(dealerCard.getBlackjackValue() == 5){
					if(hand.cards.size() >= 5){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}else{
					if(hand.cards.size() >= 4){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.DOUBLE;
					}
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else if(value == 19){
			if(hand.isSoft()){
				if(dealerCard.getBlackjackValue() == 10){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else if(dealerCard.getBlackjackValue() == 11 &&
						rules.isH17()){
					if(hand.cards.size() >= 6){
						return Rules.MOVE.HIT;
					}else{
						return Rules.MOVE.STAY;
					}
				}else{
					return Rules.MOVE.STAY;
				}
			}else{
				return Rules.MOVE.STAY;
			}
		}else{//20 or more
			return Rules.MOVE.STAY;
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
		if(value == 4 || value == 6){//2 and 3
			if(dealerCard.getBlackjackValue() >= 9){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 12 && hand.cards.get(0).value != VALUE.ACE){//6
			if(dealerCard.getBlackjackValue() < 4 ||
					dealerCard.getBlackjackValue() > 6){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 14){//7
			if(dealerCard.getBlackjackValue() > 7){
				return Rules.MOVE.HIT;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 16){//8
			if(dealerCard.getBlackjackValue() == 11){
				return Rules.MOVE.SURRENDER;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else if(value == 18){//9
			if(dealerCard.getBlackjackValue() == 2 ||
					dealerCard.getBlackjackValue() >= 10){
				return Rules.MOVE.STAY;
			}else{
				return Rules.MOVE.SPLIT;
			}
		}else{//Aces
			if(hand.cards.get(0).value != VALUE.ACE){
				System.out.println("Improper non-ace in final else of determineSplit");
			}
			return Rules.MOVE.SPLIT;
		}
	}

	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		return determineMove(rules, hand, dealerCard);
	}
}
