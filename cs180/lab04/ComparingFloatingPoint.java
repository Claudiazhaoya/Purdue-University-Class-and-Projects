public class ComparingFloatingPoint{
	public static void main(String[] args){
		double root = Math.sqrt(3.47);
		if(Math.abs((root * root)-3.47)<0.00000001){
			System.out.println("Math.sqrt(3.47) squared is 3.47");
		}
		else{
			System.out.println("Math.sqrt(3.47) squared is not 3.47. It is " + 
						Math.abs(root * root));
		}
	}
}
