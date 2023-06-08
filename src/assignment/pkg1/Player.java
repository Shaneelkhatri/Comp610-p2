package assignment.pkg1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Player {

	private String name;
	private int pot;

	private Card card1;
	private Card card2;
	private int total;

	public Player(String pName) throws Exception
	{
		name = pName;

		//File file = new File(System.getProperty("user.dir") + "\\src\\assignment\\pkg1\\input.txt");
		//Scanner sc = new Scanner(file);

		//while (sc.hasNextLine())
		//	pot = Integer.parseInt(sc.nextLine());
		//sc.close();
		pot = ConnectionDerby.getScore();
	}

	public void BlackJack(int bet)
	{
		pot +=  bet + (bet * 0.15f);
	}

	public void Win(int bet)
	{
		pot += bet; 
	}

	public void Loss(int bet)
	{
		pot -= bet;
	}

	public String getName() {
		return name;
	}

	public int getPot() {
		return pot;
	}

	public Card getCard1() {
		return card1;
	}

	public void setCard1(Card card1) {
		this.card1 = card1;
	}

	public Card getCard2() {
		return card2;
	}

	public void setCard2(Card card2) {
		this.card2 = card2;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void reset() { card1 = null; card2 = null; total = 0; }


	public void saveBalance() {
		new SavingPrompt();

		//saving into BDD
		ConnectionDerby.saveScore(getPot());

		//saving into a file
		String filePath = System.getProperty("user.dir") + "\\src\\assignment\\pkg1\\input.txt";

		try {
			FileWriter fileWriter = new FileWriter(filePath);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			String data = String.valueOf(getPot());
			bufferedWriter.write(data);
			// close the writers to release resources
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred while writing to the file.");
			e.printStackTrace();
		}

	}
}