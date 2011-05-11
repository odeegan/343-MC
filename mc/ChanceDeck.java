package mc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class ChanceDeck {

	Random generator = new Random();
	
	ArrayList<ChanceCard> chanceCards;
	ArrayList<ChanceCard> discardPile;
	
	
	public ChanceDeck() {
		chanceCards = new ArrayList<ChanceCard>();
		discardPile = new ArrayList<ChanceCard>();
		// init all the chance cards here
		// ...
//		// ...
		chanceCards.add(new ChanceCardEasyMoney());
		chanceCards.add(new ChanceCardAuctionUnowned());
		chanceCards.add(new ChanceCardInheritance());
		chanceCards.add(new ChanceCardGoToJail());
		chanceCards.add(new ChanceCardAdvanceToGo());
		chanceCards.add(new ChanceCardToxicWaste());
		chanceCards.add(new ChanceCardAdvanceToMiddleton());
		chanceCards.add(new ChanceCardAdvanceToDiamondHills());
		chanceCards.add(new ChanceCardPrisonOvercrowding());
		chanceCards.add(new ChanceCardSewagePlant());
		chanceCards.add(new ChanceCardFreeBuild());
		chanceCards.add(new ChanceCardRepossessed());
		chanceCards.add(new ChanceCardRailroad());
		chanceCards.add(new ChanceCardSteal());
		chanceCards.add(new ChanceCardEarthquake());
		// lots more ....
		
		
		// shuffle the deck
		Collections.shuffle(chanceCards);
	}

	public void addCard(ChanceCard card) {
		chanceCards.add(card);
		// TODO: the GetOutOfJail and RentDodge cards should only be discarded
		// after they've been drawn and used. Make sure the logic in this class
		// takes this into consideration. 
		}
	
	
	public ChanceCard drawCard() {
		
		if (chanceCards.size() == 0) {
			// Move cards from the discard pile back to the deck.
			// We can't simply instantiate a new deck, because players may
			// still have cards in their possession.
			//chanceCards = discardPile;
			for(ChanceCard card: discardPile)
				chanceCards.add(card);
			
			
			// shuffle the new deck
			//Collections.shuffle(chanceCards);
			
			// empty the discardPile
			discardPile.clear();
			
			// draw a card from the new deck
			// recursion baby, woot! woot!
			return drawCard();
		} else {
			int rand = generator.nextInt(chanceCards.size());
		
			// get the card at the random index
			ChanceCard card = chanceCards.get(rand);
		
			// remove the card fromdi the chanceCards ArrayList
			chanceCards.remove(rand);
		
			if (card.name != "Get Out of Jail" && card.name != "Rent Dodge") {
				discard(card);
			}
			return card;
		}
	}
	
	public void discard(ChanceCard card) {
		discardPile.add(card);
	}
	
	
}
