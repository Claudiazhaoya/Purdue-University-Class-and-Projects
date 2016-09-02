import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class LinkedListQueue {	
	private static class Node{
		public String value;
		public Node next;
	}
	private Node head = null;
	private Node tail = null;

	public void enqueue(String value){
		Node temp = new Node();
		temp.value =value;
		temp.next = null;

		if(isEmpty()){
			head = tail =temp;
		}else{
			tail.next = temp;
			tail = temp;
		}
	}
	public String dequeue(){
		String value = null;
		if(isEmpty()){
			System.out.println("EMPTY");
		}else{
			value = head.value;
			head = head.next;
			if(head==null){
				tail=null;
			}
			return value;
		}
	}
	public String peek(){
		String value =null;
		if(isEmpty()){
			System.out.println("EMPTY");
		}else{
			value = head.value;
			return value;
		}
	}
	public boolean isEmpty(){
		return head==null;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		LinkedListQueue data = new LinkedListQueue();
		int players = 0;
		int elim = 0;
		System.out.println("Enter the number of players:");
		players = in.nextInt();
		System.out.println("Enter the nth player to be eliminated:");
		elim = in.nextInt();

		for(int i=0;i<players;i++){
			String value = "Player "+i;
			data.enqueue(value);
		}
		for(int j=0;j<players;j++){
			System.out.println(data.dequeue());
		}
	}
}
