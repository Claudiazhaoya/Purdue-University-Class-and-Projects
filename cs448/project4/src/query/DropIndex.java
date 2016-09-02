package query;

import parser.AST_DropIndex;
import index.HashIndex;
import global.Minibase;

/**
 * Execution plan for dropping indexes.
 */
class DropIndex implements Plan {
	String file;

  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if index doesn't exist
   */
  public DropIndex(AST_DropIndex tree) throws QueryException {
  		this.file = tree.getFileName();   //get the file name from the AST tree
  		QueryCheck.indexExists(file);     //check to see if the index exists(throws error otherwise)
  } // public DropIndex(AST_DropIndex tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {
  		HashIndex hash = new HashIndex(file);   //open and load the hash found by the file
  		hash.deleteFile();                      //delete the hash 
  		Minibase.SystemCatalog.dropIndex(file); //remove it form the system catalogs

    // print the output message
    System.out.println("DropIndex done");     //print out the drop done.

  } // public void execute()

} // class DropIndex implements Plan
