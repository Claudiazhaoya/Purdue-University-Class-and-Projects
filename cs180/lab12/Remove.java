public class Remove{
	public char[] removeDuplicates(char[] inp){
		int number = inp.length;
		char[] out = new char[number];
		int i=0;
		int b=0;
		do{
			for(int j=0;j<out.length;j++){ 
				if(out[j]!=inp[i]){
					
				}
			out[j] = inp[i];
			System.out.println(out[j]);
			}
			i++;
			b++;
		}
		while(b<number);
		return out;

	}
	public static void main(String[] args){
		Remove test = new Remove();
		char[] in= {'b','d','a','b','f','a','g','a','a','f'};
		test.removeDuplicates(in);
	}
}
