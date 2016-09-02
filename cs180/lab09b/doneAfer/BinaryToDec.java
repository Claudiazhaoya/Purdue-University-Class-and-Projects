public class BinaryToDec{
	public int binaryToDecimal(String binaryString){
		int dec = 0;
		int i=0;
		do{
			dec*=2;
			if(binaryString.charAt(i)=='1'){
				dec+=1;
			}
			i++;
		}while(i<binaryString.length());
		return dec;
	}
	public static void main(String[] args){
		BinaryToDec test = new BinaryToDec();
		String input = args[0];
		int out = test.binaryToDecimal(input);
		System.out.println(out);
	}
}
