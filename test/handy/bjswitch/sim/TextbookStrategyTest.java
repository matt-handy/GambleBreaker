package handy.bjswitch.sim;

import handy.blackjack.sim.Hand;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import handy.common21.sim.Rules.MOVE;
import handy.common21.sim.sidebet.SideBetDescription;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import handy.bjswitch.strategy.TextbookStrategy;
import junit.framework.TestCase;

public class TextbookStrategyTest extends TestCase {
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
	
	final Hand blackjack = new Hand(TEN, ACE);
	final Hand twenty = new Hand(TEN, TEN);
	final Hand nineteen = new Hand(TEN, NINE);
	final Hand eighteen = new Hand(TEN, EIGHT);
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
	final Hand tenSpl = new Hand(TEN, TEN);
	final Hand nineSpl = new Hand(NINE, NINE);
	final Hand eightSpl = new Hand(EIGHT, EIGHT);
	final Hand sevenSpl = new Hand(SEVEN, SEVEN);
	final Hand sixSpl = new Hand(SIX, SIX);
	final Hand fiveSpl = new Hand(FIVE, FIVE);
	final Hand fourSpl = new Hand(FOUR, FOUR);
	final Hand threeSpl = new Hand(THREE, THREE);
	final Hand twoSpl = new Hand(TWO, TWO);
	
	Rules rules = new Rules(true, 8, 0.87f, new HashSet<SideBetDescription>(), 20);
	
	public void testTextbook(){
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, FIVE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, SIX) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, seven, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, FIVE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, SIX) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eight, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, FIVE) == MOVE.HIT);
		Hand ninePrime = new Hand(THREE, TWO);
		ninePrime.hit(FOUR);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ninePrime, SIX) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nine, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, TWO) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, THREE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, FOUR) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, FIVE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, SEVEN) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, EIGHT) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, ten, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, TWO) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, THREE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, FOUR) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, FIVE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, SEVEN) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, EIGHT) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, NINE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eleven, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, FIVE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, SIX) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, twelve, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, THREE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, FOUR) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, FIVE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, SIX) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, thirteen, ACE) == MOVE.HIT);
		
		List<Hand> hands = new ArrayList<Hand>();
		hands.add(fourteen);
		hands.add(fifteen);
		hands.add(sixteen);
		for(Hand hand : hands){
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TWO) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, THREE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FOUR) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FIVE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SIX) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SEVEN) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, EIGHT) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, NINE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TEN) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, ACE) == MOVE.HIT);
		}
		
		hands = new ArrayList<Hand>();
		hands.add(seventeen);
		hands.add(eighteen);
		hands.add(nineteen);
		hands.add(twenty);
		hands.add(blackjack);
		for(Hand hand : hands){
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TWO) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, THREE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FOUR) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FIVE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SIX) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SEVEN) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, EIGHT) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, NINE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TEN) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, ACE) == MOVE.STAY);
		}
		
		hands = new ArrayList<Hand>();
		hands.add(sThirteen);
		hands.add(sFourteen);
		hands.add(sFifteen);
		for(Hand hand : hands){
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TWO) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, THREE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FOUR) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FIVE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SIX) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SEVEN) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, EIGHT) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, NINE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TEN) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, ACE) == MOVE.HIT);
		}
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, FIVE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSixteen, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, FIVE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sSeventeen, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, TWO) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, THREE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, FOUR) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, FIVE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, SEVEN) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, EIGHT) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sEighteen, ACE) == MOVE.HIT);
		
		hands = new ArrayList<Hand>();
		hands.add(sNineteen);
		hands.add(sTwenty);
		hands.add(sTwentyOne);
		for(Hand hand : hands){
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, THREE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FOUR) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FIVE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SIX) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SEVEN) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, EIGHT) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, NINE) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TEN) == MOVE.STAY);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, ACE) == MOVE.STAY);
		}
		
		hands = new ArrayList<Hand>();
		hands.add(twoSpl);
		hands.add(threeSpl);
		for(Hand hand : hands){
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TWO) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, THREE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FOUR) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, FIVE) == MOVE.SPLIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SIX) == MOVE.SPLIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, SEVEN) == MOVE.SPLIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, EIGHT) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, NINE) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, TEN) == MOVE.HIT);
			assertTrue(TextbookStrategy.determineTextbookMove(rules, hand, ACE) == MOVE.HIT);
		}
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, FOUR) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, FIVE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, SIX) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fourSpl, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, TWO) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, THREE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, FOUR) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, FIVE) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, SIX) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, SEVEN) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, EIGHT) == MOVE.DOUBLE);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, fiveSpl, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, TWO) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, THREE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, FOUR) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, FIVE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, SIX) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, SEVEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sixSpl, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, TWO) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, THREE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, FOUR) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, FIVE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, SIX) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, SEVEN) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, EIGHT) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, NINE) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, sevenSpl, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, TWO) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, THREE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, FOUR) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, FIVE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, SIX) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, SEVEN) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, EIGHT) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, NINE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, TEN) == MOVE.HIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, eightSpl, ACE) == MOVE.HIT);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, TWO) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, THREE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, FOUR) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, FIVE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, SIX) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, SEVEN) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, EIGHT) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, NINE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, TEN) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, nineSpl, ACE) == MOVE.STAY);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, TWO) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, THREE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, FOUR) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, FIVE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, SIX) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, SEVEN) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, EIGHT) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, NINE) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, TEN) == MOVE.STAY);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, tenSpl, ACE) == MOVE.STAY);
		
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, TWO) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, THREE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, FOUR) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, FIVE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, SIX) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, SEVEN) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, EIGHT) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, NINE) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, TEN) == MOVE.SPLIT);
		assertTrue(TextbookStrategy.determineTextbookMove(rules, aceSpl, ACE) == MOVE.SPLIT);
	}
}
