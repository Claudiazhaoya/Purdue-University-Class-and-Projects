
public class RecursiveOdd{
	public int counter=0;
	public int numberOfOdd(int input){
		if(input<=0){
		return 0;
		}else{
			if(input%2!=0){
				
				counter++;
			}
			numberOfOdd(input/10);
		}
		return counter;
	}
	public static void main(String[] args){
		RecursiveOdd test = new RecursiveOdd();
		int output = test.numberOfOdd(1);
		System.out.println(output);

	}
}
