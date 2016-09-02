package relop;

/**
 * The selection operator specifies which tuples to retain under a condition; in
 * Minibase, this condition is simply a set of independent predicates logically
 * connected by OR operators.
 */
public class Selection extends Iterator {
	private Predicate[] preds;   //array of predicats used to determine what to return
	private Tuple nextTup;       // tuple to return 
	private Iterator iter;       // iterator instance to work on
	private boolean open;        // status of iterator open or closed
	
  /**
   * Constructs a selection, given the underlying iterator and predicates.
   */
  public Selection(Iterator iter, Predicate... preds) {
	  this.preds = preds;    //set our predicats to the paramters
    this.iter = iter;       // set our iterator to the paramter
    schema = iter.getSchema();  //set the local schmea to the passed iterators schema
    this.open = true;       //set the status to open
  }

  /**
   * Gives a one-line explaination of the iterator, repeats the call on any
   * child iterators, and increases the indent depth along the way.
   */
  public void explain(int depth) {
	 indent(depth);
    System.out.println("HashJoin Iterator");
  }

  /**
   * Restarts the iterator, i.e. as if it were just constructed.
   */
  public void restart() {
    if(this.open){    //if the iteartor is open close it to relase pinned pages
      iter.close();   
    }
    iter.restart();   //restart the iterator
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
	return this.open;    //just need to return the current status boolean
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
	this.iter.close();     //will close the iterator and realse pages with built in function
    this.open = false;    //sets the status to closed
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
	 while (iter.hasNext()) {      //while we have a next in the iterator
		  Tuple tup = iter.getNext();   //get the next tuple

      if(preds.length == 0){    //if no predicats we can assume we return all tuples ie nothing to check against
        nextTup = tup; //need this in case there are no predicates.
        return true;
      }
		  for(Predicate p : preds) {  //for all the predicats
			 if(p.evaluate(tup) == true) { //check to see if the tuple evaluates to true with the predicate
				  nextTup = tup;  //if so set the return tuple to the temp
				  return true;    //return true
			 }
		  }
	 }
	 nextTup = null;   //if no more matches set the return tuple to null
	 return false;     //and return false
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() {
	 if(nextTup != null) { //if the iterator is open
		  return nextTup;   //return the return tuple found in hasNext
	 }else{
		  throw new IllegalStateException("no more tuples");      //otherwise we throw error
	 }
  }

} // public class Selection extends Iterator
