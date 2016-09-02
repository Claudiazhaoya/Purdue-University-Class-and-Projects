public class Exponentiation{
	static int out=0;
	static int exp(int input,int pow){
		if(pow==0){
			return 1;
		}else if(input==0){
			return 0;
		}else if(pow==1){
			return input;
		}else{
			return input*exp(input,pow-1);	
		}
	}
	public static void main(String[] args){
		Exponentiation test = new Exponentiation();
		int output = test.exp(2,2);
		System.out.println(output);

	}
}
