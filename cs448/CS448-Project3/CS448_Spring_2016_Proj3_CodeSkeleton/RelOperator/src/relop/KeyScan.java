package relop;

import global.RID;
import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import index.HashScan;

/**
 * Wrapper for hash scan, an index access method.
 */
public class KeyScan extends Iterator {
  HeapFile our_file; //take in a heapfile so we need one global
  HashScan our_scan; //we need a scan as well
  HashIndex our_index;  //need the index to open
  SearchKey our_key;    // this is the key we use  to find where to open
  Boolean isopen = false; //boolean to keep track of the status of the scan
  /**
   * Constructs an index scan, given the hash index and schema.
   */
  public KeyScan(Schema schema, HashIndex index, SearchKey key, HeapFile file) {
    setSchema(schema);    //set the schema
    this.our_file = file; //set our file to the input
	  this.our_index = index;  //set our index to the input
    
    this.our_key = key;     // set our key to the input
    this.our_scan = index.openScan(key);    //open the scan using the index and key
    this.isopen = true;   //set the status to open
  }

  /**
   * Gives a one-line explaination of the iterator, repeats the call on any
   * child iterators, and increases the indent depth along the way.
   */
  public void explain(int depth) {
	System.out.println("Current scan: " + this.our_scan.toString() + ", debth: " + depth);
    
  }

  /**
   * Restarts the iterator, i.e. as if it were just constructed.
   */
  public void restart() {
    if(isopen){         //check if the scan is open,
      this.our_scan.close();  //if so close it to relase pinned pages
    }
    this.our_scan = our_index.openScan(our_key);  //open the scan using the index and the key
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    return isopen;        //just return the status of the boolean
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
    if(isopen){         //check if the scan is open
    our_scan.close();   //if so close it and release pinned pages
  }
	isopen = false;      //set the boolean to false
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
    if(isopen){       //check if the scan is open
      return our_scan.hasNext();  //return the boolean that is returned from the scan.hasNext 
    }
    return false;   //if scan is closed no next so false
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() {	
	if(isopen){        //check if the scan is open
      RID rid_data = our_scan.getNext();  //if so get the RID of the next tuple
      byte[] data = our_file.selectRecord(rid_data);  //get the data of the next one
      Tuple new_tuple = new Tuple(this.getSchema(),data); //create the new TUple to return 
      return new_tuple;   //return the new tuple
    }else{
		throw new IllegalStateException("no more tuples");
      //throw error if there are no more tuples
	}
  }

} // public class KeyScan extends Iterator
