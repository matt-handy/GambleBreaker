package handy.bjswitch.strategy;

import handy.cards.Card;
import handy.common21.sim.Rules;
import handy.common21.strategy.StrategyConfig;

public class HighLowCountingStrategy  extends LinearCountingStrategy {
	
	
	public HighLowCountingStrategy(StrategyConfig cfg, Rules rules){
		super(cfg, rules);
	}

	protected void updateCount(Card card){
		if(card.getBlackjackValue() < 7){
			count++;
		}else if(card.getBlackjackValue() > 9){
			count--;
		}
		if(Rules.DEBUG){
			System.out.println(count + ": Update count: " + card.suite + " " + card.value);
		}
	}
	
	


	
	
}
