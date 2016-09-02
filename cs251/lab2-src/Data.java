import java.util.*;
import java.lang.String;

class	Data implements Comparable<Data>{
	public  int v1;
	public  int v2;
	public  int weight;

	public Data(int v1, int v2, int weight){
		this.v1=v1;
		this.v2=v2;
		this.weight=weight;
	}

	public int retrieveV1(){
		return v1;
	}
	public int retrieveV2(){
		return v2;
	}
	public int retrieveWeight(){
		return weight;
	}
	@Override
		public int compareTo(Data temp) {
			int cWeight = temp.weight;
			if (this.weight > cWeight) {
				return 1;
			} else if (this.weight == cWeight) {
				return 0;
			} else {
				return -1;
			}
		}

	public String toString(){
		return String.format("%d %d %d", v1, v2, weight);
	}
}
