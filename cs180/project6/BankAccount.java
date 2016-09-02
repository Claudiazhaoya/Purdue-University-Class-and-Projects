/**
  Project 1 banking system
  This project creats two bank account objects 
  and transfers money from one bank account to the other
  @author Brian Shrawder
  @lab section
  @date 
 */

import java.util.Scanner;

/**
 * The name of the account holder and intitial balance for each account provided
 * as input from the user The account number of each is assigned randomly in the
 * constructor The method balance returns the balance in an account The method
 * transfer deducts money from one account and adds it to another The amount to
 * be deducted is passed as an argument to transfer from manin
 */
public class BankAccount {
	/**
	 * Name of the Account holder
	 */
	String name;

	/**
	 * account number of the account
	 */
	String type;

	/**
	 * balance in account;
	 */
	double balance;

	/**
	 * Constructor to assign values for instance variables name assigned using
	 * the passed argument balance assigned using the passed arugment account
	 * number is assinged a random integer value with 6 digits between 100000 -
	 * 999999;
	 * 
	 * @param name
	 *            the account holders name
	 * @param balance
	 *            the account balance
	 */

	public BankAccount(String name, String type, double balance) {
		long min = 100000;
		long max = 999999;
		this.name = name;
		this.balance = balance;
		this.type = type;
	}

	/*
	 * Returns the account holders name
	 * 
	 * @returns a string containing no whitespace
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * Returns the account balance
	 * 
	 * @returns a double value of account balance
	 */
	public double getBalance() {
		return this.balance;
	}

	public String getType() {
		return this.type;
	}

	/*
	 * preform money transfer from one account to another
	 * 
	 * @param destination acount
	 * 
	 * @param amount
	 */
	public void transfer(BankAccount destinationBankAccount, double ammount) {
		double test = (this.balance - ammount);
		this.balance = this.balance - ammount;
		if (test < 0) {
			System.out.println("Error not enough funds");
		} else {
			destinationBankAccount.balance += ammount;
		}
	}

	public void deposit(double ammount) {
		this.balance += ammount;
	}

	public void print(BankAccount currentAccount) {
		System.out.print("Name: " + currentAccount.getName());
		System.out.print(" Balance: " + currentAccount.getBalance());
		System.out.println();
	}

	public double withDraw(double ammount) {
		this.balance -= ammount;
		return this.balance;
	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String type = "normal";
		System.out.println("Enter name for the new BankAccount Holder:");
		String name = in.nextLine();
		System.out.println("Enter the initial deposit ammount:");
		double start = (double) in.nextDouble();
		in.nextLine(); // eat newline left over
		BankAccount first = new BankAccount(name, type, start);
		// print statment after first account input
		first.print(first);

		// second account
		System.out.println("Enter name for the new BankAccount Holder:");
		name = in.nextLine();
		System.out.println("Enter the initial deposit ammount:");
		start = in.nextDouble();
		in.nextLine();
		BankAccount second = new BankAccount(name, type, start);
		// output
		second.print(second);

		System.out.println("Enter the amount to be transfered:");
		System.out.println("It should be less than " + first.getBalance());
		int ammount = in.nextInt();
		if (ammount > 1000) {
			System.out.println("Error more than ammount possible");
		}
		first.transfer(second, ammount);
		first.print(first);
		second.print(second);

	}
}
