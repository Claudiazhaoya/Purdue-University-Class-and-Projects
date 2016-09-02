import java.util.*;
public class UnionFind {
		ArrayList<Integer> edges;
	   public UnionFind(int N) {
		
		 edges = new ArrayList<Integer>();
		 edges.add(0);
		 for (int i = 1; i <= N; i++) {
			 edges.add(i);
		 }
	   } 
	   public int find(int p){
		   while (p != edges.get(p)){
			   edges.set(p, find(p));
			   p = edges.get(p);
		   }
		   return p;
	   }
	   public void union(int p, int q){
		   edges.set(p, find(q));
		
	   }
}
