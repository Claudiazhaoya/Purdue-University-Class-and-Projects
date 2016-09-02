public class P5{
	public int binaryToDecimal(String binaryString){
		int dec = 0;
		int i = 0;
		do{
			dec*=2;
			if(binaryString.charAt(i)=='1'){
				dec+=1;
			}
			i++;
		}while(i<3);
		return dec;
		}
	public static void main(String[] args){
		P5 test = new P5();
		String input = "010";
		int out = test.binaryToDecimal(input);
		System.out.println(out);
		}
}
