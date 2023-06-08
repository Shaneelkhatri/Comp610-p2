package assignment.pkg1;


public class Shows {

	public Shows() {

	}


	public void printCard(Card card)
	{
		System.out.println(card.getName());
	}


	public void printPlayerValue(Player player)
	{
		System.out.println("Your hand is valued at:");
		System.out.println(player.getTotal());
		System.out.println();
	}
	
	public void printDealerValue(Dealer dealer)
	{
		System.out.println("The dealers' hand is valued at:");
		System.out.println(dealer.getTotal());
		System.out.println();
	}
}
