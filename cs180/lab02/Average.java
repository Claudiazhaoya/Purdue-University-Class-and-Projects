import java.util.Scanner;
public class Average{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter first number:");
		int a = input.nextInt();
		System.out.println("Enter second number:");
		int b = input.nextInt();
		System.out.println("Enter last number:");
		int c = input.nextInt();
		int sum = (a+b+c);
		int average = (sum/3);
		double frac_average = (sum/3.0);
		System.out.println("average with no fraction is:" + average);
		System.out.println("average with fractin is:" + frac_average);

		

	}
}
