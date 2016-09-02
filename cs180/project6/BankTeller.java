import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BankTeller implements Runnable {
	private Thread t;
	private Thread t2;
	static ArrayList<BankAccount> allAccounts = new ArrayList<BankAccount>();
	ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
	static ArrayList<String> roger = new ArrayList<String>();
	int delay = 0;
	members test = new members();
	static Queue<String> data = new LinkedList<String>();
	static Queue<String> transaction = new LinkedList<String>();
	private Scanner in;
	private static Scanner input;
	static boolean keep = true;

	public BankTeller() {

		// Runnable t1 = new BankTeller(1);
		// Runnable t2 = new BankTeller(2);
		// System.out.println("t1");
		// t1.run();
		// System.out.println("t2");
		// t2.run();
		// System.out.println("main");
		t = new Thread(this);
		t2 = new Thread(this);
		t.start();
		t2.start();
		this.inBank();

		// this.run();
	}

	public void makeAccount(String input) {
		if (input == null) {
			return;
		}
		// System.out.println(input);
		if (!input.contains(",")) {
			return;
		}
		int start = input.indexOf(",");
		String name = input.substring(0, start);
		int middle = input.lastIndexOf(',');
		String type = input.substring(start + 1, middle);
		String end = input.substring(middle + 2, input.length());
		double ammount = Double.parseDouble(end);
		// System.out.println(type);
		// BankAccount temp = new BankAccount(Name,type,ammount);
		if (type.matches("saving")) {
			Saving temp = new Saving(name, ammount);
			allAccounts.add(temp);
		} else if (type.matches("checking")) {
			Checking temp1 = new Checking(name, ammount);
			allAccounts.add(temp1);
		}
		// this.BankAccounts.add(temp);

	}

	public boolean checker(String input) {
		if (!input.contains(",")) {
			return false;
		}
		if (!input.matches(".*\\d+.*")) {
			return false;
		}

		int start = input.indexOf(",");
		String name = input.substring(0, start);
		if (name.contains("Lastname") || !name.contains(" ")) {
			return false;
		}
		int middle = input.lastIndexOf(',');
		String type = input.substring(start + 1, middle);
		if (type.contains(",")) {
			return false;
		}

		if (!type.matches("checking") && !type.matches("saving")) {
			return false;
		}
		String end = input.substring(middle + 2, input.length());
		double check = Double.parseDouble(end);
		if (check < 100.00) {
			return false;
		}
		// System.out.printf("name: %s type: %s ammount: %s\n", Name, type,end);

		return true;
	}

	public String inBank() {

		// temp= new LinkedList<String>();
		try {
			in = new Scanner(new File("C:\\Users\\Brian\\Desktop\\data.txt"));
		} catch (Exception e) {
			System.out.println("Invalid FIle Name");
		}
		while (in.hasNextLine()) {
			while (data.size() < 25) {
				String a = in.nextLine();
				if (this.checker(a)) {
					data.add(a);
					// System.out.println("added");
				}
			}
		}
		keep = false;
		in.close();
		return "test";
	}

	public void timeLag(Thread t, int time) {
		try {
			t.sleep(time*1000); // ////CHANGE THIS BACK
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Checking example = new Checking(null, balance);
	@Override
	public void run() {
		// BankAccounts = new ArrayList<BankAccount>();
		while (keep = true) {
			Object monitor = new Object();
			if (data.peek() == null) {
			} else {
				String out = data.remove();
				makeAccount(out);
			}
			timeLag(t, 1);
			timeLag(t2, 2);
			// allAccounts.addAll(this.BankAccounts);
		}
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// (new Thread(new BankTeller())).start();
		BankTeller test = new BankTeller();
		try {
			input = new Scanner(new File(
					"C:\\Users\\Brian\\Desktop\\transactions.txt"));
		} catch (Exception e) {
			System.out.println("Invalid File Name");
		}
		while (input.hasNextLine()) {
			String b = input.nextLine();
			transaction.add(b);
			// System.out.println(b);
		}
		input.close();

		// System.out.println("TRY :: "+allAccounts.get(1).getName()+" "+allAccounts.get(1).getType());
		String sytax = "***************************\n"
				+ "Transaction Unsuccessful\n";
		sytax += "Reason: Pleas pertain to the requested format\n"
				+ "***************************\n";
		while (!transaction.isEmpty()) {
			String temp = transaction.remove();
			//System.out.println(temp);
			if (!temp.contains(",")) {
				System.out.print(sytax);

			} else if (!temp.matches(".*\\d+.*")) {
				System.out.println(sytax);
			} else {
				int first = temp.indexOf(",");
				String name = temp.substring(0, first);
				String temp2 = temp.substring(first + 1, temp.length());
				if (!temp2.contains(",")) {
					if (name.matches(".*\\d+.*")) {
						//System.out.println("Deposite");
					} else {
						// for(int j=0;j<allAccounts.size()-1;j++){
						// System.out.println(allAccounts.get(j).name);
						// if(allAccounts.get(j).name.matches(name)){
						// if(!allAccounts.get(j).getType().matches("checking")){
						// System.out.println("can not withdraw");
						// }else{
						// double ammount = Double.parseDouble(temp2);
						// double left=allAccounts.get(j).withDraw(ammount);
						// System.out.println("remaining: "+left);
						// }
						// }
						// }
						// for(int k=0;k<allAccounts.size()-1;k++){
						// if(allAccounts.get(k).name.matches(temp2)){
						// double ammount = Double.parseDouble(name);
						// allAccounts.get(k).Deposit(ammount);
						// System.out.println("new ammount: "+allAccounts.get(k).getBalance());
						// }
						// }
						//System.out.println("withdraw");
					}
				} else {
					//System.out.println("transfer");
				}
			}
		}
	}

}
