package handy.blackjack.trainer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import handy.blackjack.sim.Hand;
import handy.blackjack.strategy.TextbookStrategy;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.Round.OUTCOME;
import handy.common21.sim.Rules;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.DealerChoice;

public class StrategyTrainer {

	public static final boolean H17 = false;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;

	public static void main(String[] args) {
		Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 6, 0.8f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
		Shoe shoe = new Shoe(rules.getShoeDeckCount());
		Scanner sc = new Scanner(System.in);

		boolean playing = true;
		while (playing) {
			if (shoe.getDeckPenetration() >= rules.getDeckPenetration()) {
				shoe = new Shoe(rules.getShoeDeckCount());
			}

			Hand dealerHand = new Hand(shoe.poll(), shoe.poll());
			Hand playerHand = new Hand(shoe.poll(), shoe.poll());

			if (dealerHand.isNatural() && playerHand.isNatural()) {
				System.out.println("Both natural, push!");
				continue;
			} else if (dealerHand.isNatural()) {
				System.out.println("Dealer natural, you lose!");
				continue;
			} else if (playerHand.isNatural()) {
				System.out.println("Your natural, you win!");
				continue;
			}

			System.out.println("Dealer showing: " + dealerHand.firstCard().toString());
			System.out.println(
					"You're holding: " + playerHand.cards.get(0).toString() + " and " + playerHand.cards.get(1));

			List<Hand> hands = new ArrayList<Hand>();
			try{
				playHand(sc, rules, playerHand, shoe, dealerHand.firstCard(), hands);
			}catch (Exception e){
				System.out.println(e.getMessage());
				continue;
			}
			
			DealerChoice.playDealerHand(shoe, dealerHand, rules.isH17());
			
			for(Hand h : hands){
				OUTCOME outcome = handy.common21.sim.Round.getVanillaBlackjackOutcome(h, dealerHand);
				if (outcome == OUTCOME.DEALER_WIN) {
					System.out.println("Dealer wins!");
				} else if (outcome == OUTCOME.PLAYER_BLACKJACK ||
						outcome == OUTCOME.PLAYER_WIN){
					System.out.println("Player wins!");
				} else {
					System.out.println("Push!");
				}
			}
			
		}
	}
	
	public static void playHand(Scanner scan, Rules rules, Hand hand, Shoe shoe, Card dealerCard, List<Hand> hr) throws Exception{
		System.out.println("You have this hand, what is your move?");
		for(Card c : hand.cards){
			System.out.println(c.toString());
		}
		String cmd = scan.next();
		Rules.MOVE desiredMove = TextbookStrategy.determineTextbookMove(rules, hand, dealerCard);
		if(!compareMoves(cmd, desiredMove)){
			throw new Exception("You chose: " + cmd + ", not " + humanReadMove(desiredMove));
		}
		if(desiredMove == Rules.MOVE.STAY){
			//do nothing
			hr.add(hand);
		}else if(desiredMove == Rules.MOVE.HIT){
			Card newCard = shoe.poll();
			hand.hit(newCard);
			playHand(scan, rules, hand, shoe, dealerCard, hr);
		}else if(desiredMove == Rules.MOVE.SURRENDER){
			System.out.println("Surrendered!");
			hr.add(hand);
		}else if(desiredMove == Rules.MOVE.SPLIT){
			for(Hand h : hand.split()){
				Card newCard = shoe.poll();
				h.hit(newCard);
				playHand(scan, rules, h, shoe, dealerCard, hr);
			}
		}else if(desiredMove == Rules.MOVE.DOUBLESPLIT){
			for(Hand h : hand.split()){
				hand.doubleDown();
				Card newCard = shoe.poll();
				h.hit(newCard);
				System.out.println("Doubled after splitting");
				hr.add(h);
			}
		}else if (desiredMove == Rules.MOVE.SPLITACE){
			for(Hand h : hand.split()){
				Card newCard = shoe.poll();
				h.hit(newCard);
				System.out.println("Split aces");
				hr.add(h);
			}
		}else{
			hand.doubleDown();
			Card newCard = shoe.poll();
			hand.hit(newCard);
			hr.add(hand);
		}
	}

	public static boolean compareMoves(String cmd, Rules.MOVE move){
		switch(move){
		case STAY:
			return cmd.equals("S");
		case HIT:
			return cmd.equals("H");
		case SURRENDER:
			return cmd.equals("SUR");
		case SPLIT:
			return cmd.equals("SP");
		case DOUBLESPLIT:
			return cmd.equals("DSP");
		case SPLITACE:
			return cmd.equals("SPA");
		case DOUBLE:
			return cmd.equals("D");
		default:
			return false;
		}
	}
	
	public static String humanReadMove(Rules.MOVE move){
		switch(move){
		case STAY:
			return "STAY";
		case HIT:
			return "HIT";
		case SURRENDER:
			return "SURRENDER";
		case SPLIT:
			return "SPLIT";
		case DOUBLESPLIT:
			return "DOUBLESPLIT";
		case SPLITACE:
			return "SPLITACE";
		case DOUBLE:
			return "DOUBLE";
		default:
			return "Unknown enum";
		}
	}
}
