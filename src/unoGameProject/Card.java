package unoGameProject;

// Class header
public class Card {
	
	// Initialize instance variables
	private Color color;
	private NumberOrAction cardValue;
	private CardType cardType;
	
	// Constructor
	public Card(Color c, NumberOrAction v) {
		color = c;
		cardValue = v;
		assignCardType();
	}
	
	// Constructor
	public Card(Color c, NumberOrAction v, CardType t) {
		color = c;
		cardValue = v;
		cardType = t;
	}
	
	// Constructor
	public Card(Card card) {
		color = card.getColor();
		cardValue = card.getCardValue();
		cardType = card.getCardType();
	}
	
	// Getter methods
	
	// Returns the color of the card
	public Color getColor() {
		return(color);
	}
	
	// Gets the value of the card
	public NumberOrAction getCardValue() {
		return(cardValue);
	}
	
	// Gets the number of points the card is worth
	public int getScore() {
		return(cardValue.score());
	}
	
	// Gets the type of the card
	public CardType getCardType() {
		return(cardType);
	}
	
	// Sets the card's type based on its color
	private void assignCardType() {
		if (cardValue == NumberOrAction.WILD_DRAW_TWO) {
			cardType = CardType.LIGHT;
		} else if (cardValue == NumberOrAction.WILD_DRAW_COLOR) {
			cardType = CardType.DARK;
		} else {
			for (CardType value: CardType.values()) {
				if (value.colors().contains(color)) {
					cardType = value;
					break;
				}
			}
		}
	}
	
	// Returns a String representation of the Card
	public String toString() {
		return(getCardValue() + " " + getColor());
	}
	
}
