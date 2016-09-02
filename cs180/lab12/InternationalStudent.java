public class InternationalStudent extends Student{
	public double tax(double salary){
		if(salary>=50000){
			double temp=0.0;
			salary = salary-50000;
			temp = super.payable;
			return temp;
			
		}else{
			return "NONE";
		}
	}
	public static void main(String[] args){
		InternationalStudent test = new InternationalStudent();
		double out = test.tax(50000);
		System.out.println(out);
	}
}
