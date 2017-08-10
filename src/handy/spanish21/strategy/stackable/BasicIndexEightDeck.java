package handy.spanish21.strategy.stackable;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;
import handy.blackjack.strategy.stackable.LinearCountStackableStrategy;
import handy.cards.Card;

public class BasicIndexEightDeck extends LinearCountStackableStrategy {

	@Override
	public MOVE determineMove(Rules rules, Hand hand, Card dealerCard, int trueCount) {
		int value = hand.getValue();
		// Account for Index >= 3 for insurance
		if (hand.isSoft()) {
			if (value == 13) {
				if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= 2) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 6) {
					if (trueCount >= 1) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (value == 14) {
				if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= 1) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 6) {
					if (trueCount >= -1) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (value == 15) {
				if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= 1) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 6) {
					if (trueCount >= -5) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (value == 16) {
				if (dealerCard.getBlackjackValue() == 4) {
					if (trueCount >= 2) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= -3) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (value == 17) {
				if (dealerCard.getBlackjackValue() == 3) {
					if (trueCount >= 1) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 4) {
					if (trueCount >= -5) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				} else if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= -8) {
						return MOVE.DOUBLE;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (value == 18) {
				if (dealerCard.getBlackjackValue() == 2) {
					if (trueCount >= 2) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 3) {
					if (trueCount >= -2) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 4) {
					if (trueCount >= -6) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= -8) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				}
			} else if (value == 19) {
				if (dealerCard.getBlackjackValue() == 3) {
					if (trueCount >= 5) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 4) {
					if (trueCount >= 3) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= 2) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				}
			} else if (value == 20) {
				if (dealerCard.getBlackjackValue() == 5) {
					if (trueCount >= 4) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				} else if (dealerCard.getBlackjackValue() == 6) {
					if (trueCount >= 4) {
						return MOVE.STAY;
					} else {
						return MOVE.DOUBLE;
					}
				}
			}
			return null;
		}

		if (hand.cards.size() == 2) { // Can late surrender?
			if (hand.getValue() == 16 && hand.canSplit()) {
				if (trueCount >= -4) {
					return MOVE.SURRENDER;
				} else {
					return MOVE.HIT;
				}
			} else if (hand.getValue() == 15){
				if(dealerCard.getBlackjackValue() == 9){
					if (trueCount >= 2) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 10){
					if (trueCount >= 0) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 11){
					if (trueCount >= -1) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}
			} else if (hand.getValue() == 16){
				if(dealerCard.getBlackjackValue() == 9){
					if (trueCount >= 0) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 10){
					if (trueCount >= -2) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}else if(dealerCard.getBlackjackValue() == 11){
					if (trueCount >= -4) {
						return MOVE.SURRENDER;
					} else {
						return MOVE.HIT;
					}
				}
			}
		}

		if (value == 9) {
			if (dealerCard.getBlackjackValue() == 4) {
				if (trueCount >= 0) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 5) {
				if (trueCount >= -2) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 6) {
				if (trueCount >= -4) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 10) {
			if (dealerCard.getBlackjackValue() == 7) {
				if (trueCount >= -6) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 8) {
				if (trueCount >= -2) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 9) {
				if (trueCount >= -2) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 10 || dealerCard.getBlackjackValue() == 11) {
				if (trueCount >= 0) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 11) {
			if (dealerCard.getBlackjackValue() == 10 || dealerCard.getBlackjackValue() == 11) {
				if (trueCount >= -4) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 9) {
				if (trueCount >= -5) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 8) {
				if (trueCount >= -6) {
					return MOVE.DOUBLE;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 12) {
			if (dealerCard.getBlackjackValue() == 5 || dealerCard.getBlackjackValue() == 6) {
				if (trueCount >= 2) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 13) {
			if (dealerCard.getBlackjackValue() == 2) {
				if (trueCount >= 3) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 3) {
				if (trueCount >= 1) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 4) {
				if (trueCount >= 0) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 5) {
				if (trueCount >= -2) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 6) {
				if (trueCount >= -4) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 14) {
			if (dealerCard.getBlackjackValue() == 2) {
				if (trueCount >= -1) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 3) {
				if (trueCount >= -3) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 4) {
				if (trueCount >= -4) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 5) {
				if (trueCount >= -5) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 6) {
				if (trueCount >= -7) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 13) {
			if (dealerCard.getBlackjackValue() == 2) {
				if (trueCount >= -4) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 3) {
				if (trueCount >= -6) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 4) {
				if (trueCount >= -7) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			} else if (dealerCard.getBlackjackValue() == 5) {
				if (trueCount >= -8) {
					return MOVE.STAY;
				} else {
					return MOVE.HIT;
				}
			}
		} else if (value == 12 && dealerCard.getBlackjackValue() == 11) {
			if (trueCount >= -2) {
				return MOVE.STAY;
			} else {
				return MOVE.HIT;
			}
		}
		return null;
	}

}
