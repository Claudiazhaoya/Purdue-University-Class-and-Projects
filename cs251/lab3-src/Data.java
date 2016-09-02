import java.util.*;
import java.lang.String;

class Data{
	public int vertex;
	public int inVisit;

	public Data(int vertex,int inVisit){
		this.vertex=vertex;
		this.inVisit=inVisit;
	}
	public int getV(){
		return vertex;
	}
	public int getVisit(){
		return inVisit;
	}
	public String toString(){
		return String.format("%d %d", vertex);
	}
}
