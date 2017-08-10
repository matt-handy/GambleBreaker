package handy.blackjacksim.strategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import handy.common21.sim.Rules;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.StrategyConfig;
import handy.blackjack.strategy.HighLowCountingStrategy;
import handy.cards.Shoe;
import junit.framework.TestCase;

public class CountDwellTimeTest extends TestCase {

	public static final boolean H17 = true;
	public static final boolean CAN_SURRENDER = true;
	public static final boolean CAN_DOUBLE = true;
	public static final boolean CAN_DOUBLESPLIT = false;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	Rules rules = new Rules(H17, CAN_SURRENDER, CAN_DOUBLE, 8, 0.8f, CAN_DOUBLESPLIT, new HashSet<SideBetDescription>(), MIN_BET);
	
	StrategyConfig sc = new StrategyConfig(false, -5, 0);
	
	
	public void testCountDwellEightDeckShoeFullPen(){
		Map<Integer, Long> masterCountOfCounts = new HashMap<Integer, Long>();
		for(int i = 0; i < 100000; i++){ //Run 100000 shoes to average the counts
			Shoe testShoe = new Shoe(8);
			HighLowCountingStrategy strat = new HighLowCountingStrategy(sc, rules);
			while(testShoe.decksRemaining() > 0){
				strat.updateStrategyCard(testShoe.poll());
				int count = strat.getTrueCount(testShoe);
				
				if(masterCountOfCounts.get(count) == null){
					masterCountOfCounts.put(count, 1l);
				}else{
					long currentCountOfCount = masterCountOfCounts.get(count);
					currentCountOfCount += 1;
					masterCountOfCounts.put(count, currentCountOfCount);
				}
			}
		}
		
		for(Integer count : masterCountOfCounts.keySet()){
			System.out.print("For count: " + count);
			double dwellFraction = new Double(masterCountOfCounts.get(count)) / new Double(52 * 8 * 100000);
			System.out.println(" we have fraction: " + dwellFraction);
		}
		
		double zeroFraction = new Double(masterCountOfCounts.get(0)) / new Double(52 * 8 * 100000);
		System.out.println("Zero fraction: " + zeroFraction);
		assertTrue(.23 < zeroFraction);
		assertTrue(.25 > zeroFraction);
		
		double oneFraction = new Double(masterCountOfCounts.get(1)) / new Double(52 * 8 * 100000); 
		assertTrue(.10 < oneFraction);
		assertTrue(.11 > oneFraction);
		
		double twoFraction = new Double(masterCountOfCounts.get(2)) / new Double(52 * 8 * 100000);
		assertTrue(.06 < twoFraction);
		assertTrue(.07 > twoFraction);
		
		double threeFraction = new Double(masterCountOfCounts.get(3)) / new Double(52 * 8 * 100000);
		assertTrue(.03 < threeFraction);
		assertTrue(.04 > threeFraction);
		
		double fourFraction = new Double(masterCountOfCounts.get(4)) / new Double(52 * 8 * 100000); 
		assertTrue(.02 < fourFraction);
		assertTrue(.03 > fourFraction);
		
		double fiveFraction = new Double(masterCountOfCounts.get(5)) / new Double(52 * 8 * 100000);
		assertTrue(.01 < fiveFraction);
		assertTrue(.02 > fiveFraction);
		
		double sixFraction = new Double(masterCountOfCounts.get(6)) / new Double(52 * 8 * 100000); 
		assertTrue(.01 < sixFraction);
		assertTrue(.015 > sixFraction);
		
		double sevenFraction = new Double(masterCountOfCounts.get(7)) / new Double(52 * 8 * 100000);
		assertTrue(.001 < sevenFraction);
		assertTrue(.01 > sevenFraction);
	}
	
	public void testCountDwellEightDeckShoeFull(){
		Map<Integer, Long> masterCountOfCounts = new HashMap<Integer, Long>();
		for(int i = 0; i < 100000; i++){ //Run 100000 shoes to average the counts
			Shoe testShoe = new Shoe(8);
			HighLowCountingStrategy strat = new HighLowCountingStrategy(sc, rules);
			while(testShoe.decksRemaining() > 1){
				strat.updateStrategyCard(testShoe.poll());
				int count = strat.getTrueCount(testShoe);
				
				if(masterCountOfCounts.get(count) == null){
					masterCountOfCounts.put(count, 1l);
				}else{
					long currentCountOfCount = masterCountOfCounts.get(count);
					currentCountOfCount += 1;
					masterCountOfCounts.put(count, currentCountOfCount);
				}
			}
		}
		
		for(Integer count : masterCountOfCounts.keySet()){
			System.out.print("For count: " + count);
			double dwellFraction = new Double(masterCountOfCounts.get(count)) / new Double(52 * 8 * 100000);
			System.out.println(" we have fraction: " + dwellFraction);
		}
		
		double zeroFraction = new Double(masterCountOfCounts.get(0)) / new Double(52 * 8 * 100000);
		System.out.println("Zero fraction: " + zeroFraction);
		assertTrue(.22 < zeroFraction);
		assertTrue(.24 > zeroFraction);
		
		double oneFraction = new Double(masterCountOfCounts.get(1)) / new Double(52 * 8 * 100000); 
		assertTrue(.10 < oneFraction);
		assertTrue(.11 > oneFraction);
		
		double twoFraction = new Double(masterCountOfCounts.get(2)) / new Double(52 * 8 * 100000);
		assertTrue(.05 < twoFraction);
		assertTrue(.06 > twoFraction);
		
		double threeFraction = new Double(masterCountOfCounts.get(3)) / new Double(52 * 8 * 100000);
		assertTrue(.03 < threeFraction);
		assertTrue(.04 > threeFraction);
		
		double fourFraction = new Double(masterCountOfCounts.get(4)) / new Double(52 * 8 * 100000); 
		assertTrue(.015 < fourFraction);
		assertTrue(.025 > fourFraction);
		
		double fiveFraction = new Double(masterCountOfCounts.get(5)) / new Double(52 * 8 * 100000);
		assertTrue(.01 < fiveFraction);
		assertTrue(.02 > fiveFraction);
		
		double sixFraction = new Double(masterCountOfCounts.get(6)) / new Double(52 * 8 * 100000); 
		assertTrue(.005 < sixFraction);
		assertTrue(.01 > sixFraction);
		
		double sevenFraction = new Double(masterCountOfCounts.get(7)) / new Double(52 * 8 * 100000);
		assertTrue(.001 < sevenFraction);
		assertTrue(.01 > sevenFraction);
	}
}
