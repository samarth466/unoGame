package unoGameProject;

// Import Java modules
import java.util.ArrayList;
import java.util.Scanner;

// Class header
public class Player extends Person {
	
	// Constructor
	public Player(String name) {
		super(name);
	}
	
	public String printHand() {
		String output = "";
		for (int i=0; i<super.getHand().getHand().size(); i++) {
			output += i+1 + ": " + super.getHand().getHand().get(i);
		}
		return(output);
	}
	
	private void printTopCard(Card topCard, Color currentColor) {
		System.out.println("The top card is: \n" + topCard);
		if (topCard.getColor() == Color.UNDEFINED) {
			System.out.println("The wild color is " + currentColor);
		}
	}
	
	// Allows the user to pick a card to play
	@Override
	public Color chooseCard(Card topCard, Deck deck, Deck discardPile, Color currentColor, boolean light) {
		Scanner input = new Scanner(System.in);
		int cardIndex = -1;
		boolean getNum = true;
		printTopCard(topCard,currentColor);
		while (getNum) {
			try {
				System.out.println("Pick a number corresponding with the card that you'd like to play or -1 to draw a card. Press 0 to hear the top card on the discard pile. Your hand is:\n" + printHand());
				cardIndex = input.nextInt();
				input.nextLine();
				if (cardIndex == -1) {
					ArrayList<Card> temp = super.getHand().getHand();
					super.getHand().takeCardFromDeck(deck);
					for (Card card: super.getHand().getHand()) {
						if (!temp.contains(card)) {
							System.out.println("You drew: " + card);
							if (cardIsPlayable(topCard,currentColor, card)) {
								System.out.println("Press 1 if you'd like to play this card, 0 otherwise");
								int userChoice = input.nextInt();
								input.nextLine();
								if (userChoice == 1) {
									super.getHand().removeCard(card);
									discardPile.add(card);
									super.getHand().removeCard(card);
									if (card.getColor() == Color.UNDEFINED) {
										input.close();
										return(chooseColor(light));
									} else {
										input.close();
										return(card.getColor());
									}
								} else {
									System.out.println("Your turn is over. You have " + super.getHand().getNumberOfCards() + " cards.");
								}
							} else {
								System.out.println("You cannot play this card. Your turn is over. You have " + super.getHand().getNumberOfCards() + " cards.");
								input.close();
								return(Color.UNDEFINED);
							}
						}
					}
				} else if (cardIndex == 0) {
					printTopCard(topCard,currentColor);
				} else if (cardIndex <= super.getHand().getNumberOfCards() && cardIndex > 0) {
					Card cardToPlay = super.getHand().getHand().get(cardIndex);
					if (cardIsPlayable(topCard,currentColor,cardToPlay)) {
						discardPile.add(cardToPlay);
						super.getHand().removeCard(cardToPlay);
						if (cardToPlay.getColor() == Color.UNDEFINED) {
							input.close();
							return(chooseColor(light));
						} else {
							input.close();
							return(cardToPlay.getColor());
						}
					}
				}
			} catch (Exception e) {
				//System.out.println(e);
				System.out.println("Invalid!");
				input.nextLine();
			}
		}
		return(Color.UNDEFINED);
	}
	
	public boolean cardIsPlayable(Card topCard, Color currentColor, Card cardToPlay) {
		if (cardToPlay.getCardValue().cardName().contains("Wild")) {
			return(true);
		} else if (topCard.getCardValue().cardName().contains("Wild") && currentColor == cardToPlay.getColor()) {
			return(true);
		} else if (topCard.getColor() == cardToPlay.getColor()) {
			return(true);
		} else if (topCard.getCardValue() == cardToPlay.getCardValue()) {
			return(true);
		} else {
			return(false);
		}
	}
	
	public Color chooseColor(boolean light) {
		Scanner input = new Scanner(System.in);
		Color color;
		String output = "Press ";
		if (light) {
			for (int i=0; i<CardType.LIGHT.colors().size()-1; i++) {
				output += i+1 + " for " + CardType.LIGHT.colors().get(i) + ", ";
			}
			output += CardType.LIGHT.colors().size() + " for " + CardType.LIGHT.colors().get(CardType.LIGHT.colors().size()-1) + ".";
		} else {
			for (int i=0; i<CardType.DARK.colors().size()-1; i++) {
				output += i+1 + " for " + CardType.DARK.colors().get(i) + ", ";
			}
			output += CardType.DARK.colors().size() + " for " + CardType.DARK.colors().get(CardType.LIGHT.colors().size()-1) + ".";
		}
		while(true) {
			try {
				System.out.println(output);
				int colorIndex = input.nextInt();
				input.nextLine();
				if (light) {
					color = CardType.LIGHT.colors().get(colorIndex-1);
					break;
				} else {
					color = CardType.DARK.colors().get(colorIndex-1);
					break;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Invalid input. Try again.");
			}
		}
		input.close();
		return(color);
	}
	
}
