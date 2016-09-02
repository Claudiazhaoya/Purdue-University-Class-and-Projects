package query;

import parser.AST_CreateIndex;
import heap.HeapFile;
import index.HashIndex;
import global.Minibase;
import relop.FileScan;
import relop.Schema;
import relop.Tuple;
import global.SearchKey;
import global.RID;

/**
 * Execution plan for creating indexes.
 */
class CreateIndex implements Plan {

	protected String file;
	String ixTable;
	String ixColumn;
	Schema our_schema;

  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if index already exists or table/column invalid
   */
  public CreateIndex(AST_CreateIndex tree) throws QueryException {
  	this.file = tree.getFileName();			//get the file name from the AST tree
  	this.ixTable = tree.getIxTable();		//get the table name from the AST tree
  	this.ixColumn = tree.getIxColumn();		//get the column name to be indexed from the AST tree

  	
  	QueryCheck.fileNotExists(file);			//check if the File exists or not will throw error 
  	
  	QueryCheck.tableExists(ixTable);		//check if the table to be indexed exits can throw error
  	//our_schema =QueryCheck.tableExists(ixTable);

  	our_schema = Minibase.SystemCatalog.getSchema(ixTable);	//gets and sets the schema to be used by the index
  	QueryCheck.columnExists(our_schema,ixColumn);	//checks if the column to be indexed exists using shcema and index
  	
  	//int col_num = QueryCheck.columnExists(our_schema,ixColumn); //chcks if the dolumn to be in



  } // public CreateIndex(AST_CreateIndex tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {
  	HashIndex hasher = new HashIndex(file);		//creats and opens a blank hash index to create the index
  	HeapFile heap = new HeapFile(ixTable);		//create a new blank heap file
  	FileScan scaner = new FileScan(our_schema,heap);	//open a FileScan to iterate through the file

  	int temp = our_schema.fieldNumber(ixColumn);// gets the number of the field that refers to the column to be indexd
  	while(scaner.hasNext()){ 					//go through the file scan
  		Tuple temp_tuple = scaner.getNext();	// get the next tuple in the scan
  		SearchKey key = new SearchKey(temp_tuple.getField(temp));	//create a search key for this tuple
  		RID temp_RID = scaner.getLastRID();			//create a temp RID that is equal to the one from the tuple
  		hasher.insertEntry(key,temp_RID);			//insert the search key and the RID into the hash index

  	}scaner.close();			//done going through so can close the File Scan
  	//IndexScan could use this but it is not efficient at all would need to have a hash idex an would prob get wrong RID
  	//KeyScan //cant use keyscan we need a RID for insert into index
  	Minibase.SystemCatalog.createIndex(file,ixTable,ixColumn);	//make an entry into the system catalogs for the new index
    // print the output message
    System.out.println("Create Index done");	//print out that the index was created 

  } // public void execute()

} // class CreateIndex implements Plan
