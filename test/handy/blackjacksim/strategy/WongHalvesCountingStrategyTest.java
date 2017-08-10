package handy.blackjacksim.strategy;

import java.util.HashSet;

import handy.common21.sim.Rules;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.StrategyConfig;
import handy.blackjack.strategy.WongHalvesCountingStrategy;
import handy.cards.Shoe;
import junit.framework.TestCase;

public class WongHalvesCountingStrategyTest extends TestCase {
	public static final int MIN_BET = 10;
	public static final Rules PERMISSIVE_RULES = new Rules(false, true, true, 6, 0.8f, true, new HashSet<SideBetDescription>(), MIN_BET);
	
	public void testCountParity(){
		Shoe shoe = new Shoe(4);
		StrategyConfig sc = new StrategyConfig(false, -5, 0);
		WongHalvesCountingStrategy strat = new WongHalvesCountingStrategy(sc, PERMISSIVE_RULES);
		
		while(shoe.shoeList.size() > 0){
			strat.updateStrategyCard(shoe.poll());
		}
		
		assertTrue(strat.getTrueCount(shoe) == 0);
		assertTrue(strat.getAbsoluteCount() == 0);
	}
}
