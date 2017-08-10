package handy.blackjack.strategy.sidebet;

import handy.common21.sim.Hand;
import handy.cards.Card;
import handy.cards.Shoe;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class InsuranceSideBetStrategy extends SideBetStrategy {

	public enum INSURANCE_STRAT {WONG, EXACT_PROB};
	INSURANCE_STRAT strat;
	
	//For Wong
	private int linearInsuranceCount = 0;
	
	//For exact
	private int tensRemaining;
	private int allRemaining;
	private int decks;
	private float threshold;
	
	public void resetShoe() {
		linearInsuranceCount = 0;
		
		tensRemaining = 16 * decks;
		allRemaining = 52 * decks;
	}
	
	public InsuranceSideBetStrategy(int baseBet, INSURANCE_STRAT strat, int decks, float threshold){
		super(baseBet);
		this.strat = strat;
		
		this.decks = decks;
		this.threshold = threshold;
		
		resetShoe();
	}

	public void updateStrategyDealerHand(Hand dealerHand) {
		int idx = 0;
		for(Card card : dealerHand.cards){
			//We've already seen the first card and put it in strategy
			if(idx != 0){
				updateStrategyCard(card);
			}
			idx++;
		}
	}
	
	public void updateStrategyDealtHand(Hand hand) {
		for(Card card : hand.cards){
			updateStrategyCard(card);
		}
	}

	public void updateStrategyCard(Card card) {
		if(card.getBlackjackValue() == 10){
			linearInsuranceCount -= 2;
			
			tensRemaining--;
		}else{
			linearInsuranceCount++;
		}
		
			allRemaining--;
	}

	public int getBet(Shoe shoe, Hand playerHand, Card dealerUpCard, int regularBet) {
		if(strat == INSURANCE_STRAT.WONG){
			if(linearInsuranceCount >= shoe.size() * 4){
				//if(baseBet > (regularBet / 2)){
					return regularBet / 2;
				//}else{
				//	return baseBet;
				//}
			}
		}else if(strat == INSURANCE_STRAT.EXACT_PROB){
			if(((float) tensRemaining) / ((float) allRemaining) > threshold){
				//System.out.println("Tens: " + tensRemaining + ", total: " + allRemaining);
				//if(baseBet > (regularBet / 2)){
					return regularBet / 2;
				//}else{
				//	return baseBet;
				//}
			}
		}
		return 0;
	}

}
