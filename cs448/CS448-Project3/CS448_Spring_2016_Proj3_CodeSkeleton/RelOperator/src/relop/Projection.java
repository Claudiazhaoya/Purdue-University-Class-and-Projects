package relop;

/**
 * The projection operator extracts columns from a relation; unlike in
 * relational algebra, this operator does NOT eliminate duplicate tuples.
 */
public class Projection extends Iterator {

    Iterator our_iter;     // we need an iterator here to wrap the scans
    Schema our_schema;     // our schema we are using to keep the projection safe
    Integer[] our_fields;  // our copy of the fields parameter
	boolean isopen = false;  // boolean to keep track of open or not
  /**
   * Constructs a projection, given the underlying iterator and field numbers.
   */
  public Projection(Iterator iter, Integer... fields) {
	this.our_fields = fields;  // set the fields to the input parameter
    this.our_iter = iter;     // set our iterator to the input paramter
	this.our_schema = new Schema(fields.length); //create the schema based on the number of fields
	
    Schema temp_schema = iter.getSchema(); // get the  schema of the interator paramter
    
	for(int i=0;i<our_fields.length;i++){
      our_schema.initField(i,temp_schema.fieldType(our_fields[i]), 
        temp_schema.fieldLength(our_fields[i]),temp_schema.fieldName(our_fields[i]));
      //here we create the final schema
      // for every element in the fields we need to create a new field in our schema based on 
      // the iterators schema and the fileds given in input

	}
	
    setSchema(our_schema);  //set the final schema
	isopen = true;           // boolean to see if the iterator is open or not
  }

  /**
   * Gives a one-line explaination of the iterator, repeats the call on any
   * child iterators, and increases the indent depth along the way.
   */
  public void explain(int depth) {
    System.out.println("Projection Iterator " + depth);
	our_iter.explain(depth+1);
  }

  /**
   * Restarts the iterator, i.e. as if it were just constructed.
   */
  public void restart() {
	isopen = true; 
    our_iter.restart(); //restarts the iterator and sets the boolean to true
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    return isopen;    //returns the boolean that keeps open status
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
      our_iter.close();   //closes and releases pinned pages
      isopen = false;     // sets the status to closed
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
      if(isopen){  //checks if iterator is open if so finds if there is a next 
      return our_iter.hasNext();
    }else{
      return false;     //otherwise return false
    }
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() {
    if(isopen){      //if the iterator is open
      Tuple old_tup = our_iter.getNext();   // get the next tuple of the iterator
      Tuple new_tup = new Tuple(our_schema);  //create a new tuple from our set schmea
      for(int i=0;i<our_fields.length;i++){   //set the fields of the new tuple to match the old one
        new_tup.setField(i,old_tup.getField(our_fields[i]));
      }
      return new_tup; //return the tuple

    }else{
      throw new IllegalStateException("no more tuples"); //otherwise throw exception
    }
  }

} // public class Projection extends Iterator
