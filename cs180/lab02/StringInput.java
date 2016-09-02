import java.util.Scanner;
public class StringInput{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter you favorite food:");
		String food = input.nextLine();
		System.out.println("Your favorite food is: "+food);
		System.out.println("Enter your full name:");
		String full_name = input.nextLine();
		System.out.println("Your full name is: " + full_name);
	}
}
