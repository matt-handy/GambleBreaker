package handy.spanish21.strategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import handy.spanish21.sim.Rules;
import handy.spanish21.strategy.HighLowCountingStrategy;
import handy.cards.SpanishShoe;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.StrategyConfig;
import junit.framework.TestCase;

public class CountDwellTimeTest extends TestCase {

	public static final boolean H17 = true;
	public static final boolean CAN_SURRENDER = true;
	
	public static final int MIN_BET = 10;
	public static final int BASE_BET = 20;
	
	Rules rules = new Rules(H17, 8, 0.8f, new HashSet<SideBetDescription>(), MIN_BET);
	
	StrategyConfig sc = new StrategyConfig(false, -5, 0);
	
	public void testCountDwellEightDeckShoeFull(){
		Map<Integer, Long> masterCountOfCounts = new HashMap<Integer, Long>();
		for(int i = 0; i < 100000; i++){ //Run 100000 shoes to average the counts
			SpanishShoe testShoe = new SpanishShoe(8);
			HighLowCountingStrategy strat = new HighLowCountingStrategy(sc, rules);
			boolean first = true;
			while(testShoe.decksRemaining() > 1){
				if(first){
					assertTrue(strat.getTrueCount(testShoe) == -4);
					assertTrue(strat.getAbsoluteCount() == -32);
					first = false;
				}
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
		
		double n4Fraction = new Double(masterCountOfCounts.get(-4)) / new Double(52 * 8 * 100000);
		assertTrue(.18 < n4Fraction);
		assertTrue(.195 > n4Fraction);
		
		double n3Fraction = new Double(masterCountOfCounts.get(-3)) / new Double(52 * 8 * 100000); 
		assertTrue(.18 < n3Fraction);
		assertTrue(.19 > n3Fraction);
		
		double n2Fraction = new Double(masterCountOfCounts.get(-2)) / new Double(52 * 8 * 100000);
		assertTrue(.09 < n2Fraction);
		assertTrue(.10 > n2Fraction);
		
		double n1Fraction = new Double(masterCountOfCounts.get(-1)) / new Double(52 * 8 * 100000);
		assertTrue(.045 < n1Fraction);
		assertTrue(.055 > n1Fraction);
		
		double zeroFraction = new Double(masterCountOfCounts.get(0)) / new Double(52 * 8 * 100000); 
		assertTrue(.04 < zeroFraction);
		assertTrue(.05 > zeroFraction);
		
		double oneFraction = new Double(masterCountOfCounts.get(1)) / new Double(52 * 8 * 100000);
		assertTrue(.007 < oneFraction);
		assertTrue(.01 > oneFraction);
		
		double twoFraction = new Double(masterCountOfCounts.get(2)) / new Double(52 * 8 * 100000); 
		assertTrue(.003 < twoFraction);
		assertTrue(.007 > twoFraction);
		
		double threeFraction = new Double(masterCountOfCounts.get(3)) / new Double(52 * 8 * 100000);
		assertTrue(.001 < threeFraction);
		assertTrue(.005 > threeFraction);
	}
}
