package handy.common21.stats;

import java.util.Set;

public class SimulationSetResults {
	private long totalSumOfEndMoney;
	private int totalBankrupts;
	private int totalTableSwitches;
	private int numOfSimulations;
	private String simSetName;
	private int gamesPerSim;
	private int startingCash;
	private Set<Integer> outcomes;
	
	public SimulationSetResults(long totalSumOfEndMoney, int totalBankrupts, int totalTableSwitches,
			String simSetName, int numOfSimulations, int gamesPerSim, int startingCash, Set<Integer> outcomes){
		this.totalSumOfEndMoney = totalSumOfEndMoney;
		this.totalBankrupts = totalBankrupts;
		this.totalTableSwitches = totalTableSwitches;
		this.simSetName = simSetName;
		this.numOfSimulations = numOfSimulations;
		this.gamesPerSim = gamesPerSim;
		this.startingCash = startingCash;
		this.outcomes = outcomes;
	}
	
	public long getExpectedFinalCashPool(){
		return (totalSumOfEndMoney / numOfSimulations);
	}
	
	public float getOddsOfBankrupcy(){
		return ((float)(totalBankrupts) / numOfSimulations);
	}
	
	public int getAverageTablesSwitched(){
		return (totalTableSwitches) / numOfSimulations;
	}
	
	public int getGamesBetweenSwitches(){
		if(getAverageTablesSwitched() == 0){
			return -1;
		}else{
			return gamesPerSim / getAverageTablesSwitched();
		}
	}
	
	public long getHourlyReturnAt100GPH(){
		return (getExpectedFinalCashPool() - startingCash) / (gamesPerSim / 100);
	}
	
	public void printString(){
		System.out.println(simSetName + " Complete");
		System.out.println("Expected final pool: " + getExpectedFinalCashPool());
		System.out.println("Positive outcome: " + getPercentagePositive() + "%");
		System.out.println("Standard Deviation on final: " + getStdDevFinalPot());
		System.out.println("Hourly return at 100 games / hr: " + getHourlyReturnAt100GPH());
		System.out.println("Odds of bankrupcy: " + getOddsOfBankrupcy());
		if(getGamesBetweenSwitches() >= 0){
			System.out.println("Average tables switched per " + gamesPerSim + " games: " + getAverageTablesSwitched());
			System.out.println("Games between switches: " + getGamesBetweenSwitches());
		}
	}
	
	public double getStdDevFinalPot(){
		double powerSum = 0;
		double mean = getExpectedFinalCashPool();
		for(Integer result : outcomes){
			powerSum += Math.pow(result - mean, 2);
		}
		return Math.sqrt(powerSum / outcomes.size());
	}
	
	public float getPercentagePositive(){
		float totalPos = 0;
		for(Integer result : outcomes){
			if(result >= startingCash){
				totalPos++;
			}
		}
		return (totalPos / outcomes.size()) * 100;
	}
}
