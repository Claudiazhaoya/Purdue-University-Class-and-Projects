
public class StackList implements Stack {
	int entries=0;
	List head;
	List temperary;
	List outnode;
	public class List{
		public double val;
		public List nextList;
	}
    public StackList() {
    	head=null;
    }
    public int numberOfEntries() {
	return entries;
    }
    public void push( double val ) throws StackFullException {
    	List packet = new List();
    	packet.val=val;
    	packet.nextList=head;
    	head=packet;
    	entries++;
    }
    public double pop() throws StackEmptyException {
    	if(entries==0){
    			throw new StackEmptyException();
    	}
    	List temperary = head;
    	head=head.nextList;
    	entries--;
    	return temperary.val;
    }  
    public String toString() {
        List outnode=head;
    	String output = "";
    	output +="---Stack---\n";
    	while(entries>0){
    		output += entries-1+": "+outnode.val+"\n";
    		outnode=outnode.nextList;
    		entries--;
    	}	
	return output;
    }
};
