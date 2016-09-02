import java.util.Scanner;
public class Sum{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter first number:");
		int a = input.nextInt();
		System.out.println("Enter second number:");
		int b = input.nextInt();
		System.out.println("Enter last number:");
		int c = input.nextInt();
		int output = (a+b+c);
		System.out.println("the sum of"+ " "+a +" "+ b + " "+ c +" is: " +output);

	}
}
