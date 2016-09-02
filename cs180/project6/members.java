
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class members {
	private Scanner in;
	Queue<String> temp; 
	public boolean checker(String input){
		if(!input.contains(",")){
			return false;
		}
		if(!input.matches(".*\\d+.*")){
			return false;
		}

		int start = input.indexOf(",");
		String Name=input.substring(0,start);
		if(Name.contains("Lastname")||!Name.contains(" ")){
			return false;
		}
		int middle = input.lastIndexOf(',');
		String type = input.substring(start+1,middle);
		if(type.contains(",")){
			return false;
		}

		if(!type.matches("checking")&&!type.matches("saving")){
			return false;
		}
		String end = input.substring(middle+2,input.length());
		double check = Double.parseDouble(end);
		if(check<100.00){
			return false;
		}
		System.out.printf("name: %s type: %s ammount: %s\n", Name, type,end);
		
		return true;
	}
	public String InBank(){
		
		temp= new LinkedList<String>();
		try{
			in = new Scanner(new File("C:\\Users\\Brian\\Desktop\\data.txt"));
		}catch(Exception e){
			System.out.println("Invalid FIle Name");
		}
		while(in.hasNextLine()&&temp.size()<25){
			String a=in.nextLine();
			if(this.checker(a)){
				temp.add(a);
			}
		}
		in.close();
		return "test";
	}	
}
