import java.util.*;
import java.lang.String;

class BipartMatch{

	public static void printer(int[] matched, int size){
		int i=0;
		int g=0;
		int numOfEdges=0;
		int b;
		while(g<size){
			if(matched[g]!=0){
				numOfEdges++;
			}
			g++;
		}
		System.out.println(numOfEdges);
		while(i<size){
			if(matched[i]!=0){
				System.out.println((i+1)+" "+matched[i]);
			}
			i++;
		}
	}



	public static int[] bip_m(LinkedList<Data>[] info, int[] matched,int size){
		int b=0;
		int i=0;
		int visited[];
		visited = new int[size];
		Data temp = new Data(0,0);
		Queue<Integer> queue = new LinkedList<Integer>();
		while(i<size){
			if(info[i].size()==1){
				queue.add(i+1);
				break;
			}
			i++;
		}
		while(queue.size()!=0){
			int outer=0;
			int checker = 0;
			b = queue.remove();
			visited[b-1]=1;
			while(outer<info[b-1].size()){
				checker = info[b-1].get(outer).getV();
				if(visited[checker-1]==0){
					queue.add(checker);
					if((matched[checker-1]==0)&&(matched[b-1]==0)){
						matched[checker-1]=b;
						matched[b-1]=checker;
					}
				}
				outer++;
			}
		}

		return matched;
	}




	public static int checkVert(int vert1, int vert2,int set1, int set2){
		if(vert1<vert2){
			return 0;
		}
		if(vert1>vert2){
			return 1;
		}
		return 0;
	}

	public static void main(String[] args){
	LinkedList<Data>[] info;
	int[] matched;
	int set1;
	int set2;
	int edges;
	
		Scanner scanner = new Scanner(System.in);
		int counter=0;
		int vertex1=0;
		int vertex2=0;
		int checker=0;
		int printCheck=0;
		
		while(checker==0){
		counter=0;
		printCheck=0;
		set1=scanner.nextInt();
		set2=scanner.nextInt();

		
		info = new LinkedList[set1+set2];
		matched = new int[set1+set2];

		int i=0;
		while(i<(set1+set2)){
			info[i] = new LinkedList<Data>();
			matched[i]=0;
			i++;
		}

		edges=scanner.nextInt();

		while(counter<edges){
			vertex1 = scanner.nextInt();
			vertex2 = scanner.nextInt();
			counter++;
			int inMatch=0;
			Data data1 = new Data(vertex1,inMatch);
			Data data = new Data(vertex2,inMatch);
			int insert=0;
			info[(vertex2-1)].add(data1);
			info[(vertex1-1)].add(data);

		}

		checker = scanner.nextInt();
		bip_m(info,matched,(set1+set2));
		printer(matched,(set1));
		if(checker!=1){
			System.out.println("0");
		}

		else if(checker==1){
			System.out.println("1");
		}

		}
	}
}
