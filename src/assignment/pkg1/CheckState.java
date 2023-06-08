package assignment.pkg1;

import java.util.Scanner;

public class CheckState {

	public CheckState()
	{
		
	}
	
	public String Hit_Stand()
	{
		String choice = "1";
		System.out.println();
		System.out.println("Do you want to hit (1) or stand (2)?");
		Scanner hit = new Scanner(System.in);
		choice = hit.nextLine();

		// make sure you can only hit or stay
		while(!(choice.equalsIgnoreCase("2") || choice.equalsIgnoreCase("1")))
		{
			System.out.println("Do you want to hit (1) or stand (2)?");
			choice = hit.nextLine();
		}
		//hit.close();
		return choice;
	}
}
