import java.util.Scanner;
public class Initials{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		String first_name;
		String last_name;
		String birth;
		String email;
		System.out.println("Enter your first name:");
		first_name = input.nextLine();
		System.out.println("Enter your last name:");
		last_name = input.nextLine();
		System.out.println("Enter your date of birth (MM-DD-YY):");
		birth = input.nextLine();
		String sub1 = first_name.substring(0,1);
		String sub2 = last_name.substring(0,1);
		String sub3 = birth.substring(3,5);
		String sub4 = birth.substring(0,2);
		email = sub1;
		email = email.concat(sub2);
		email = email.toLowerCase();
		email = email.concat(sub3);
		email = email.concat(sub4);
		email = email.concat("@gmail.com");
		System.out.println(email);
	
	}
}
