package handy.blackjack.strategy.stackable;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;

public class IllustriousEighteen extends LinearCountStackableStrategy {

	@Override
	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, int trueCount){
		int value = hand.getValue();
		//Account for Index >= 3 for insurance
		if(hand.isSoft()){
			return null;
		}
		if(value == 16 && dealerCard.getBlackjackValue() == 10){
			if(trueCount >= 0){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 15 && dealerCard.getBlackjackValue() == 10){
			if(trueCount >= 4){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 20 && dealerCard.getBlackjackValue() == 5 &&
				hand.canSplit()){
			if(trueCount >= 5){
				return MOVE.SPLIT;
			}else{
				return MOVE.STAY;
			}
		}else if(value == 20 && dealerCard.getBlackjackValue() == 6 &&
				hand.canSplit()){
			if(trueCount >= 4){
				return MOVE.SPLIT;
			}else{
				return MOVE.STAY;
			}
		}else if(value == 10 && dealerCard.getBlackjackValue() == 10 &&
				rules.canDouble(hand)){
			if(trueCount >= 4){
				return MOVE.DOUBLE;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 12 && dealerCard.getBlackjackValue() == 3){
			if(trueCount >= 2){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 12 && dealerCard.getBlackjackValue() == 2){
			if(trueCount >= 3){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 11 && dealerCard.getBlackjackValue() == 11 &&
				rules.canDouble(hand)){
			if(trueCount >= 1){
				return MOVE.DOUBLE;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 9 && dealerCard.getBlackjackValue() == 2 &&
				rules.canDouble(hand)){
			if(trueCount >= 1){
				return MOVE.DOUBLE;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 10 && dealerCard.getBlackjackValue() == 11 &&
				rules.canDouble(hand)){
			if(trueCount >= 4){
				return MOVE.DOUBLE;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 9 && dealerCard.getBlackjackValue() == 7 &&
				rules.canDouble(hand)){
			if(trueCount >= 3){
				return MOVE.DOUBLE;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 16 && dealerCard.getBlackjackValue() == 9){
			if(trueCount >= 5){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 13 && dealerCard.getBlackjackValue() == 2){
			if(trueCount >= -1){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 12 && dealerCard.getBlackjackValue() == 4){
			if(trueCount >= 0){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 12 && dealerCard.getBlackjackValue() == 5){
			if(trueCount >= -1){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 12 && dealerCard.getBlackjackValue() == 6){
			if(trueCount >= 0){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}else if(value == 13 && dealerCard.getBlackjackValue() == 3){
			if(trueCount >= -1){
				return MOVE.STAY;
			}else{
				return MOVE.HIT;
			}
		}
		return null;
	}

}
