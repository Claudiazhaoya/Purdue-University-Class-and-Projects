package query;

import parser.AST_Delete;
import index.HashIndex;
import global.*;
import heap.*;
import relop.*;
import java.util.*;
import index.*;

/**
 * Execution plan for deleting tuples.
 */
class Delete implements Plan {
	String our_table;			//local string for the table name
	Predicate[][] predicates;	//2d array of Precdicate objects gotten from the tree.
	Schema our_schema;				//local copy of the schema to be used
	IndexDesc[] indexes;		//array of IndexDescription objects
	
  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if table doesn't exist or predicates are invalid
   */
  public Delete(AST_Delete tree) throws QueryException {
	  our_table = tree.getFileName();				//get and set the table name from the tree
	  QueryCheck.tableExists(our_table);			//check to see if the table exists or not(throw error)

	  our_schema = Minibase.SystemCatalog.getSchema(our_table);

	  predicates = tree.getPredicates();			//get and set the predicates from the tree
	  QueryCheck.predicates(our_schema, predicates);	//check to see if the predicats match the current schema
	  
	  indexes = Minibase.SystemCatalog.getIndexes(our_table);
	  for (IndexDesc index : indexes) {
		  QueryCheck.indexExists(index.indexName);
	  }
	  
  } // public Delete(AST_Delete tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {

	  int delRows = 0;								//counter of the number of rows changed
	  boolean eval = false;							

	  HeapFile heapFile = new HeapFile(our_table);	//open a heapFile based on the table name given in the tree
	  HeapScan heapScan = heapFile.openScan();		//open a heapScan of the File just opend
	  //RID rid = new RID();	
	  Tuple tuple;									//inilitize space for a temp tuple
	  
	  while(heapScan.hasNext()) {					//as long as there is still stuff in the heapscan
		  RID rid = new RID();						// make a new RID as an output for the getnext
		  eval = false;								//set the boolean to false ie not changed
		  byte[] data = heapScan.getNext(rid);		//get the data of the next record and sets rid output
		  tuple = new Tuple(our_schema, data);			//create a new tuple based on the schema and data from heapscan
		  
		  for (Predicate[] outer : predicates) {	//outer part of 2d array of predicates	
			for (Predicate inner : outer) {			//innder part of 2d array of predicates
				eval = eval | inner.evaluate(tuple);	//evaluate each predicate against the tuple
				if (eval) {								//if match break for find
					break;
				}
			}
			if (!eval){								//if no match on specific predicate break for issue
				break;
			}
		  }
		  if(eval){									//if we have a match we need to do the dlete and update the system catalogs
			  for(IndexDesc index : indexes) {		//for all the indexs in the index Desc
				  HashIndex hashIndex = new HashIndex(index.indexName);	// open the hash index by the name
				  Object value = tuple.getField(index.columnName);	//get the field we are modifing
			      SearchKey searchKey = new SearchKey(value);		//create the serach key we will use to find the entry
			      hashIndex.deleteEntry(searchKey, rid);    		//delete the entry
			  }
			  heapFile.deleteRecord(rid);							//delte the entry from the heapfile
			  delRows++;											//increment the number of rows changed
		  }
	  }
	  heapScan.close();											//close the heapScan
	  
      // print the output message
      System.out.println(delRows + " rows affected.");			//print results
  } // public void execute()

} // class Delete implements Plan
