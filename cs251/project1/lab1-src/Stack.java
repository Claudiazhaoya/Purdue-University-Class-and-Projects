
public interface Stack {
    public int numberOfEntries();
    public void push( double val ) throws StackFullException;
    public double pop() throws StackEmptyException;
    public String toString();
};

