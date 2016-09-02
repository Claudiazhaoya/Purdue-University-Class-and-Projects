package query;

import parser.AST_Update;
import global.*;
import heap.*;
import relop.*;
import java.util.*;
import index.*;

/**
 * Execution plan for updating tuples.
 */
class Update implements Plan {

	String our_table;		//local variable for the table name from the tree
	Predicate[][] predicates;		//local 2d array for the predicates
	Schema our_schema;					//local version of the schema for checking
	Object[] objects;				//array of objects
	String[] updateColumns;			//array of strings for the updated columns
	
  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if invalid column names, values, or pedicates
   */
  public Update(AST_Update tree) throws QueryException {
		objects = tree.getValues();			//get the values to be updated from the tree
		our_table = tree.getFileName();		//get the name of the table to be updated from the tree
		QueryCheck.tableExists(our_table);	//make sure that table exists
		predicates = tree.getPredicates();	// get the predicates from the tree
		our_schema = Minibase.SystemCatalog.getSchema(our_table);	//get the schema so we can check the predicates
		QueryCheck.predicates(our_schema, predicates);		//check if the predicates exist
		updateColumns = tree.getColumns();				//get the columns from the tree
		for(int i=0;i<updateColumns.length;i++){
			QueryCheck.columnExists(our_schema,updateColumns[i]);	//check if the columns exsist
		}
  } // public Update(AST_Update tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {

	int updateRows = 0;		//number of rows updated
	boolean eval = false;	//boolean to track match or no match
	HeapFile heapFile = new HeapFile (our_table);	//open a heapfile based on name from tree
	HeapScan heapScan = heapFile.openScan();		//open a heapScan from that heapFile
	RID rid = new RID();							//creat temp RID
	Tuple tuple;									//create temp tuple
	byte[] data;									//data byte array when retriving data
	IndexDesc[] indexDesc = Minibase.SystemCatalog.getIndexes(our_table);	//array of indexes on our table
	
	boolean hasIndex;	//boolena to track if there is an index
	if(indexDesc.length == 0){		
		hasIndex = false;		//if there are no indexes on our table set it to false
	}else{
		hasIndex = true;		//otherwise it is true
	}
	
	while (heapScan.hasNext()){		//while there is data left in the heapscan
		eval = false;				//set the matcher to false
		data = heapScan.getNext(rid);	// get the data from the next record and set the temp rid
		tuple = new Tuple (our_schema, data);	//set the temp tuple based on the schema and the new data
		
		for (Predicate[] outer : predicates) {		//just like in delete we need to check all the predicates		
			for (Predicate inner : outer){			//inner loop of predicates
				eval = eval | inner.evaluate(tuple);	//check every predicate againt the tuple
				if (eval){								//if we have a match break to outer
					break;
				}
			}
			if (!eval){							//if any of the outers have no match break out  there is an issue
				break;
			}
		}

		if (eval) {
			int i = 0;		//we are ok to go we matched predicates
			Tuple newTuple = new Tuple (our_schema, data);	
			
			for (String updateIndex : updateColumns){
					newTuple.setField(updateIndex, objects[i++]);		//create a tuple of the updated info to be changed
			}
			if (hasIndex){												//if the table has an index we need to edit the system catalogs
				for (IndexDesc index : indexDesc){						//for all the indexs on the table
					HashIndex hashIndex = new HashIndex(index.indexName);	//open a Hash index file 
					for (String updateIndex : updateColumns){				//for all the columns being updated
						if (updateIndex.equalsIgnoreCase(index.columnName)){
							Object value = tuple.getField(index.columnName);	//get the value of the old tuple
							SearchKey searchKey = new SearchKey (value);		//get the searchkey of the old
							hashIndex.deleteEntry(searchKey, rid);				//delete the old entry
							value = newTuple.getField(index.columnName);		//get the value of the new entry
							searchKey = new SearchKey(value);					//get the search key for the new entry
							hashIndex.insertEntry(searchKey, rid);				//insert the new entry
						}
					}
				}
			}
			heapFile.updateRecord(rid, newTuple.getData());						//update the heapfile record evne if there is no index
			updateRows++;													//increment the amount of rows changed
		}
	}
	heapScan.close();														//close the heapscan
	
	// print the output message
	System.out.println(updateRows + " rows affected. ");	//print results
  } // public void execute()

} // class Update implements Plan
