public class question2{
	static boolean ascending(int[] input){
		for(int i=0;i<(input.length-1);i++){
			if(input[i] <= input[i+1]){
				continue;
			}else{
				return false;
			}
		}return true;
	}
	public static void main(String[] args){
		boolean test = true;
		question2 example = new question2();
		int[] in = {0,1,2,3,4,5};
		int[] other = {0,2,4,1,0};
		test = example.ascending(in);
		if(test==true){
			System.out.println("true");
		}else if(test==false){
			System.out.println("false");
		}
	}
}
