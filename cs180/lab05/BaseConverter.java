import java.util.Scanner;
public class BaseConverter{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the base 10 number to be converted:");
		int input = in.nextInt();
		String out ="";
		while(input!=0){
			out = input%3 + out;
			input = input/3;
		}
		System.out.println(out);
	}
}
