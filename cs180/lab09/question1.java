import java.util.ArrayList;
public class question1{
	static void print(int input){
		int in = input;
		ArrayList<Integer> reverse = new ArrayList<Integer>();
		do{
			System.out.print(in%10);
			in = in/10;
		}while(in>0);
	}
	public static void main(String[] args){
		question1 test = new question1();
		test.print(123456789);
	}
}
