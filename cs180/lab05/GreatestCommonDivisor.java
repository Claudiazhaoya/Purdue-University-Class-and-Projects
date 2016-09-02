import java.util.Scanner;
public class GreatestCommonDivisor{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the first number:");
		int first = in.nextInt();
		System.out.println("Enter the second number:");
		int second = in.nextInt();
		if(first>second){
			for(int i=first;i>=1;i--){
				if(first%i==0&&second%i==0){
					System.out.println("GCD is: "+i);
					break;
				}
			}
		}else{
			for(int i=second;i>=1;i--){
				if(second%i==0&&first%i==0){
					System.out.println("GCD is: "+i);
					break;
				}
			}
		}

	}
}
