package handy.common21.sim.sidebet;

import handy.common21.sim.Hand;

public class InsuranceDescription extends SideBetDescription {

	private static InsuranceDescription singleton = null;
	
	private InsuranceDescription(){
		
	}
	
	public static InsuranceDescription getInsuranceDescription(){
		if(singleton == null){
			singleton = new InsuranceDescription();
		}
		return singleton;
	}
	
	public int determineReturnOnBetFromRound(Hand dealerHand, Hand playerHand, int playerBet) {
		if(dealerHand.isNatural()){
			return playerBet * 2;
		}else{
			return -playerBet;
		}
	}

}
