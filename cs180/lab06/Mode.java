public class Mode{
	public int getMode(int[] list){
		int test = list[list.length-1];
		int[] mode = new int[test+1];
		int top =0;
		int location =0;
			// puts the number of each number into test array
		for(int i=0;i<(list.length);i++){
			mode[list[i]]++;
		}
			//checks which element has max so we knwo what mode is
		for(int i=0;i<mode.length;i++){
			if(mode[i]>top){
				top = mode[i];
				location =i;
			}
		}
			// sees of there is two maxes if so return a 0
		for(int i=0;i<mode.length;i++){
			if(mode[i]==top&&location!=i){
				location=0;
			}
		}
			// return
		return mode[location];

	}
	public static void main(String[] args){
		Mode example = new Mode();
		int out;
		//int[] list = {1,2,3,4,5,5,6,6,10};
		int[] list = {1};
		//int[] list = {1,2,3,3,3,5,6,6,10};
		//int [] list = {1,2};
		//int[] list = {1,2,4,4,5,6,6,7,7,9,9,9,10,11,11,11};
		out = example.getMode(list);
			//if more than one max print none else print the mode
		if(out==0){
			System.out.println("No Mode");
		}else{
			System.out.println("Mode is: "+out);
		}
	}
}
