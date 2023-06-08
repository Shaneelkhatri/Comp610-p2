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
	
	
}
