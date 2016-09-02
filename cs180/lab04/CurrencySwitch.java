import java.util.Scanner;
public class CurrencySwitch{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String input;
		System.out.println("ENTER THE TYPE OF COIN: ");
		input = in.nextLine();
		switch(input){
			case "penny":
				System.out.println("Value of a penny is: "+"1");
				break;
			case "nickel":
				System.out.println("Value of a nickel is: "+"5");
				break;
			case "dime":
				System.out.println("Value of a dime is: "+"10");
				break;
			case "quarter":
				System.out.println("Value of a quarter is: "+"25");
				break;
			case "half-dollar":
				System.out.println("Value of a half-dollar is: "+"50");
				break;
			case "dollar":
				System.out.println("Vaule of a dollar is: "+"100");
				break;
			default:
				System.out.println("Unkown coin");
				break;
		}
	}
}
