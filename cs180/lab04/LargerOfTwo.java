import java.util.Scanner;
public class LargerOfTwo{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		double first;
		double second;
		System.out.println("ENTER FIRST NUMBER");
		first = in.nextDouble();
		System.out.println("ENTER SECOND NUMBER");
		second = in.nextDouble();
		if(first>second){
			System.out.println("THE BIGGER NUMBER IS: "+first);
		}else if(second>first){
			System.out.println("THE BIGGER NUBMER IS: "+second);
		}else{
			System.out.println("THE NUMBERS ARE THE SAME:");
		}
	}
}
