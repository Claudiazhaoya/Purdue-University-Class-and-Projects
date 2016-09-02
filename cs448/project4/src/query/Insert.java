package query;

import parser.AST_Insert;
import global.*;
import heap.HeapFile;
import index.HashIndex;
import relop.*;
/**
 * Execution plan for inserting tuples.
 */
class Insert implements Plan {
	String our_file;		//local variable for the file name from the AST tree
	Object[] our_values;		// array of objects to be inserted into the index
	Schema our_schema;		// local version of the schema from the table from the tree
  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if table doesn't exists or values are invalid
   */
  public Insert(AST_Insert tree) throws QueryException {
	our_file = tree.getFileName();		//get the file name from the tree and set to local variable
	//QueryCheck.fileNotExists(our_file); // check if the file exists or not, if it does not throw error
	QueryCheck.tableExists(our_file);	//check to see if the Table exists otherwise throw an error

	our_values = tree.getValues();
	our_schema = Minibase.SystemCatalog.getSchema(our_file);
	QueryCheck.insertValues(our_schema, our_values);		//checks to make sure the values to be inserted match the schema
  } // public Insert(AST_Insert tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {
	HeapFile heap = new HeapFile(our_file);		//open a heap file based on the file name
	Tuple tuple = new Tuple(our_schema, our_values);// create tuple based on the schema and the values from the tree
	RID rid = heap.insertRecord(tuple.getData());	//insert record into heapFIle and create a RID
	IndexDesc[] indexes = Minibase.SystemCatalog.getIndexes(our_file); //get the indexes for the file in the tree

	for (IndexDesc indexDesc : indexes){		//for every index in system catalogs from the file
		String indexName = indexDesc.indexName;	//get the name of the current index
		String our_column = indexDesc.columnName; //get the column that the index is on
		HashIndex hash = new HashIndex(indexName);	//open the index based on the index name
		SearchKey searchKey = new SearchKey(tuple.getField(our_column));	//create a new search key for the new entry
		hash.insertEntry(searchKey, rid);	//insert the entry 
	}
	
	// print the output message
	System.out.println("1 rows affected.");		//print out result
    //System.out.println("0 rows affected. (Not implemented)");

  } // public void execute()

} // class Insert implements Plan
