import java.util.Scanner;
import java.util.ArrayList;
public class Question1{
	public ArrayList tasks(){
		Scanner in = new Scanner(System.in);
		ArrayList<String> data = new ArrayList<String>();
		while(true){
			System.out.println("Enter your next task, enter done to stop");
			String input = in.nextLine();
			if(input.matches("done")){
				break;
			}else{
			data.add(input);
			}
		}
		return data;
	}
	public static void main(String[] args){
		ArrayList<String> output = new ArrayList<String>();
		Question1 test = new Question1();
		output = test.tasks();
		System.out.println("your daily tasks are:");
		for(int i=0;i<output.size();i++){
			System.out.println(output.get(i));
		}
	}
}
