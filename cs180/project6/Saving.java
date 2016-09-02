public class Saving extends BankAccount {

	public Saving(String name, double balance) {

		super(name, "saving", balance * 1.03);
	}

	public void deposit(String name, double balance) {
		BankAccount test = new Saving(name, balance * 1.03);
		super.deposit(balance);
	}
}
