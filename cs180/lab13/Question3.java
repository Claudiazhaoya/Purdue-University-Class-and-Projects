import java.util.Scanner;
public class Question3{
	static boolean attempt(String input){
		if(input.length()==1){
			return true;
		}
		if(input.charAt(0)==input.charAt(input.length()-1)||input.charAt(0)-32==input.charAt(input.length()-1)||input.charAt(0)+32==input.charAt(input.length()-1)){
			return (attempt(input.substring(1,input.length()-1)));
		}
		return false;
	}
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter string: ");
		String in = input.nextLine();
		Question3 test = new Question3();
		boolean first = test.attempt(in);
		if(first==true){
			System.out.println(input+" is a palandrome");
		}else{
			System.out.println(input+" is not a palandrome");
		}
	}
}
