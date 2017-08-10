package handy.blackjack.sim.sets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import handy.blackjack.sim.Hand;
import handy.common21.strategy.DealerChoice;
import handy.cards.Card;
import handy.cards.Card.SUITE;
import handy.cards.Card.VALUE;
import handy.cards.StackedShoe;

public class BustCalculator {

	private static final boolean H17 = false;
	private static final int SAMPLES = 100000;

	public static void main(String args[]) {
		BustCalculator sim = new BustCalculator();
		sim.findAllOutcomeProbabilities();
	}

	public void findAllOutcomeProbabilities() {
		try {
			File file = new File("dealerBustH17.txt");
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			Card ace = new Card(SUITE.CLUBS, VALUE.ACE);
			findPlayedHandStats(ace, SAMPLES, writer);

			Card two = new Card(SUITE.CLUBS, VALUE.TWO);
			findPlayedHandStats(two, SAMPLES, writer);

			Card three = new Card(SUITE.CLUBS, VALUE.THREE);
			findPlayedHandStats(three, SAMPLES, writer);

			Card four = new Card(SUITE.CLUBS, VALUE.FOUR);
			findPlayedHandStats(four, SAMPLES, writer);

			Card five = new Card(SUITE.CLUBS, VALUE.FIVE);
			findPlayedHandStats(five, SAMPLES, writer);

			Card six = new Card(SUITE.CLUBS, VALUE.SIX);
			findPlayedHandStats(six, SAMPLES, writer);

			Card seven = new Card(SUITE.CLUBS, VALUE.SEVEN);
			findPlayedHandStats(seven, SAMPLES, writer);

			Card eight = new Card(SUITE.CLUBS, VALUE.EIGHT);
			findPlayedHandStats(eight, SAMPLES, writer);

			Card nine = new Card(SUITE.CLUBS, VALUE.NINE);
			findPlayedHandStats(nine, SAMPLES, writer);

			Card ten = new Card(SUITE.CLUBS, VALUE.TEN);
			findPlayedHandStats(ten, SAMPLES, writer);

			writer.flush();

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void findPlayedHandStats(Card upCard, int samples, FileWriter fw) throws IOException {
		Map<Integer, Integer> outcomes = new HashMap<Integer, Integer>();

		for (int idx = 0; idx < samples; idx++) {
			int outcome = playDealerHand(upCard);
			if (outcomes.containsKey(outcome)) {
				int newTotal = outcomes.get(outcome) + 1;
				outcomes.put(outcome, newTotal);
			} else {
				outcomes.put(outcome, 1);
			}
		}

		fw.write("Stats for: " + upCard.toString() + "\r\n");
		int bustTotal = 0;
		for (Integer outcome : outcomes.keySet()) {
			fw.write(outcome + ": " + (outcomes.get(outcome).floatValue() / samples) * 100 + "%\r\n");
			if (outcome > 21) {
				bustTotal += outcomes.get(outcome);
			}
		}
		fw.write("Busts: " + (bustTotal * 1.0) / (samples * 1.0) + "\r\n");
	}

	private int playDealerHand(Card upCard) {
		List<Card> excludedCard = new ArrayList<Card>();
		excludedCard.add(upCard);
		StackedShoe ss = new StackedShoe(1, excludedCard);

		Hand hand = new Hand(upCard, ss.poll());
		DealerChoice.playDealerHand(ss, hand, H17);
		return hand.getValue();
	}
}
