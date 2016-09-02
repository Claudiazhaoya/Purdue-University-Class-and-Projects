import java.util.Scanner;
public class Currency{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String input;
		System.out.println("ENTER THE TYPE OF COIN: ");
		input = in.nextLine();
		if(input.equals("penny")){
			System.out.println("Value of a penny is: "+"1");
		}else if(input.equals("nickel")){
			System.out.println("Value of a nickel is: "+"5");
		}else if(input.equals("dime")){
			System.out.println("Value of a dime is: "+"10");
		}else if(input.equals("quarter")){
			System.out.println("Value of a quarter is: "+"25");
		}else if(input.equals("half-dollar")){
			System.out.println("Value of a half-dollar is: "+"50");
		}else if(input.equals("dollar")){
			System.out.println("Value of a dollar is: "+"100");
		}else{
			System.out.println("unkown coin");
		}
	}
}
