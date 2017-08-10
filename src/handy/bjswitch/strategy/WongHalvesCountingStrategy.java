package handy.bjswitch.strategy;

import handy.cards.Card;
import handy.common21.sim.Rules;
import handy.common21.strategy.StrategyConfig;

public class WongHalvesCountingStrategy extends LinearCountingStrategy {

	public WongHalvesCountingStrategy(StrategyConfig cfg, Rules rules){
		super(cfg, rules);
	}

	protected void updateCount(Card card){
		if(card.getBlackjackValue() >= 10){
			count--;
		}else if(card.getBlackjackValue() == 9){
			count -= .5;
		}else if(card.getBlackjackValue() == 2 || card.getBlackjackValue() == 7){
			count += .5;
		}else if(card.getBlackjackValue() == 3 || card.getBlackjackValue() == 4 ||
				card.getBlackjackValue() == 6){
			count += 1;
		}else if(card.getBlackjackValue() == 5){
			count += 1.5;
		}
		
		if(Rules.DEBUG){
			System.out.println(count + ": Update count: " + card.suite + " " + card.value);
		}
	}

}
