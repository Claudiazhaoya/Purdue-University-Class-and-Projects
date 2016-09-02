import java.util.Scanner;

public class TaxBracket {
  //decaler all variables
	private int bracket10;
	private int bracket15;
	private int bracket25;
	private int bracket28;
	private int bracket33;
	private int bracket35;
	private int bracket40;
	private int totalIncomes;

	private int top10;
	private int top15;
	private int top25;
	private int top28;
	private int top33;
	private int top35;
	private int bot40;

	public TaxBracket() {
   //inilizle all variables
		bracket10 = 0;
		bracket15 = 0;
		bracket25 = 0;
		bracket28 = 0;
		bracket33 = 0;
		bracket35 = 0;
		bracket40 = 0;
		totalIncomes = 0;
	}
	public void addBracket(int income) {
   //assign values to the limits and assign tax bracket to income
		top10 = 9075;
		top15 = 36900;
		top25 = 89350;
		top28 = 186350;
		top33 = 405100;
		top35 = 406750;
		bot40 = 406750;
		this.totalIncomes++;
		if (income <= top10) {
			this.bracket10++;
		} else if (income >= (top10 + 1) && income <= top15) {
			this.bracket15++;
		} else if (income >= (top15 + 1) && income <= top25) {
			this.bracket25++;
		} else if (income >= (top25 + 1) && income <= top28) {
			this.bracket28++;
		} else if (income >= (top28 + 1) && income <= top33) {
			this.bracket33++;
		} else if (income >= (top33 + 1) && income <= top35) {
			this.bracket35++;
		} else if (income >= (top35 = 1)) {
			this.bracket40++;
		}
  
	}
	public static void main(String[] args) {
		TaxBracket bracket = new TaxBracket();
		Scanner in = new Scanner(System.in);
		int income = -1;
		while (true) {
			System.out.println("Enter an income:");
			income = in.nextInt();
			if (income < 0) {
				break;
			} else {
				bracket.addBracket(income);
   
			}
		}
  //print out the results
		System.out.println("Total incomes entered = " + bracket.totalIncomes);
		System.out.println("Number of 10% Bracket = " + bracket.bracket10);
		System.out.println("Number of 15% Bracket = " + bracket.bracket15);
		System.out.println("Number of 25% Bracket = " + bracket.bracket25);
		System.out.println("Number of 28% Bracket = " + bracket.bracket28);
		System.out.println("Number of 33% Bracket = " + bracket.bracket33);
		System.out.println("Number of 35% Bracket = " + bracket.bracket35);
		System.out.println("Number of 40% Bracket = " + bracket.bracket40);
  
	}
}
