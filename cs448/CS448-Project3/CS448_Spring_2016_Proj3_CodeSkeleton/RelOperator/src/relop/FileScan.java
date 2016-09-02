package relop;

import java.lang.IllegalStateException;
import global.RID;
import heap.HeapFile;
import heap.HeapScan;

/**
 * Wrapper for heap file scan, the most basic access method. This "iterator"
 * version takes schema into consideration and generates real tuples.
 */
public class FileScan extends Iterator {

  HeapFile our_heap;      //we need the heap file to open the scan
  HeapScan our_scan;      //we need the heap scan to have a scan to refrence with
  RID current_rid;        //need a global RID to return and set in has and getnext

  /**
   * Constructs a file scan, given the schema and heap file.
   */
  public FileScan(Schema schema, HeapFile file) {
    this.our_heap = file; //set the heapfile to the input parameter
    setSchema(schema); //set the schema settings in the relop package to the input parameter

    our_scan = our_heap.openScan();  //create and open the scan
    this.current_rid = new RID(); //sets the current RID for when we want to get the data
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
    if(our_scan != null){   //check if the scan is open, if it is we need to relase pinned pages by closing
        our_scan.close();
    }
        our_scan = our_heap.openScan(); //restart the scan
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    if(our_scan !=null){ //check to see if the scan is open if so return true
      return true;
    }else{
      return false;  //otherwise return false;
    }
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {
    if(our_scan != null){ //check to see if the scan is open if so we can close otherwise not
    our_scan.close();     
    }//the heapscan.close method auto releases all pinned pages
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
    if(our_scan != null){ //if the scan is open return if it has next or not
      return our_scan.hasNext();
    }else{
      return false; //scan is not open there will not be a next
    }
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() throws IllegalStateException {
    if(our_scan != null){
        byte[] data = our_scan.getNext(current_rid); //get the data to put into the tuple
        Tuple new_tup = new Tuple(getSchema(),data); //make a new tuple for the data
        return new_tup; //return the tuple
    }else{
      throw new IllegalStateException("no more tuples");
      
    }
  }

  /**
   * Gets the RID of the last tuple returned.
   */
  public RID getLastRID() {
    return current_rid;
  }

} // public class FileScan extends Iterator
