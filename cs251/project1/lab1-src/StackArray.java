import java.util.Arrays;

public class StackArray implements Stack {
	double[] stake;//stack array
	double out;
	int maxSize;
	int entries = 0;//number of entries
	
    public StackArray( int maxSize ) {
    	stake = new double[maxSize];
    	this.maxSize=maxSize;
    }
    public int numberOfEntries() {
	return entries;
    }
    public void push( double val ) throws StackFullException {
    	if(entries==maxSize){
    		throw new StackFullException();
    	}
    	stake[entries] = val;
    	entries++;
    }
    public double pop() throws StackEmptyException {
    	if(entries==0){
    			throw new StackEmptyException();
    	}
    	entries--;
    	return stake[entries];
    }  
    public String toString() {
   	out = stake[entries-1];
   	String output = "";
   	output +="---Stack---\n";
   	for(int i=entries;i>0;i--){
   		output += i-1+": "+stake[i-1]+"\n";
   	}
	return output;
    }
};
