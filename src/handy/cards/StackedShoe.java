package handy.cards;

import java.util.List;

public class StackedShoe extends Shoe {

	public StackedShoe(int deckCount, List<Card> excludedCards) {
		super(deckCount);
		for (Card card : excludedCards){
			for(Card shoeCard : shoeList){
				if(shoeCard.suite == card.suite &&
						shoeCard.value == card.value){
					shoeList.remove(shoeCard);
					break;
				}
			}
		}
	}

}
