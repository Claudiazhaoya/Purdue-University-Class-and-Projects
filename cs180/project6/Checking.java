public class Checking extends BankAccount {
	public Checking(String name, double balance) {
		super(name, "checking", balance);

	}

	public double withdraw(double ammount) {
		this.balance -= ammount;
		return this.balance;

	}

}
