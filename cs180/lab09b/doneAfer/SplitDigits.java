import java.util.Scanner;
import java.util.ArrayList;
public class SplitDigits{
	public static void main(String[] args){
		
		System.out.println("Enter a number");
		

		
		int input = 0;
		
		while(true){
			try{
				Scanner in = new Scanner(System.in);
				input = in.nextInt();
				break;
			}catch(Exception e){
				System.out.println("Input Error");
				
			}
			
		}
		//System.out.println(length);
		ArrayList<Integer> out = new ArrayList<Integer>();
		do{
			out.add(input%10);
			input = input/10;
		}while(input>0);
		int outSize = out.size()-1;
		for(int i=outSize;i>=0;i--){
			System.out.println(out.get(i));
		}
	}
}
		
