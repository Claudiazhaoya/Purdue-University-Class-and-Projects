public class CompoundInterest{
	static double out=0.0;
	static double calculate(double input, int year){
		if(input==0){
			return 0.0;
		}else if(year==0){
			return input;
		}else{
			out=(input*1.05);
			year--;
			calculate(out,year);
		}
		return out;
	}
	public static void main(String[] args){
		CompoundInterest test = new CompoundInterest();
		double output = test.calculate(1500,3);
		System.out.println(output);
	}
}
