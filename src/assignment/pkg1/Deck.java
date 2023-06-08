package assignment.pkg1;


import java.util.Random;

public class Deck {
	Random random = new Random();


	private String[] cards = new String[] {
			"ACE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT","NINE","Ten", "Jack", "Queen", "King" };


    public Card nextCard()
	{
		int value = random.nextInt(13) + 1;
		int suite = random.nextInt(4) + 1;
		Card card = new Card(value, suite);
		return card;
	}


    public void kindsCard(int n)
    {
		switch(n) {
		case 0:
			System.out.print("SPADES-");
			break;
		case 1:
			System.out.print("HEARTS-");
			break;
		case 2:
			System.out.print("DIAMONDS-");
			break;
		default:
			System.out.print("CLUBS-");
			break;
		}
    }
    
    public void printNumber(int n)
    {
    	System.out.println(cards[n]);
    }
    
}
 