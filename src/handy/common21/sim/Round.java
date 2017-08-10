package handy.common21.sim;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.DealerChoice;

public abstract class Round {
	protected OUTCOME lastHandOutcome = OUTCOME.PUSH;
	protected Map<SideBetDescription, Integer> sideBetsMap = new HashMap<SideBetDescription, Integer>();
	
	public RoundOutcome playRoundMaster(Shoe shoe, Rules rules, Player player, OUTCOME previousOutcome) {
		int playerBet = player.getBet(shoe, previousOutcome);

		Hand dealerHand = getHand(shoe.poll(), shoe.poll());
		Hand playerHand = getHand(shoe.poll(), shoe.poll());
		
		playerBet = playerBetMod(player, shoe, dealerHand.firstCard(), playerHand, playerBet);

		setupSideBets(rules, shoe, playerHand, dealerHand, playerBet, player);

		player.updateStrategyPlayerHand(playerHand);
		player.updateStrategyCard(dealerHand.firstCard());

		boolean playPlayerHand = workBlackjackLogic(player, playerHand, dealerHand, playerBet);

		if (!playPlayerHand) {
			return new RoundOutcome(lastHandOutcome, player.walkAway(shoe));
		}

		List<HandResult> hr = null;
		
		try {
			hr = playHand(player, rules, playerHand, shoe, dealerHand.firstCard(), playerBet);
		} catch (Exception e) {
			e.printStackTrace();
			// Default to push if some exceptional state occurs
			return new RoundOutcome(OUTCOME.PUSH, player.walkAway(shoe));
		}
		
		
		resolveHands(hr, player, dealerHand, shoe, rules);

		// Why do we only return the last hand outcome, and not aggregate? Split
		// hands happen
		// rarely, and its hard to establish what the "normal" baseline response
		// is. Just
		// assume the player is emotionally responding to the last hand played.
		return new RoundOutcome(lastHandOutcome, player.walkAway(shoe));
	}
	
	protected void resolveHands(List<HandResult> hr, Player player, Hand dealerHand, Shoe shoe, Rules rules){
		DealerChoice.playDealerHand(shoe, dealerHand, rules.isH17());
		player.updateStrategyDealerHand(dealerHand);

		if (Rules.DEBUG) {
			System.out.println("Dealer Hand:");
			for (Card card : dealerHand.cards) {
				System.out.println("Card: " + card.suite + " " + card.value);
			}
		}

		

			for (HandResult result : hr) {

				if (Rules.DEBUG) {
					System.out.println("Player Hand:");
					for (Card card : result.hand.cards) {
						System.out.println("Card: " + card.suite + " " + card.value);
					}
				}

				OUTCOME outcome = getOutcome((Hand) result.hand, dealerHand);
				lastHandOutcome = outcome;
				player.dollars += determineOutcome(outcome, result, dealerHand);

				if(Rules.DEBUG){
					System.out.println("Outcome: " + lastHandOutcome);
				}
				
				//TODO: Test if this counts insurance costs twice if insurance is bet
				//and multiple hands result
				determineSideBetOutcome(player, dealerHand, result.hand);
			}
		
			
	}
	
	public void determineSideBetOutcome(handy.common21.sim.Player player, handy.common21.sim.Hand dealerHand,
			handy.common21.sim.Hand playerHand) {
		for (SideBetDescription sideBet : sideBetsMap.keySet()) {
			player.dollars += sideBet.determineReturnOnBetFromRound(dealerHand, playerHand, sideBetsMap.get(sideBet));
		}
	}
	
	public abstract boolean workBlackjackLogic(Player player, Hand playerHand, Hand dealerHand, int playerBet);
	public abstract int determineOutcome(OUTCOME outcome, HandResult result, Hand dealerHand);
	public abstract void setupSideBets(Rules rules, Shoe shoe, Hand playerHand, Hand dealerHand, int playerBet, Player player);
	public abstract OUTCOME getOutcome(Hand playerHand, Hand dealerHand);
	public abstract List<HandResult> playHand(Player player, Rules rules, Hand hand, Shoe shoe, Card dealerCard, int bet)
			throws Exception;
	public abstract Hand getHand(Card card1, Card card2);
	public abstract int playerBetMod(Player player, Shoe shoe, Card dealerUpCard, Hand playerHand, int playerBet);
	
	public enum OUTCOME {
		SURRENDER, PLAYER_BLACKJACK, PLAYER_WIN, DEALER_WIN, PUSH
	};
	
	public abstract RoundOutcome play(Shoe shoe, Rules rules, Player player, OUTCOME previousOutcome) throws Exception;
	
	public static OUTCOME getVanillaBlackjackOutcome(Hand playerHand, Hand dealerHand) {
		if (playerHand.isBust()) {
			return OUTCOME.DEALER_WIN;
		} else if (dealerHand.isNatural() && playerHand.isNatural()) {
			return OUTCOME.PUSH;
		} else if (dealerHand.isNatural()) {
			return OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			return OUTCOME.PLAYER_BLACKJACK;
		} else if (dealerHand.isBust()) {
			return OUTCOME.PLAYER_WIN;
		} else if (dealerHand.getValue() > playerHand.getValue()) {
			return OUTCOME.DEALER_WIN;
		} else if (dealerHand.getValue() < playerHand.getValue()) {
			return OUTCOME.PLAYER_WIN;
		} else if (dealerHand.getValue() == playerHand.getValue()) {
			return OUTCOME.PUSH;
		} else {
			System.out.println("This should never happen");
			return OUTCOME.DEALER_WIN;
		}
	}
	
	public boolean vanillaBlackjackLogic(Player player, handy.common21.sim.Hand playerHand, handy.common21.sim.Hand dealerHand, int playerBet) {
		// Need blackjack check here
		if (dealerHand.isNatural() && playerHand.isNatural()) {
			determineSideBetOutcome(player, dealerHand, playerHand);
			lastHandOutcome = OUTCOME.PUSH; // push
		} else if (dealerHand.isNatural()) {
			determineSideBetOutcome(player, dealerHand, playerHand);
			player.dollars -= playerBet;
			lastHandOutcome = OUTCOME.DEALER_WIN;
		} else if (playerHand.isNatural()) {
			determineSideBetOutcome(player, dealerHand, playerHand);
			player.dollars += (playerBet * 1.5);
			lastHandOutcome = OUTCOME.PLAYER_BLACKJACK;
		} else {
			return true;
		}
		return false;
	}
	
	public int vanillaBlackjackWinLoss(OUTCOME outcome, HandResult result, handy.common21.sim.Hand dealerHand) {
		if (result.hasSurrendered) {
			return -((int) result.bet / 2);
		} else if (outcome == OUTCOME.DEALER_WIN) {
			return -result.bet;
		} else if (outcome == OUTCOME.PLAYER_WIN || outcome == OUTCOME.PLAYER_BLACKJACK) {
			return result.bet;
		} else {
			if(outcome != OUTCOME.PUSH){
				System.out.println("ERROR: none push in else clause");
			}
			return 0;// Push
		}
	}
}
