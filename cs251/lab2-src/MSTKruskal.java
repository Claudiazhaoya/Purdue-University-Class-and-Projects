import java.util.*;
import java.lang.String;

class	MSTKruskal{

	public static LinkedList<Data> min_st(int n,LinkedList<Data> edge){
		int i=0;
		int vert1=0;
		int vert2=0;

		UnionFind sort = new UnionFind(n);
		while(i<edge.size()){
			vert1 = sort.find(edge.get(i).retrieveV1());
			vert2 = sort.find(edge.get(i).retrieveV2());

			if(vert1 != vert2){
				sort.union(vert1,vert2);
			}
			i++;
		}

		return edge;
	}

	public static void printer(LinkedList<Data> edge){
		int i=0;
		while(i<edge.size()){
			System.out.println(""+edge.get(i).retrieveV1()+" "+edge.get(i).retrieveV2());
			i++;
		}
	}

	public static void main (String[] args){
		LinkedList<Data> edge = new LinkedList<Data>();

		int counter=0;
		int v1=0;
		int v2=0;
		int weight=0;
		Scanner scanner = new Scanner(System.in);
		int n=scanner.nextInt();
		int m=scanner.nextInt();


		while(counter<m){
			v1 = scanner.nextInt();
			v2 = scanner.nextInt();
			weight = scanner.nextInt();
			counter++;

			Data d = new Data(v1,v2,weight);
			edge.add(d);
		}

		Collections.sort(edge);
		System.out.println("\n");
		printer(min_st(n,edge));
		System.out.println("0");


	}
}
