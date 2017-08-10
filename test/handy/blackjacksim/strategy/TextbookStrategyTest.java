package handy.blackjacksim.strategy;

import java.util.HashSet;

import handy.blackjack.sim.Hand;
import handy.common21.sim.Rules;
import handy.common21.sim.Rules.MOVE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.StrategyConfig;
import handy.blackjack.strategy.TextbookStrategy;
import handy.cards.Card;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class TextbookStrategyTest extends TestCase {

	public static final int MIN_BET = 10;
	public static final Rules PERMISSIVE_RULES = new Rules(false, true, true, 6, 0.8f, true, new HashSet<SideBetDescription>(), MIN_BET);
	public static final Rules PERMISSIVE_RULES_NO_DOUBLEPLSPLIT = new Rules(false, true, true, 6, 0.8f, false, new HashSet<SideBetDescription>(), MIN_BET);
	public static final TextbookStrategy TBS = new TextbookStrategy(new StrategyConfig(false, -5, 5));

	public void testS17PlayerHard5() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TWO),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);
				assertTrue(move == MOVE.HIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard6() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TWO),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FOUR));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);
				assertTrue(move == MOVE.HIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard7() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.THREE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FOUR));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);
				assertTrue(move == MOVE.HIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard8() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.THREE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FIVE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);
				assertTrue(move == MOVE.HIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard9() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FOUR),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FIVE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() == 2 || dealerCard.getBlackjackValue() > 6) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard10() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FOUR),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SIX));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 9) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard11() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FOUR),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 10) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard12() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FIVE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.STAY);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard13and14() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.SIX),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.STAY);
				}

			}

			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.SIX), new Card(Card.SUITE.DIAMOND, Card.VALUE.EIGHT));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.STAY);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard15() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.EIGHT),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 7) {
					assertTrue(move == MOVE.STAY);
				} else if (dealerCard.value == VALUE.SEVEN || dealerCard.value == VALUE.EIGHT
						|| dealerCard.value == VALUE.NINE || dealerCard.value == VALUE.ACE) {
					assertTrue(move == MOVE.HIT);
				} else if (dealerCard.value == VALUE.TEN || dealerCard.value == VALUE.JACK
						|| dealerCard.value == VALUE.QUEEN || dealerCard.value == VALUE.KING) {
					assertTrue(move == MOVE.SURRENDER);
				} else {
					assertTrue(false);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard16() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				// System.out.println("DealerCard: " + value + ", MOVE: " +
				// move);

				if (dealerCard.getBlackjackValue() < 7) {
					assertTrue(move == MOVE.STAY);
				} else if (dealerCard.value == VALUE.SEVEN || dealerCard.value == VALUE.EIGHT) {
					assertTrue(move == MOVE.HIT);
				} else if (dealerCard.getBlackjackValue() > 8) {
					assertTrue(move == MOVE.SURRENDER);
				} else {
					assertTrue(false);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerHard17Plus() {
		Card.VALUE testVals[] = { VALUE.SEVEN, VALUE.EIGHT, VALUE.NINE, VALUE.TEN, VALUE.ACE };

		try {
			for (VALUE val : testVals) {
				Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.JACK),
						new Card(Card.SUITE.DIAMOND, val));

				for (Card.VALUE value : Card.VALUE.values()) {
					Card dealerCard = new Card(Card.SUITE.CLUBS, value);
					MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

					assertTrue(move == MOVE.STAY);
				}
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testS17PlayerSoft13and14() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.TWO));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 5) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}

			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE), new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 5) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSoft15and16() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FOUR));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}

			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE), new Card(Card.SUITE.DIAMOND, Card.VALUE.FIVE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSoft17() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SIX));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				//System.out.println("DealerCard: " + value + ", MOVE: " + move);
			
				if (dealerCard.getBlackjackValue() > 6 || dealerCard.getBlackjackValue() < 3) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLE);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSoft18() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 7 && dealerCard.getBlackjackValue() > 2) {
					assertTrue(move == MOVE.DOUBLE);
				} else if (value == VALUE.TWO ||
						value == VALUE.SEVEN ||
						value == VALUE.EIGHT){
					assertTrue(move == MOVE.STAY);
				} else if (dealerCard.getBlackjackValue() > 8){
					assertTrue(move == MOVE.HIT);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSoft19Plus() {
		Card.VALUE testVals[] = { VALUE.EIGHT, VALUE.NINE, VALUE.TEN};

		try {
			for (VALUE val : testVals) {
				Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
						new Card(Card.SUITE.DIAMOND, val));

				for (Card.VALUE value : Card.VALUE.values()) {
					Card dealerCard = new Card(Card.SUITE.CLUBS, value);
					MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

					assertTrue(move == MOVE.STAY);
				}
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit2and3() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TWO),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.TWO));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.DOUBLESPLIT);
				} else if (dealerCard.getBlackjackValue() < 8){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}

			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.THREE), new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.DOUBLESPLIT);
				} else if (dealerCard.getBlackjackValue() < 8){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit2and3NoDoubleSplit() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TWO),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.TWO));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES_NO_DOUBLEPLSPLIT, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.HIT);
				} else if (dealerCard.getBlackjackValue() < 8){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}

			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.THREE), new Card(Card.SUITE.DIAMOND, Card.VALUE.THREE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES_NO_DOUBLEPLSPLIT, hand, dealerCard, null);

				//System.out.println("DealerCard: " + value + ", MOVE: " + move);
				
				if (dealerCard.getBlackjackValue() < 4) {
					assertTrue(move == MOVE.HIT);
				} else if (dealerCard.getBlackjackValue() < 8){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit4() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FOUR),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FOUR));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 5 || dealerCard.getBlackjackValue() > 6) {
					assertTrue(move == MOVE.HIT);
				} else {
					assertTrue(move == MOVE.DOUBLESPLIT);
				}

			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit4NoDoubleSplit() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FOUR),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FOUR));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES_NO_DOUBLEPLSPLIT, hand, dealerCard, null);

				assertTrue(move == MOVE.HIT);
				
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit5() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.FIVE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.FIVE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				assertTrue(move != MOVE.DOUBLESPLIT);
				assertTrue(move != MOVE.SPLIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit6() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.SIX),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SIX));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 3) {
					assertTrue(move == MOVE.DOUBLESPLIT);
				} else if (dealerCard.getBlackjackValue() < 7){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit6NoDoubleSplit() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.SIX),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SIX));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES_NO_DOUBLEPLSPLIT, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 3) {
					assertTrue(move == MOVE.HIT);
				} else if (dealerCard.getBlackjackValue() < 7){
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit7() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.SEVEN),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.SEVEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.getBlackjackValue() < 8) {
					assertTrue(move == MOVE.SPLIT);
				} else {
					assertTrue(move == MOVE.HIT);
				}

			}

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit8andAce() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.EIGHT),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.EIGHT));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				assertTrue(move == MOVE.SPLIT);
			}
			
			hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.ACE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.ACE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				assertTrue(move == MOVE.SPLITACE);
			}

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit9() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.NINE),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.NINE));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				if (dealerCard.value == VALUE.SEVEN ||
						dealerCard.getBlackjackValue() > 9) {
					assertTrue(move == MOVE.STAY);
				} else {
					assertTrue(move == MOVE.SPLIT);
				}

			}

		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	public void testS17PlayerSplit10() {
		try {
			Hand hand = new Hand(new Card(Card.SUITE.CLUBS, Card.VALUE.TEN),
					new Card(Card.SUITE.DIAMOND, Card.VALUE.TEN));

			for (Card.VALUE value : Card.VALUE.values()) {
				Card dealerCard = new Card(Card.SUITE.CLUBS, value);
				MOVE move = TBS.determineMove(PERMISSIVE_RULES, hand, dealerCard, null);

				assertTrue(move != MOVE.DOUBLESPLIT);
				assertTrue(move != MOVE.SPLIT);
			}
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
