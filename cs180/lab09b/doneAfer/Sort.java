import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Sort{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int number = 0;
		StringBuilder test = new StringBuilder();
		while(number>=0){
			System.out.println("Enter a non negative number");
			number = in.nextInt();

			if(number<0){
				break;
			}else{
			test.append(number);
			}
		}
		String out = test.toString();
		int[] array = new int[out.length()];
		int length = out.length();
		for(int i=0;i<out.length();i++){
			array[i] = Character.digit(out.charAt(i),10);
		}
		int size = array.length;
		for(int i=1; i< length;i++){
			for(int x =0; x<length-i;x++){
				if(array[x]>array[x+1]){
					
					int temp = array[x];
					array[x] = array[x+1];
					array[x+1] = temp;
				}
			}
		}
		for(int i=0;i<length;i++){
			System.out.print(array[i]);
		}


	}
}
