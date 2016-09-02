import java.util.ArrayList;
public class CummulativeSum{
	static ArrayList<Integer> out = new ArrayList<Integer>();
	static int counter=0;
	static ArrayList<Integer> sum(int[] input){
		int temp=counter;
		int temp2=0;
		if(counter==input.length){
			return out;
		}else{
			if(counter==0){
				out.add(input[0]);
			}else{
				while(temp>=0){
					temp2+=input[temp];
					temp--;
				}
				out.add(temp2);
			}
			counter++;
			sum(input);
		}
		return out;
	}
	public static void main(String[] args){
		int in[] = {2,3,1,5};
		CummulativeSum test = new CummulativeSum();
		ArrayList<Integer> output = test.sum(in);
		for(int i=0;i<output.size();i++){
			System.out.println(output.get(i));
		}
	}
}
