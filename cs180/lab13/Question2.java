import java.util.Scanner;
import java.util.LinkedList;
public class Question2 {	
	private static class Node{
		public String value;
		public Node next;
	}
	private Node head = null;
	private Node tail = null;
	private int size=0;

	public void addFirst(String value){
		Node temp = new Node();
		temp.value = value;
		temp.next = head;
		head = temp;
		if(tail==null){
			tail=head;
		}
		size++;
	}
	public void addLast(String value){
		Node temp = new Node();
		temp.value = value;
		temp.next=null;
		if(tail==null){
			head=tail=temp;
		}
		else{
			tail.next = temp;
		}
		size++;
	}
	public int getSize(){
		return size;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		Question2 data = new Question2();
		int players = 0;
		int elim = 0;
		System.out.println("Enter the number of players:");
		players = in.nextInt();
		System.out.println("Enter the nth player to be eliminated:");
		elim = in.nextInt();

		for(int i=0;i<players;i++){
			String value = "Player "+i;
			data.addLast(value);
		}
		System.out.println("Winner: Player");
		System.out.println("No of turns:"+(players-1));
	}
}
