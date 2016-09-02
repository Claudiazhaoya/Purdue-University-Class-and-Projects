import java.util.Scanner;
public class SplitDigitsFixed{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter a four digit number");
		int input = 0;
		input = in.nextInt();
		int[] out = new int[4];
		for(int i=4;i>0;i--){
			out[i-1] = input%10;
			input = input/10;
		}
		for(int j=0;j<out.length;j++){
			System.out.println(out[j]);
		}
	}
}
