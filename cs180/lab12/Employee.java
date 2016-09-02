public class Employee{
	private double medicare = 1.45;
	private double socialTax = 6.2;

	public double getMediare(){return medicare;}
	public double getSocialTax(){return socialTax;}

	public double taxPayable(double salary){
		double tax=0.0;
		double temp=0;
		//normal test;
		tax+=salary*.062;
		tax+=salary*.0145;
		if(salary>=0){
			if(salary>7825){
				tax+= 7825*.1;
			}else{
				tax += salary*.1;
			}
			temp = salary-7825;
		}if(salary>=7826){
			if(salary>31850){
				tax+=31850*.15;
			}else{
				tax+=temp*.15;
			}
			temp = salary-31850;
		}if(salary>=31851){
			if(salary>77100){
				tax+=77100*.25;
			}else{
				tax+=temp*.25;
			}
			temp = salary-77100;
		}if(salary>=77101){
			if(salary>160850){
				tax+=160850*.28;
			}else{
				tax+=temp*.28;
			}
			temp = salary-160850;
		}if(salary>=160851){
			if(salary>349700){
				tax+=349700*.33;
			}else{
				tax+=temp*.33;
			}
			temp = salary-349700;
		}if(salary>=349701){
			tax+=temp*.35;
		}
		return tax;
	}
}
