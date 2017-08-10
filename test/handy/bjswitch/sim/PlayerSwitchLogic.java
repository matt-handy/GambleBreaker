package handy.bjswitch.sim;

import handy.bjswitch.sim.Hand;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import junit.framework.TestCase;

public class PlayerSwitchLogic extends TestCase {
	final Card ACE = new Card(SUITE.CLUBS, VALUE.ACE);
	final Card KING = new Card(SUITE.CLUBS, VALUE.KING);
	final Card QUEEN = new Card(SUITE.CLUBS, VALUE.QUEEN);
	final Card JACK = new Card(SUITE.CLUBS, VALUE.JACK);
	final Card TEN = new Card(SUITE.CLUBS, VALUE.TEN);
	final Card NINE = new Card(SUITE.CLUBS, VALUE.NINE);
	final Card EIGHT = new Card(SUITE.CLUBS, VALUE.EIGHT);
	final Card SEVEN = new Card(SUITE.CLUBS, VALUE.SEVEN);
	final Card SIX = new Card(SUITE.CLUBS, VALUE.SIX);
	final Card FIVE = new Card(SUITE.CLUBS, VALUE.FIVE);
	final Card FOUR = new Card(SUITE.CLUBS, VALUE.FOUR);
	final Card THREE = new Card(SUITE.CLUBS, VALUE.THREE);
	final Card TWO = new Card(SUITE.CLUBS, VALUE.TWO);
	
	final Hand sTwentyOne = new Hand(ACE, KING);
	final Hand sTwenty = new Hand(ACE, NINE);
	final Hand sNineteen = new Hand(ACE, EIGHT);
	final Hand sEighteen = new Hand(ACE, SEVEN);
	final Hand sSeventeen = new Hand(ACE, SIX);
	final Hand sSixteen = new Hand(ACE, FIVE);
	final Hand sFifteen = new Hand(ACE, FOUR);
	final Hand sFourteen = new Hand(ACE, THREE);
	final Hand sThirteen = new Hand(ACE, TWO);
	
	final Hand seventeen = new Hand(TEN, SEVEN);
	final Hand sixteen = new Hand(TEN, SIX);
	final Hand fifteen = new Hand(TEN, FIVE);
	final Hand fourteen = new Hand(TEN, FOUR);
	final Hand thirteen = new Hand(TEN, THREE);
	final Hand twelve = new Hand(TEN, TWO);
	final Hand eleven = new Hand(NINE, TWO);
	final Hand ten = new Hand(EIGHT, TWO);
	final Hand nine = new Hand(SEVEN, TWO);
	final Hand eight = new Hand(SIX, TWO);
	final Hand seven = new Hand(FIVE, TWO);
	
	final Hand aceSpl = new Hand(ACE, ACE);
	final Hand nineSpl = new Hand(NINE, NINE);
	final Hand eightSpl = new Hand(EIGHT, EIGHT);
	final Hand sevenSpl = new Hand(SEVEN, SEVEN);
	final Hand sixSpl = new Hand(SIX, SIX);
	final Hand threeSpl = new Hand(THREE, THREE);
	final Hand twoSpl = new Hand(TWO, TWO);
	
	public void testPlayerHandWeight(){
		assertTrue(Player.getPointValue(sTwentyOne, ACE) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, KING) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, QUEEN) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, JACK) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, TEN) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, NINE) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, EIGHT) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, SEVEN) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, SIX) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, FIVE) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, FOUR) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, THREE) == 3);
		assertTrue(Player.getPointValue(sTwentyOne, TWO) == 3);
		
		assertTrue(Player.getPointValue(sTwenty, ACE) == 2);
		assertTrue(Player.getPointValue(sTwenty, KING) == 2);
		assertTrue(Player.getPointValue(sTwenty, QUEEN) == 2);
		assertTrue(Player.getPointValue(sTwenty, JACK) == 2);
		assertTrue(Player.getPointValue(sTwenty, TEN) == 2);
		assertTrue(Player.getPointValue(sTwenty, NINE) == 3);
		assertTrue(Player.getPointValue(sTwenty, EIGHT) == 3);
		assertTrue(Player.getPointValue(sTwenty, SEVEN) == 3);
		assertTrue(Player.getPointValue(sTwenty, SIX) == 2);
		assertTrue(Player.getPointValue(sTwenty, FIVE) == 2);
		assertTrue(Player.getPointValue(sTwenty, FOUR) == 2);
		assertTrue(Player.getPointValue(sTwenty, THREE) == 2);
		assertTrue(Player.getPointValue(sTwenty, TWO) == 2);
		
		assertTrue(Player.getPointValue(sNineteen, ACE) == 1);
		assertTrue(Player.getPointValue(sNineteen, KING) == 1);
		assertTrue(Player.getPointValue(sNineteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sNineteen, JACK) == 1);
		assertTrue(Player.getPointValue(sNineteen, TEN) == 1);
		assertTrue(Player.getPointValue(sNineteen, NINE) == 2);
		assertTrue(Player.getPointValue(sNineteen, EIGHT) == 3);
		assertTrue(Player.getPointValue(sNineteen, SEVEN) == 3);
		assertTrue(Player.getPointValue(sNineteen, SIX) == 1);
		assertTrue(Player.getPointValue(sNineteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sNineteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sNineteen, THREE) == 1);
		assertTrue(Player.getPointValue(sNineteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(sEighteen, ACE) == 1);
		assertTrue(Player.getPointValue(sEighteen, KING) == 1);
		assertTrue(Player.getPointValue(sEighteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sEighteen, JACK) == 1);
		assertTrue(Player.getPointValue(sEighteen, TEN) == 1);
		assertTrue(Player.getPointValue(sEighteen, NINE) == 1);
		assertTrue(Player.getPointValue(sEighteen, EIGHT) == 2);
		assertTrue(Player.getPointValue(sEighteen, SEVEN) == 2);
		assertTrue(Player.getPointValue(sEighteen, SIX) == 1);
		assertTrue(Player.getPointValue(sEighteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sEighteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sEighteen, THREE) == 1);
		assertTrue(Player.getPointValue(sEighteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(seventeen, ACE) == 0);
		assertTrue(Player.getPointValue(seventeen, KING) == 0);
		assertTrue(Player.getPointValue(seventeen, QUEEN) == 0);
		assertTrue(Player.getPointValue(seventeen, JACK) == 0);
		assertTrue(Player.getPointValue(seventeen, TEN) == 0);
		assertTrue(Player.getPointValue(seventeen, NINE) == 0);
		assertTrue(Player.getPointValue(seventeen, EIGHT) == 0);
		assertTrue(Player.getPointValue(seventeen, SEVEN) == 0);
		assertTrue(Player.getPointValue(seventeen, SIX) == 0);
		assertTrue(Player.getPointValue(seventeen, FIVE) == 0);
		assertTrue(Player.getPointValue(seventeen, FOUR) == 0);
		assertTrue(Player.getPointValue(seventeen, THREE) == 0);
		assertTrue(Player.getPointValue(seventeen, TWO) == 0);
		
		assertTrue(Player.getPointValue(sixteen, ACE) == 0);
		assertTrue(Player.getPointValue(sixteen, KING) == 0);
		assertTrue(Player.getPointValue(sixteen, QUEEN) == 0);
		assertTrue(Player.getPointValue(sixteen, JACK) == 0);
		assertTrue(Player.getPointValue(sixteen, TEN) == 0);
		assertTrue(Player.getPointValue(sixteen, NINE) == 0);
		assertTrue(Player.getPointValue(sixteen, EIGHT) == 0);
		assertTrue(Player.getPointValue(sixteen, SEVEN) == 0);
		assertTrue(Player.getPointValue(sixteen, SIX) == 0);
		assertTrue(Player.getPointValue(sixteen, FIVE) == 0);
		assertTrue(Player.getPointValue(sixteen, FOUR) == 0);
		assertTrue(Player.getPointValue(sixteen, THREE) == 0);
		assertTrue(Player.getPointValue(sixteen, TWO) == 0);
		
		assertTrue(Player.getPointValue(fifteen, ACE) == 0);
		assertTrue(Player.getPointValue(fifteen, KING) == 0);
		assertTrue(Player.getPointValue(fifteen, QUEEN) == 0);
		assertTrue(Player.getPointValue(fifteen, JACK) == 0);
		assertTrue(Player.getPointValue(fifteen, TEN) == 0);
		assertTrue(Player.getPointValue(fifteen, NINE) == 0);
		assertTrue(Player.getPointValue(fifteen, EIGHT) == 0);
		assertTrue(Player.getPointValue(fifteen, SEVEN) == 0);
		assertTrue(Player.getPointValue(fifteen, SIX) == 0);
		assertTrue(Player.getPointValue(fifteen, FIVE) == 0);
		assertTrue(Player.getPointValue(fifteen, FOUR) == 0);
		assertTrue(Player.getPointValue(fifteen, THREE) == 0);
		assertTrue(Player.getPointValue(fifteen, TWO) == 0);
		
		assertTrue(Player.getPointValue(fourteen, ACE) == 0);
		assertTrue(Player.getPointValue(fourteen, KING) == 0);
		assertTrue(Player.getPointValue(fourteen, QUEEN) == 0);
		assertTrue(Player.getPointValue(fourteen, JACK) == 0);
		assertTrue(Player.getPointValue(fourteen, TEN) == 0);
		assertTrue(Player.getPointValue(fourteen, NINE) == 0);
		assertTrue(Player.getPointValue(fourteen, EIGHT) == 0);
		assertTrue(Player.getPointValue(fourteen, SEVEN) == 0);
		assertTrue(Player.getPointValue(fourteen, SIX) == 0);
		assertTrue(Player.getPointValue(fourteen, FIVE) == 0);
		assertTrue(Player.getPointValue(fourteen, FOUR) == 0);
		assertTrue(Player.getPointValue(fourteen, THREE) == 0);
		assertTrue(Player.getPointValue(fourteen, TWO) == 0);
		
		assertTrue(Player.getPointValue(thirteen, ACE) == 0);
		assertTrue(Player.getPointValue(thirteen, KING) == 0);
		assertTrue(Player.getPointValue(thirteen, QUEEN) == 0);
		assertTrue(Player.getPointValue(thirteen, JACK) == 0);
		assertTrue(Player.getPointValue(thirteen, TEN) == 0);
		assertTrue(Player.getPointValue(thirteen, NINE) == 0);
		assertTrue(Player.getPointValue(thirteen, EIGHT) == 0);
		assertTrue(Player.getPointValue(thirteen, SEVEN) == 0);
		assertTrue(Player.getPointValue(thirteen, SIX) == 0);
		assertTrue(Player.getPointValue(thirteen, FIVE) == 0);
		assertTrue(Player.getPointValue(thirteen, FOUR) == 0);
		assertTrue(Player.getPointValue(thirteen, THREE) == 0);
		assertTrue(Player.getPointValue(thirteen, TWO) == 0);
		
		assertTrue(Player.getPointValue(twelve, ACE) == 0);
		assertTrue(Player.getPointValue(twelve, KING) == 0);
		assertTrue(Player.getPointValue(twelve, QUEEN) == 0);
		assertTrue(Player.getPointValue(twelve, JACK) == 0);
		assertTrue(Player.getPointValue(twelve, TEN) == 0);
		assertTrue(Player.getPointValue(twelve, NINE) == 0);
		assertTrue(Player.getPointValue(twelve, EIGHT) == 0);
		assertTrue(Player.getPointValue(twelve, SEVEN) == 0);
		assertTrue(Player.getPointValue(twelve, SIX) == 0);
		assertTrue(Player.getPointValue(twelve, FIVE) == 0);
		assertTrue(Player.getPointValue(twelve, FOUR) == 0);
		assertTrue(Player.getPointValue(twelve, THREE) == 0);
		assertTrue(Player.getPointValue(twelve, TWO) == 0);
		
		assertTrue(Player.getPointValue(eleven, ACE) == 2);
		assertTrue(Player.getPointValue(eleven, KING) == 2);
		assertTrue(Player.getPointValue(eleven, QUEEN) == 2);
		assertTrue(Player.getPointValue(eleven, JACK) == 2);
		assertTrue(Player.getPointValue(eleven, TEN) == 2);
		assertTrue(Player.getPointValue(eleven, NINE) == 2);
		assertTrue(Player.getPointValue(eleven, EIGHT) == 2);
		assertTrue(Player.getPointValue(eleven, SEVEN) == 2);
		assertTrue(Player.getPointValue(eleven, SIX) == 2);
		assertTrue(Player.getPointValue(eleven, FIVE) == 2);
		assertTrue(Player.getPointValue(eleven, FOUR) == 2);
		assertTrue(Player.getPointValue(eleven, THREE) == 2);
		assertTrue(Player.getPointValue(eleven, TWO) == 2);
		
		assertTrue(Player.getPointValue(ten, ACE) == 2);
		assertTrue(Player.getPointValue(ten, KING) == 2);
		assertTrue(Player.getPointValue(ten, QUEEN) == 2);
		assertTrue(Player.getPointValue(ten, JACK) == 2);
		assertTrue(Player.getPointValue(ten, TEN) == 2);
		assertTrue(Player.getPointValue(ten, NINE) == 2);
		assertTrue(Player.getPointValue(ten, EIGHT) == 2);
		assertTrue(Player.getPointValue(ten, SEVEN) == 2);
		assertTrue(Player.getPointValue(ten, SIX) == 2);
		assertTrue(Player.getPointValue(ten, FIVE) == 2);
		assertTrue(Player.getPointValue(ten, FOUR) == 2);
		assertTrue(Player.getPointValue(ten, THREE) == 2);
		assertTrue(Player.getPointValue(ten, TWO) == 2);
		
		assertTrue(Player.getPointValue(nine, ACE) == 1);
		assertTrue(Player.getPointValue(nine, KING) == 1);
		assertTrue(Player.getPointValue(nine, QUEEN) == 1);
		assertTrue(Player.getPointValue(nine, JACK) == 1);
		assertTrue(Player.getPointValue(nine, TEN) == 1);
		assertTrue(Player.getPointValue(nine, NINE) == 1);
		assertTrue(Player.getPointValue(nine, EIGHT) == 1);
		assertTrue(Player.getPointValue(nine, SEVEN) == 1);
		assertTrue(Player.getPointValue(nine, SIX) == 1);
		assertTrue(Player.getPointValue(nine, FIVE) == 1);
		assertTrue(Player.getPointValue(nine, FOUR) == 1);
		assertTrue(Player.getPointValue(nine, THREE) == 1);
		assertTrue(Player.getPointValue(nine, TWO) == 1);
		
		assertTrue(Player.getPointValue(eight, ACE) == 1);
		assertTrue(Player.getPointValue(eight, KING) == 1);
		assertTrue(Player.getPointValue(eight, QUEEN) == 1);
		assertTrue(Player.getPointValue(eight, JACK) == 1);
		assertTrue(Player.getPointValue(eight, TEN) == 1);
		assertTrue(Player.getPointValue(eight, NINE) == 1);
		assertTrue(Player.getPointValue(eight, EIGHT) == 1);
		assertTrue(Player.getPointValue(eight, SEVEN) == 1);
		assertTrue(Player.getPointValue(eight, SIX) == 1);
		assertTrue(Player.getPointValue(eight, FIVE) == 1);
		assertTrue(Player.getPointValue(eight, FOUR) == 1);
		assertTrue(Player.getPointValue(eight, THREE) == 1);
		assertTrue(Player.getPointValue(eight, TWO) == 1);
		
		assertTrue(Player.getPointValue(seven, ACE) == 1);
		assertTrue(Player.getPointValue(seven, KING) == 1);
		assertTrue(Player.getPointValue(seven, QUEEN) == 1);
		assertTrue(Player.getPointValue(seven, JACK) == 1);
		assertTrue(Player.getPointValue(seven, TEN) == 1);
		assertTrue(Player.getPointValue(seven, NINE) == 1);
		assertTrue(Player.getPointValue(seven, EIGHT) == 1);
		assertTrue(Player.getPointValue(seven, SEVEN) == 1);
		assertTrue(Player.getPointValue(seven, SIX) == 1);
		assertTrue(Player.getPointValue(seven, FIVE) == 1);
		assertTrue(Player.getPointValue(seven, FOUR) == 1);
		assertTrue(Player.getPointValue(seven, THREE) == 1);
		assertTrue(Player.getPointValue(seven, TWO) == 1);
		
		assertTrue(Player.getPointValue(sSeventeen, ACE) == 1);
		assertTrue(Player.getPointValue(sSeventeen, KING) == 1);
		assertTrue(Player.getPointValue(sSeventeen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sSeventeen, JACK) == 1);
		assertTrue(Player.getPointValue(sSeventeen, TEN) == 1);
		assertTrue(Player.getPointValue(sSeventeen, NINE) == 1);
		assertTrue(Player.getPointValue(sSeventeen, EIGHT) == 1);
		assertTrue(Player.getPointValue(sSeventeen, SEVEN) == 1);
		assertTrue(Player.getPointValue(sSeventeen, SIX) == 1);
		assertTrue(Player.getPointValue(sSeventeen, FIVE) == 1);
		assertTrue(Player.getPointValue(sSeventeen, FOUR) == 1);
		assertTrue(Player.getPointValue(sSeventeen, THREE) == 1);
		assertTrue(Player.getPointValue(sSeventeen, TWO) == 1);
		
		assertTrue(Player.getPointValue(sSixteen, ACE) == 1);
		assertTrue(Player.getPointValue(sSixteen, KING) == 1);
		assertTrue(Player.getPointValue(sSixteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sSixteen, JACK) == 1);
		assertTrue(Player.getPointValue(sSixteen, TEN) == 1);
		assertTrue(Player.getPointValue(sSixteen, NINE) == 1);
		assertTrue(Player.getPointValue(sSixteen, EIGHT) == 1);
		assertTrue(Player.getPointValue(sSixteen, SEVEN) == 1);
		assertTrue(Player.getPointValue(sSixteen, SIX) == 1);
		assertTrue(Player.getPointValue(sSixteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sSixteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sSixteen, THREE) == 1);
		assertTrue(Player.getPointValue(sSixteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(sFifteen, ACE) == 1);
		assertTrue(Player.getPointValue(sFifteen, KING) == 1);
		assertTrue(Player.getPointValue(sFifteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sFifteen, JACK) == 1);
		assertTrue(Player.getPointValue(sFifteen, TEN) == 1);
		assertTrue(Player.getPointValue(sFifteen, NINE) == 1);
		assertTrue(Player.getPointValue(sFifteen, EIGHT) == 1);
		assertTrue(Player.getPointValue(sFifteen, SEVEN) == 1);
		assertTrue(Player.getPointValue(sFifteen, SIX) == 1);
		assertTrue(Player.getPointValue(sFifteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sFifteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sFifteen, THREE) == 1);
		assertTrue(Player.getPointValue(sFifteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(sFourteen, ACE) == 1);
		assertTrue(Player.getPointValue(sFourteen, KING) == 1);
		assertTrue(Player.getPointValue(sFourteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sFourteen, JACK) == 1);
		assertTrue(Player.getPointValue(sFourteen, TEN) == 1);
		assertTrue(Player.getPointValue(sFourteen, NINE) == 1);
		assertTrue(Player.getPointValue(sFourteen, EIGHT) == 1);
		assertTrue(Player.getPointValue(sFourteen, SEVEN) == 1);
		assertTrue(Player.getPointValue(sFourteen, SIX) == 1);
		assertTrue(Player.getPointValue(sFourteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sFourteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sFourteen, THREE) == 1);
		assertTrue(Player.getPointValue(sFourteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(sThirteen, ACE) == 1);
		assertTrue(Player.getPointValue(sThirteen, KING) == 1);
		assertTrue(Player.getPointValue(sThirteen, QUEEN) == 1);
		assertTrue(Player.getPointValue(sThirteen, JACK) == 1);
		assertTrue(Player.getPointValue(sThirteen, TEN) == 1);
		assertTrue(Player.getPointValue(sThirteen, NINE) == 1);
		assertTrue(Player.getPointValue(sThirteen, EIGHT) == 1);
		assertTrue(Player.getPointValue(sThirteen, SEVEN) == 1);
		assertTrue(Player.getPointValue(sThirteen, SIX) == 1);
		assertTrue(Player.getPointValue(sThirteen, FIVE) == 1);
		assertTrue(Player.getPointValue(sThirteen, FOUR) == 1);
		assertTrue(Player.getPointValue(sThirteen, THREE) == 1);
		assertTrue(Player.getPointValue(sThirteen, TWO) == 1);
		
		assertTrue(Player.getPointValue(aceSpl, ACE) == 2);
		assertTrue(Player.getPointValue(aceSpl, KING) == 2);
		assertTrue(Player.getPointValue(aceSpl, QUEEN) == 2);
		assertTrue(Player.getPointValue(aceSpl, JACK) == 2);
		assertTrue(Player.getPointValue(aceSpl, TEN) == 2);
		assertTrue(Player.getPointValue(aceSpl, NINE) == 2);
		assertTrue(Player.getPointValue(aceSpl, EIGHT) == 2);
		assertTrue(Player.getPointValue(aceSpl, SEVEN) == 2);
		assertTrue(Player.getPointValue(aceSpl, SIX) == 2);
		assertTrue(Player.getPointValue(aceSpl, FIVE) == 2);
		assertTrue(Player.getPointValue(aceSpl, FOUR) == 2);
		assertTrue(Player.getPointValue(aceSpl, THREE) == 2);
		assertTrue(Player.getPointValue(aceSpl, TWO) == 2);
		
		assertTrue(Player.getPointValue(nineSpl, ACE) == 1);
		assertTrue(Player.getPointValue(nineSpl, KING) == 1);
		assertTrue(Player.getPointValue(nineSpl, QUEEN) == 1);
		assertTrue(Player.getPointValue(nineSpl, JACK) == 1);
		assertTrue(Player.getPointValue(nineSpl, TEN) == 1);
		assertTrue(Player.getPointValue(nineSpl, NINE) == 1);
		assertTrue(Player.getPointValue(nineSpl, EIGHT) == 2);
		assertTrue(Player.getPointValue(nineSpl, SEVEN) == 1);
		assertTrue(Player.getPointValue(nineSpl, SIX) == 1);
		assertTrue(Player.getPointValue(nineSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(nineSpl, FOUR) == 1);
		assertTrue(Player.getPointValue(nineSpl, THREE) == 1);
		assertTrue(Player.getPointValue(nineSpl, TWO) == 1);
		
		assertTrue(Player.getPointValue(eightSpl, ACE) == 0);
		assertTrue(Player.getPointValue(eightSpl, KING) == 0);
		assertTrue(Player.getPointValue(eightSpl, QUEEN) == 0);
		assertTrue(Player.getPointValue(eightSpl, JACK) == 0);
		assertTrue(Player.getPointValue(eightSpl, TEN) == 0);
		assertTrue(Player.getPointValue(eightSpl, NINE) == 0);
		assertTrue(Player.getPointValue(eightSpl, EIGHT) == 1);
		assertTrue(Player.getPointValue(eightSpl, SEVEN) == 2);
		assertTrue(Player.getPointValue(eightSpl, SIX) == 1);
		assertTrue(Player.getPointValue(eightSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(eightSpl, FOUR) == 0);
		assertTrue(Player.getPointValue(eightSpl, THREE) == 0);
		assertTrue(Player.getPointValue(eightSpl, TWO) == 0);
		
		assertTrue(Player.getPointValue(sevenSpl, ACE) == 0);
		assertTrue(Player.getPointValue(sevenSpl, KING) == 0);
		assertTrue(Player.getPointValue(sevenSpl, QUEEN) == 0);
		assertTrue(Player.getPointValue(sevenSpl, JACK) == 0);
		assertTrue(Player.getPointValue(sevenSpl, TEN) == 0);
		assertTrue(Player.getPointValue(sevenSpl, NINE) == 0);
		assertTrue(Player.getPointValue(sevenSpl, EIGHT) == 0);
		assertTrue(Player.getPointValue(sevenSpl, SEVEN) == 0);
		assertTrue(Player.getPointValue(sevenSpl, SIX) == 1);
		assertTrue(Player.getPointValue(sevenSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(sevenSpl, FOUR) == 0);
		assertTrue(Player.getPointValue(sevenSpl, THREE) == 0);
		assertTrue(Player.getPointValue(sevenSpl, TWO) == 0);
		
		assertTrue(Player.getPointValue(sixSpl, ACE) == 0);
		assertTrue(Player.getPointValue(sixSpl, KING) == 0);
		assertTrue(Player.getPointValue(sixSpl, QUEEN) == 0);
		assertTrue(Player.getPointValue(sixSpl, JACK) == 0);
		assertTrue(Player.getPointValue(sixSpl, TEN) == 0);
		assertTrue(Player.getPointValue(sixSpl, NINE) == 0);
		assertTrue(Player.getPointValue(sixSpl, EIGHT) == 0);
		assertTrue(Player.getPointValue(sixSpl, SEVEN) == 0);
		assertTrue(Player.getPointValue(sixSpl, SIX) == 1);
		assertTrue(Player.getPointValue(sixSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(sixSpl, FOUR) == 1);
		assertTrue(Player.getPointValue(sixSpl, THREE) == 0);
		assertTrue(Player.getPointValue(sixSpl, TWO) == 0);
		
		assertTrue(Player.getPointValue(threeSpl, ACE) == 1);
		assertTrue(Player.getPointValue(threeSpl, KING) == 1);
		assertTrue(Player.getPointValue(threeSpl, QUEEN) == 1);
		assertTrue(Player.getPointValue(threeSpl, JACK) == 1);
		assertTrue(Player.getPointValue(threeSpl, TEN) == 1);
		assertTrue(Player.getPointValue(threeSpl, NINE) == 1);
		assertTrue(Player.getPointValue(threeSpl, EIGHT) == 1);
		assertTrue(Player.getPointValue(threeSpl, SEVEN) == 1);
		assertTrue(Player.getPointValue(threeSpl, SIX) == 1);
		assertTrue(Player.getPointValue(threeSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(threeSpl, FOUR) == 1);
		assertTrue(Player.getPointValue(threeSpl, THREE) == 1);
		assertTrue(Player.getPointValue(threeSpl, TWO) == 1);
		
		assertTrue(Player.getPointValue(twoSpl, ACE) == 1);
		assertTrue(Player.getPointValue(twoSpl, KING) == 1);
		assertTrue(Player.getPointValue(twoSpl, QUEEN) == 1);
		assertTrue(Player.getPointValue(twoSpl, JACK) == 1);
		assertTrue(Player.getPointValue(twoSpl, TEN) == 1);
		assertTrue(Player.getPointValue(twoSpl, NINE) == 1);
		assertTrue(Player.getPointValue(twoSpl, EIGHT) == 1);
		assertTrue(Player.getPointValue(twoSpl, SEVEN) == 1);
		assertTrue(Player.getPointValue(twoSpl, SIX) == 1);
		assertTrue(Player.getPointValue(twoSpl, FIVE) == 1);
		assertTrue(Player.getPointValue(twoSpl, FOUR) == 1);
		assertTrue(Player.getPointValue(twoSpl, THREE) == 1);
		assertTrue(Player.getPointValue(twoSpl, TWO) == 1);
	}
	
	public void testSplitDecision(){
		Hand eightFour = new Hand(EIGHT, FOUR);
		Hand aceEight = new Hand(ACE, EIGHT);
		
		assertTrue(Player.shouldSwitch(eightFour, aceEight, SIX));
		
		Hand twoTen = new Hand(TEN, TEN);
		Hand twoAce = new Hand(ACE, ACE);
		assertTrue(Player.shouldSwitch(twoTen, twoAce, SIX));
		
		twoTen = new Hand(TEN, TWO);
		twoAce = new Hand(ACE, ACE);
		assertTrue(Player.shouldSwitch(twoTen, twoAce, SIX));
	}
	
	public void testNatural(){
		Hand blackjack = new Hand(TEN, ACE);
		assertTrue(blackjack.isNatural());
		
		Hand twoTen = new Hand(TEN, TEN);
		Hand twoAce = new Hand(ACE, ACE);
		Hand.switchHands(twoTen, twoAce);
		assertFalse(twoTen.isNatural());
		assertFalse(twoAce.isNatural());
		assertTrue(twoTen.cards.get(0).value == VALUE.TEN);
		assertTrue(twoTen.cards.get(1).value == VALUE.ACE);
		assertTrue(twoAce.cards.get(1).value == VALUE.TEN);
		assertTrue(twoAce.cards.get(0).value == VALUE.ACE);
	}
}
