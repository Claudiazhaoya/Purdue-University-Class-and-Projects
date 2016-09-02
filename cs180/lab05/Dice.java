import java.util.Scanner;
public class Dice{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int input = in.nextInt();
		int[] die_1 = new int[input];
		int[] die_2 = new int[input];
		int range = (6-1)+1;
		for(int i=0;i<input;i++){
			die_1[i]=(int)(Math.random()*range)+1;
			die_2[i]=(int)(Math.random()*range)+1;
		}
		System.out.println(input+" throws of a pair of dice resutls in:");
		System.out.println("<die 1, die 2>");
		for(int j=0;j<input;j++){
			System.out.print(die_1[j]+", ");
			System.out.println(die_2[j]);
		}
	}
}
