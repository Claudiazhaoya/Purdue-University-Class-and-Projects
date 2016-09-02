public class NumDigits{
	static int number=0;
	static int digits(int input){
		if(input==0){
			return 0;
		}else{
			number++;
			digits(input/10);
		}
		return number;
	}
	public static void main(String[] args){
		NumDigits test = new NumDigits();
		int output = test.digits(-500000);
		System.out.println(output);
	}
}
