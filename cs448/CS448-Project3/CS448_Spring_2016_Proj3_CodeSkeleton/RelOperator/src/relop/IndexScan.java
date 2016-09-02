package relop;

import global.RID;
import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import index.BucketScan;

/**
 * Wrapper for bucket scan, an index access method.
 */
public class IndexScan extends Iterator {

  HeapFile our_file;    //
  BucketScan our_scan;  //bucketscan is the scan we are using in this file
  HashIndex our_index;  //needed to open the scan

  /**
   * Constructs an index scan, given the hash index and schema.
   */
  public IndexScan(Schema schema, HashIndex index, HeapFile file) {
    this.our_file = file; //set the HeapFIle paramter
    setSchema(schema);    //set the sechema based on input paramter
    this.our_index = index; // set the index based on the input paramter
    this.our_scan = this.our_index.openScan();  // open the scan useing the index;
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
    if(this.our_scan !=null){   //check if the scan is already open and if it is close it first
      this.our_scan.close();    //close to relase pinned pages
    }
    this.our_scan = this.our_index.openScan(); //open the scan using the index.
  }

  /**
   * Returns true if the iterator is open; false otherwise.
   */
  public boolean isOpen() {
    if(our_scan !=null){    //check if the scan is already open
      return true;          //if so return true
    }
    return false;           //otherwise return false;
  }

  /**
   * Closes the iterator, releasing any resources (i.e. pinned pages).
   */
  public void close() {   //check if the scan is already open 
    if(our_scan !=null){
      our_scan.close();   //if so close file
    }
  }

  /**
   * Returns true if there are more tuples, false otherwise.
   */
  public boolean hasNext() {
    if(our_scan!=null){     //check if the scan is open or not
      return our_scan.hasNext();  //if so check if it has a next tuple 
    }else{
      return false;     //if not open there is no next one to return
    }
  }

  /**
   * Gets the next tuple in the iteration.
   * 
   * @throws IllegalStateException if no more tuples
   */
  public Tuple getNext() throws IllegalStateException{
    if(our_scan !=null){                              // check if the scan is open
      RID rid_data = our_scan.getNext();              // get a temp RID to get next Tuple
      byte[] data = our_file.selectRecord(rid_data);  // get the data of the next record
      Tuple new_tup = new Tuple(getSchema(),data);    // create the new tuple 
      return new_tup;                                  //return the new tuple
    }
    else{
      throw new IllegalStateException("no more tuples");    //otherwise throw exception
    }
  }

  /**
   * Gets the key of the last tuple returned.
   */
  public SearchKey getLastKey() {
    return our_scan.getLastKey();   //getLastKey method auto returns the last tuple returnd
  }

  /**
   * Returns the hash value for the bucket containing the next tuple, or maximum
   * number of buckets if none.
   */
  public int getNextHash() {
    return our_scan.getNextHash(); //the scan.getNextHash auto gets the next hash of the tuple
  }

} // public class IndexScan extends Iterator
