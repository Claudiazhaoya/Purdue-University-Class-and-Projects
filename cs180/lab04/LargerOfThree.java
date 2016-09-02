import java.util.Scanner;
public class LargerOfThree{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		double first;
		double second;
		double third;
		System.out.println("ENTER THE FIRST NUBMER: ");
		first = in.nextDouble();
		System.out.println("ENTER THE SECOND NUMBER: ");
		second = in.nextDouble();
		System.out.println("ENTER THE THIRD NUMBER: ");
		third = in.nextDouble();

		if(first>second){
			if(first>third){
				System.out.println("THE LARGER NUMBER IS: "+first);
			}else if(third>first){
				System.out.println("THE LARGER NUMBER IS: "+third);
			}
		}else if(second>first){
			if(second>third){
				System.out.println("THE LARGER NUMBER IS: "+second);
			}else if(third>second){
				System.out.println("THE LARGER NUMBER IS: "+third);
			}
		}else if(third>first){
			if(third>second){
				System.out.println("THE LARGER NUMBER IS: "+third);
			}else if(second>third){
				System.out.println("THE LARGER NUMBER IS: "+second);
			}
		}else if(first==second){
			if(first==third){
				System.out.println("ALL NUMBERS ARE THE SAME: ");
			}
		}
	}
}
