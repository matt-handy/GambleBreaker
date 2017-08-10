package handy.blackjack.strategy;

import handy.common21.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.StrategyConfig;
import handy.cards.Card;
import handy.cards.Shoe;

public class TextbookStrategy extends Strategy {

	private FlatBettingStrategy fbs;

	public TextbookStrategy(StrategyConfig cfg){
		fbs = new FlatBettingStrategy(cfg.getBaseBet());
	}
	
	public Rules.MOVE determineMove (Rules rules, Hand hand, Card dealerCard, Shoe shoe) throws Exception {
		return determineTextbookMove(rules, hand, dealerCard);
	}

	public static Rules.MOVE determineTextbookMove(Rules rules, Hand hand, Card dealerCard) throws Exception {
		int value = hand.getValue();
		if (hand.canSplit() && shouldSplit(hand)) {
			return determineSplitMove(rules, hand, dealerCard);
		} else if (value <= 8) {
			return Rules.MOVE.HIT;
		} else if (value == 9) {
			if (dealerCard.getBlackjackValue() > 2 && dealerCard.getBlackjackValue() < 7) {
				if (rules.canDouble(hand)) {
					return Rules.MOVE.DOUBLE;
				} else {
					return Rules.MOVE.HIT;
				}
			} else {
				return Rules.MOVE.HIT;
			}
		} else if (value == 10) {
			if (dealerCard.getBlackjackValue() > 9) {
				return Rules.MOVE.HIT;
			} else {
				if (rules.canDouble(hand)) {
					return Rules.MOVE.DOUBLE;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 11) {
			if (rules.isH17()) {
				if (rules.canDouble(hand)) {
					return Rules.MOVE.DOUBLE;
				} else {
					return Rules.MOVE.HIT;
				}
			} else {
				if (dealerCard.value == Card.VALUE.ACE) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			}
		} else if (value == 12) {
			if (dealerCard.getBlackjackValue() < 4 || dealerCard.getBlackjackValue() > 6) {
				return Rules.MOVE.HIT;
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value == 13) {
			if (dealerCard.getBlackjackValue() > 6) {
				return Rules.MOVE.HIT;
			} else if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() <= 4) {
					return Rules.MOVE.HIT;
				} else if (dealerCard.getBlackjackValue() == 5 || dealerCard.getBlackjackValue() == 6) {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				} else {
					throw new Exception("Bad state man!");
				}
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value == 14) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() < 5 || dealerCard.getBlackjackValue() > 6) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else {
				if (dealerCard.getBlackjackValue() > 6) {
					return Rules.MOVE.HIT;
				} else {
					return Rules.MOVE.STAY;
				}
			}
		} else if (value == 15) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() < 4 || dealerCard.getBlackjackValue() > 6) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else {
				if (dealerCard.getBlackjackValue() > 6 && dealerCard.getBlackjackValue() < 10) {
					return Rules.MOVE.HIT;
				} else if (dealerCard.getBlackjackValue() == 10) {
					if (rules.canSurrender(hand)) {
						return Rules.MOVE.SURRENDER;
					} else {
						return Rules.MOVE.HIT;
					}
				} else if (dealerCard.value == Card.VALUE.ACE) {
					if (rules.isH17()) {
						if (rules.canSurrender(hand)) {
							return Rules.MOVE.SURRENDER;
						} else {
							return Rules.MOVE.HIT;
						}
					} else {
						return Rules.MOVE.HIT;
					}
				} else {
					return Rules.MOVE.STAY;
				}
			}
		} else if (value == 16) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() < 4 || dealerCard.getBlackjackValue() > 6) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else {
				if (dealerCard.getBlackjackValue() > 6 && dealerCard.getBlackjackValue() < 9) {
					return Rules.MOVE.HIT;
				} else if (dealerCard.getBlackjackValue() >= 9) {
					if (rules.canSurrender(hand)) {
						return Rules.MOVE.SURRENDER;
					} else {
						return Rules.MOVE.HIT;
					}
				} else {
					return Rules.MOVE.STAY;
				}
			}
		} else if (value == 17) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() < 3 || dealerCard.getBlackjackValue() > 6) {
					return Rules.MOVE.HIT;
				} else {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.HIT;
					}
				}
			} else if (!rules.isH17()) {
				return Rules.MOVE.STAY;
			} else {
				if (dealerCard.getBlackjackValue() <= 10) {
					return Rules.MOVE.STAY;
				} else {
					if (rules.canSurrender(hand)) {
						return Rules.MOVE.SURRENDER;
					} else {
						return Rules.MOVE.STAY;
					}
				}
			}
		} else if (value == 18) {
			if (hand.isSoft()) {
				if (dealerCard.getBlackjackValue() >= 9) {
					return Rules.MOVE.HIT;
				} else if (dealerCard.getBlackjackValue() >= 7) {
					return Rules.MOVE.STAY;
				} else if (dealerCard.getBlackjackValue() >= 3) {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.STAY;
					}
				} else {
					if (rules.isH17()) {
						if (rules.canDouble(hand)) {
							return Rules.MOVE.DOUBLE;
						} else {
							return Rules.MOVE.STAY;
						}
					} else {
						return Rules.MOVE.STAY;
					}
				}
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value == 19) {
			if (hand.isSoft() && rules.isH17()) {
				if (dealerCard.getBlackjackValue() == 6) {
					if (rules.canDouble(hand)) {
						return Rules.MOVE.DOUBLE;
					} else {
						return Rules.MOVE.STAY;
					}
				} else {
					return Rules.MOVE.STAY;
				}
			} else {
				return Rules.MOVE.STAY;
			}
		} else if (value >= 20) {
			return Rules.MOVE.STAY;
		} else {
			throw new Exception("Bad state man!");
		}
	}

	public static boolean shouldSplit(Hand h) {
		if (h.getValue() == 4 || h.getValue() == 6 || h.getValue() == 8 || h.getValue() == 12 || h.getValue() == 14
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
	public static Rules.MOVE determineSplitMove(Rules rules, Hand hand, Card dealerCard) throws Exception {
		int value = hand.getValue();
		if (value == 16) {
			return Rules.MOVE.SPLIT;
		} else if (hand.cards.get(0).value == Card.VALUE.ACE) {
			return Rules.MOVE.SPLITACE;
		} else if (value == 4 || value == 6) {
			if (dealerCard.getBlackjackValue() > 7) {
				return Rules.MOVE.HIT;
			} else if (dealerCard.getBlackjackValue() > 3) {
				return Rules.MOVE.SPLIT;
			} else {
				if (rules.canDoublesplit()) {
					return Rules.MOVE.DOUBLESPLIT;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 8) {
			if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 5) {
				return Rules.MOVE.HIT;
			} else {
				if (rules.canDoublesplit()) {
					return Rules.MOVE.DOUBLESPLIT;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else if (value == 14) {
			if (dealerCard.getBlackjackValue() > 7) {
				return Rules.MOVE.HIT;
			} else {
				return Rules.MOVE.SPLIT;
			}
		} else if (value == 18) {
			if (dealerCard.getBlackjackValue() == 7 || dealerCard.getBlackjackValue() == 10
					|| dealerCard.value == Card.VALUE.ACE) {
				return Rules.MOVE.STAY;
			} else {
				return Rules.MOVE.SPLIT;
			}
		} else if (value == 12) {
			if (dealerCard.getBlackjackValue() > 6) {
				return Rules.MOVE.HIT;
			} else if (dealerCard.getBlackjackValue() > 2) {
				return Rules.MOVE.SPLIT;
			} else {
				if (rules.canDoublesplit()) {
					return Rules.MOVE.DOUBLESPLIT;
				} else {
					return Rules.MOVE.HIT;
				}
			}
		} else {
			throw new Exception("This shouldn't be happening!!!");
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
		return fbs.getBet();
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
