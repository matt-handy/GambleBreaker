package handy.bjswitch.sim;

import java.util.HashMap;

import handy.blackjack.sim.Hand;
import handy.cards.Card;
import handy.cards.Card.VALUE;
import handy.common21.sim.sidebet.SideBetDescription;
import handy.common21.strategy.Strategy;
import handy.common21.strategy.sidebet.SideBetStrategy;

public class Player extends handy.common21.sim.Player {

	private static double[] EV_H5 = {-0.2688, -0.1884, -0.1531, -0.1165, -0.087,  -0.1571, -0.2232, -0.2992, -0.3965, -0.5188};
	private static double[] EV_H6 = {-0.2821, -0.2007, -0.1647, -0.1275, -0.0989, -0.1882, -0.251,  -0.3241, -0.4179, -0.5358};
	private static double[] EV_H7 = {-0.2517, -0.1703, -0.1352, -0.0998, -0.0569, -0.1165, -0.2551, -0.326,  -0.4091, -0.545}; // 7
	private static double[] EV_H8 = {-0.1649, -0.0859, -0.0535, -0.0218,  0.0288,  0.0334, -0.1054, -0.2527, -0.3466, -0.4678}; // 8
	private static double[] EV_H9 = {-0.0692,  0.0072,  0.0365,  0.0654,  0.143,   0.122,   0.0519, -0.0956, -0.2584, -0.3774}; // 9
	private static double[] EV_H10 = { 0.0561,  0.2162,  0.2716,  0.3269,  0.4016,  0.2632,  0.1661,  0.0722, -0.0947, -0.2761}; // 10
	private static double[] EV_H11 = { 0.1678,  0.3247,  0.3767,  0.4291,  0.4934,  0.3336,  0.2301,  0.1152, -0.0078, -0.2334}; // 11
	private static double[] EV_H12 = {-0.3549, -0.3005, -0.2791, -0.2575, -0.2308, -0.2463, -0.3028, -0.3691, -0.4557, -0.5666}; // 12
	private static double[] EV_H13 = {-0.4009, -0.3488, -0.3057, -0.26,   -0.2407, -0.3001, -0.3526, -0.4142, -0.4946, -0.5976}; // 13
	private static double[] EV_H14 = {-0.4437, -0.3488, -0.3057, -0.26,   -0.2407, -0.3501, -0.3988, -0.456,  -0.5307, -0.6263}; // 14
	private static double[] EV_H15 = {-0.4442, -0.3488, -0.3057, -0.26,   -0.2407, -0.3965, -0.4418, -0.4949, -0.5642, -0.653 }; // 15
	private static double[] EV_H16 = {-0.4442, -0.3488, -0.3057, -0.26,   -0.2407, -0.4396, -0.4816, -0.531,  -0.5953, -0.6778}; // 16
	private static double[] EV_H17 = {-0.3044, -0.2138, -0.1752, -0.1378, -0.0753, -0.1714, -0.4422, -0.4794, -0.5166, -0.6701}; // 17
	private static double[] EV_H18 = {-0.0297,  0.0517,  0.0812,  0.1067,  0.1964,  0.3349,  0.0457, -0.2394, -0.2938, -0.4085}; // 18
	private static double[] EV_H19 = { 0.2349,  0.3078,  0.3285,  0.3467,  0.409,   0.5514,  0.5336,  0.2313, -0.0709, -0.1469}; // 19
	private static double[] EV_S13 = {-0.0851, -0.0194,  0.0106,  0.0408,  0.0757,  0.0767,  0.0114, -0.0763, -0.2095, -0.3689}; // A,2
	private static double[] EV_S14 = {-0.1199, -0.0429, -0.012,   0.0193,  0.0531,  0.0355, -0.0278, -0.1123, -0.2402, -0.3934}; // A,3
	private static double[] EV_S15 = {-0.1432, -0.0648, -0.033,  -0.0006,  0.0321, -0.0053, -0.0666, -0.148,  -0.2705, -0.4177}; // A,4
	private static double[] EV_S16 = {-0.1647, -0.0851, -0.0525, -0.0192,  0.0126, -0.0456, -0.1048, -0.1831, -0.3004, -0.4416}; // A,5
	private static double[] EV_S17 = {-0.1447, -0.0653, -0.0333, -0.0014,  0.0821,  0.0029, -0.1204, -0.1932, -0.299,  -0.4563}; // A,6
	private static double[] EV_S18 = {-0.0297,  0.0517,  0.0812,  0.1096,  0.2075,  0.3349,  0.0457, -0.1451, -0.2508, -0.3968}; // A,7
	private static double[] EV_S19 = { 0.2349,  0.3078,  0.3285,  0.3467,  0.409,   0.5514,  0.5336,  0.2313, -0.0709, -0.1469}; // A,8
	private static double[] EV_S20 = { 0.4885,  0.5537,  0.5664,  0.5775,  0.6169,  0.7086,  0.7315,  0.7021,  0.3827,  0.1147}; // A,9
	private static double[] EV_S21 = {      1,       1,       1,       1,       1,       1,       1,       1,  0.9231,  0.6923}; // A,10
	private static double[] EV_SpA = { 0.1678,  0.3247,  0.3767,  0.4291,  0.4934,  0.3336,  0.2301,  0.1152, -0.0925, -0.3442}; // A,A
	private static double[] EV_Sp2 = {-0.2546, -0.1755, -0.1407, -0.0972, -0.0063, -0.1128, -0.1957, -0.2744, -0.3752, -0.5018}; // 2,2
	private static double[] EV_Sp3 = {-0.2821, -0.2007, -0.1647, -0.1264, -0.0413, -0.1701, -0.251,  -0.3241, -0.4179, -0.5358}; // 3,3
	private static double[] EV_Sp4 = {-0.1649, -0.0859, -0.0535, -0.0218,  0.0288,  0.0334, -0.1054, -0.2527, -0.3466, -0.4678}; // 4,4
	private static double[] EV_Sp5 = { 0.0561,  0.2162,  0.2716,  0.3269,  0.4016,  0.2632,  0.1661,  0.0722, -0.0947, -0.2761}; // 5,5
	private static double[] EV_Sp6 = {-0.3549, -0.3005, -0.2791, -0.1879, -0.1013, -0.2463, -0.3028, -0.3691, -0.4557, -0.5666}; // 6,6
	private static double[] EV_Sp7 = {-0.4437, -0.293,  -0.2085, -0.1236, -0.0084, -0.1892, -0.3988, -0.456,  -0.5307, -0.6263}; // 7,7
	private static double[] EV_Sp8 = {-0.2968, -0.0992, -0.0211,  0.0551,  0.1806,  0.1681, -0.1596, -0.5014, -0.5953, -0.6778}; // 8,8
	private static double[] EV_Sp9 = {-0.0297,  0.0517,  0.1047,  0.1723,  0.2665,  0.3349,  0.1207, -0.1838, -0.2938, -0.4085}; // 9,9
	private static double[] EV_Sp10 = { 0.4885,  0.5537,  0.5664,  0.5775,  0.6169,  0.7086,  0.7315,  0.7021,  0.3827,  0.1147}; // 10,10
	private static double[][] EV = {EV_H5, EV_H6, EV_H7, EV_H8, EV_H9, EV_H10, EV_H11, EV_H12, EV_H13, EV_H14,
			EV_H15, EV_H16, EV_H17, EV_H18, EV_H19, EV_S13, EV_S14, EV_S15, EV_S16, EV_S17, EV_S18, EV_S19,
			EV_S20, EV_S21, EV_SpA, EV_Sp2, EV_Sp3, EV_Sp4, EV_Sp5, EV_Sp6, EV_Sp7, EV_Sp8, EV_Sp9, EV_Sp10}; 
			
	public Player(Strategy strategy, int dollars) {
		super(strategy, dollars, new HashMap<SideBetDescription, SideBetStrategy>());
	}
	
	public static boolean shouldSwitch(Hand firstHand, Hand secondHand, Card dealerUp){
		double firstHandPts = getHandEV(firstHand, dealerUp);
		double secondHandPts = getHandEV(secondHand, dealerUp);;
		Hand switchHandOne = new Hand(firstHand.cards.get(0), secondHand.cards.get(1));
		Hand switchHandTwo = new Hand(secondHand.cards.get(0), firstHand.cards.get(1));
		double firstSHandPts = getHandEV(switchHandOne, dealerUp);
		double secondSHandPts = getHandEV(switchHandTwo, dealerUp);
		
		if(firstSHandPts + secondSHandPts > firstHandPts + secondHandPts){
			return true;
		}else{
			return false;
		}
	}
	
	public static double getHandEV(Hand hand, Card dealerCard){
		if(hand.canSplit()){
			if(hand.firstCard().getBlackjackValue() == 11){
				return EV[24][dealerCard.getBlackjackValue() - 2];
			}else{
				int value = hand.firstCard().getBlackjackValue();
				return EV[23 + value][dealerCard.getBlackjackValue() - 2];
			}
		}else if(hand.isSoft()){
			return EV[hand.getValue() + 2][dealerCard.getBlackjackValue() - 2];
		}else{
			return EV[hand.getValue() - 5][dealerCard.getBlackjackValue() - 2];
		}
	}
	
	public static boolean shouldSwitchCL(Hand firstHand, Hand secondHand, Card dealerUp){
		int currentSum = getPointValue(firstHand, dealerUp) + getPointValue(secondHand, dealerUp);
		Hand switchHandOne = new Hand(firstHand.cards.get(0), secondHand.cards.get(1));
		Hand switchHandTwo = new Hand(secondHand.cards.get(0), firstHand.cards.get(1));
		int possibleSum = getPointValue(switchHandOne, dealerUp) + getPointValue(switchHandTwo, dealerUp);
		
		//Technically, should default to simple strategy in the event of a tie.
		if(possibleSum > currentSum){
			return true;
		}else if(possibleSum == currentSum){
			return shouldSimpleSplit(firstHand, secondHand, dealerUp, switchHandOne, switchHandTwo);
		}else{
			return false;
		}
	}
	
	private static boolean shouldSimpleSplit(Hand hand1, Hand hand2, Card dealerUp,
			Hand switchHandOne, Hand switchHandTwo){
		int handOneRank = getHandSimpleRank(hand1, dealerUp);
		int handTwoRank = getHandSimpleRank(hand2, dealerUp);
		int switchHandOneRank = getHandSimpleRank(switchHandOne, dealerUp);
		int switchHandTwoRank = getHandSimpleRank(switchHandTwo, dealerUp);
		//Against a 7 or 8, make the weaker hand as high as possible in rank
		if(dealerUp.getBlackjackValue() == 7 || dealerUp.getBlackjackValue() == 8){
			if(handOneRank == 1000 || handTwoRank == 1000 ||
					handOneRank != handTwoRank){
				if(handOneRank < handTwoRank){
					if(switchHandTwoRank < handTwoRank){
						return true;
					}
				}else if(handOneRank > handTwoRank){
					if(switchHandOneRank < handOneRank){
						return true;
					}
				}else{
					if(switchHandOneRank < handOneRank ||
							switchHandTwoRank < handTwoRank){
						return true;
					}
				}
			}
		}else{
			//Against anything but a 7 or 8, maximize higher ranked hand
			if(handOneRank == 1000 || handTwoRank == 1000 ||
					handOneRank != handTwoRank){
				if(handOneRank < handTwoRank){
					if(switchHandOneRank < handOneRank){
						return true;
					}
				}else if(handOneRank > handTwoRank){
					if(switchHandTwoRank < handTwoRank){
						return true;
					}
				}else{
					if(switchHandOneRank < handOneRank ||
							switchHandTwoRank < handTwoRank){
						return true;
					}
				}
			}
		}
		
		handOneRank = getHandSimpleSecondaryRank(hand1);
		handTwoRank = getHandSimpleSecondaryRank(hand2);
		switchHandOneRank = getHandSimpleSecondaryRank(switchHandOne);
		switchHandTwoRank = getHandSimpleSecondaryRank(switchHandTwo);
		if(handOneRank < handTwoRank){
			if(switchHandOneRank < handOneRank){
				return true;
			}
		}else if(handOneRank > handTwoRank){
			if(switchHandTwoRank < handTwoRank){
				return true;
			}
		}else{
			if(switchHandOneRank < handOneRank ||
					switchHandTwoRank < handTwoRank){
				return true;
			}
		}
		
		return false;
	}
	
	private static int getHandSimpleRank(Hand hand, Card dealerCard){
		if(hand.getValue() == 21){
			return 1;
		}else if(hand.getValue() == 20){
			return 2;
		}else if(hand.getValue() == 19){
			return 3;
		}else if(hand.cards.get(0).value == VALUE.ACE &&
				hand.cards.get(1).value == VALUE.ACE){
			return 4;
		}else if(hand.getValue() == 11){
			return 5;
		}else if(hand.getValue() == 10){
			return 6;
		}else if(hand.getValue() == 9){
			return 7;
		}else if(hand.getValue() == 18 || hand.getValue() == 8){
			return 8;
		}else if(hand.cards.get(0).value == VALUE.EIGHT &&
				hand.cards.get(1).value == VALUE.EIGHT &&
				dealerCard.getBlackjackValue() < 9){
			return 9;
		}else{
			return 1000;
		}
	}
	
	private static int getHandSimpleSecondaryRank(Hand hand){
		if(hand.getValue() == 7 || hand.getValue() == 17){
			return 1;
		}else if(hand.canSplit()){
			return 2;
		}else if(hand.getValue() == 12){
			return 3;
		}else if(hand.getValue() == 13){
			return 4;
		}else{
			return 1000;
		}
	}

	public static int getPointValue(Hand hand, Card dealerUp){
		int value = hand.getValue();
		Card card1 = hand.cards.get(0);
		Card card2 = hand.cards.get(1);
		if(value == 21){
			return 3;
		}else if(value >= 13 && value <= 17 && hand.isSoft()){
			return 1;
		}else if(card1.value == VALUE.ACE && card2.value == VALUE.ACE){
			return 2;
		}else if((card1.value == VALUE.THREE && card2.value == VALUE.THREE) ||
				(card1.value == VALUE.TWO && card2.value == VALUE.TWO)){
			return 1;
		}else if(card1.value == VALUE.NINE && card2.value == VALUE.NINE){
			if(dealerUp.getBlackjackValue() == 8){
				return 2;
			}else{
				return 1;
			}
		}else if(card1.value == VALUE.EIGHT && card2.value == VALUE.EIGHT){
			if(dealerUp.getBlackjackValue() == 7){
				return 2;
			}else if(dealerUp.getBlackjackValue() == 8 ||
					dealerUp.getBlackjackValue() == 6 ||
					dealerUp.getBlackjackValue() == 5){
				return 1;
			}else{
				return 0;
			}
		}else if(card1.value == VALUE.SEVEN && card2.value == VALUE.SEVEN){
			if(dealerUp.getBlackjackValue() == 6 ||
					dealerUp.getBlackjackValue() == 5){
				return 1;
			}else{
				return 0;
			}
		}else if(card1.value == VALUE.SIX && card2.value == VALUE.SIX){
			if(dealerUp.getBlackjackValue() == 6 ||
					dealerUp.getBlackjackValue() == 5 ||
					dealerUp.getBlackjackValue() == 4){
				return 1;
			}else{
				return 0;
			}
		}else if(value == 20){
			if(dealerUp.getBlackjackValue() == 7 ||
					dealerUp.getBlackjackValue() == 8 ||
					dealerUp.getBlackjackValue() == 9){
				return 3;
			}else{
				return 2;
			}
		}else if(value == 19){
			if(dealerUp.getBlackjackValue() == 7 ||
					dealerUp.getBlackjackValue() == 8){
				return 3;
			}else if(dealerUp.getBlackjackValue() == 9){
				return 2;
			}else{
				return 1;
			}
		}else if(value == 18){
			if(dealerUp.getBlackjackValue() == 7 ||
					dealerUp.getBlackjackValue() == 8){
				return 2;
			}else{
				return 1;
			}
		}else if(value >= 12 && value <= 17){
			return 0;
		} else if(value == 11 || value == 10){
			return 2;
		}else{ //value 9 and down, excluding split possibilities
			return 1;
		}
	}
}
