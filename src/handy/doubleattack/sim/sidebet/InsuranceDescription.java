package handy.doubleattack.sim.sidebet;

import handy.common21.sim.sidebet.SideBetDescription;
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
			return (int) (playerBet * 2.5);
		}else{
			return -playerBet;
		}
	}

}
