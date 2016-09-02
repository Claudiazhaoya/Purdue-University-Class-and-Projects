package query;

import parser.AST_Select;

import global.*;
import heap.*;
import relop.*;
import relop.Iterator;
import java.util.*;

/**
 * Execution plan for selecting tuples.
 */
class Select implements Plan {

	Iterator our_iterator = null;
	boolean our_explainer = false;
	Schema current_schema = null;
	Schema[] our_Schemas;
	String[] our_tables;
	String[] our_columns;
	Integer[] our_col_id;
	Predicate[][] our_predicates;
	
	/**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if validation fails
   */
  public Select(AST_Select tree) throws QueryException {
		
		our_tables = tree.getTables();		//array of tables to select from 
		our_Schemas = new Schema[our_tables.length]; //array of shems for the tables selected from 

			for(int i=0;i<our_tables.length;i++){	//for all the tables in the tree
				if(current_schema==null){			//if the current schema is null(no other combined selection)
				current_schema = QueryCheck.tableExists(our_tables[i]);//make sure the table exists and get the schema from it
					our_Schemas[i] = current_schema;		//set the schema from the table to the shcema array
				}else{									//there is a already existing scheam need to join
					our_Schemas[i] = QueryCheck.tableExists(our_tables[i]);		//get the shemca from the table 
					Schema joiner = Schema.join(current_schema,our_Schemas[i]);	//join the current and the new
					current_schema = joiner;		//set the new current schema
				}
			}
		our_columns = tree.getColumns();			//get all the columns from the tree
		our_col_id = new Integer[our_columns.length];
			for(int i=0;i<our_columns.length;i++){				//make sure they all exists
				our_col_id[i] = QueryCheck.columnExists(current_schema,our_columns[i]);
			}

		our_predicates = tree.getPredicates();	//get all the predicates
			QueryCheck.predicates(current_schema, our_predicates);	//make sure they all exist

		our_explainer = tree.isExplain;	//set global boolean to tree setting

  } // public Select(AST_Select tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {

	Iterator it = null;

	for (int i= 0; i < our_tables.length; i++) {			//for every table in the array
		HeapFile heapFile = new HeapFile(our_tables[i]);	//open a heap file for the table
		if(it != null) {									// if there is an iterator we need to join for nested or combined querye
			Iterator temp1 = new FileScan(our_Schemas[i], heapFile); // open filescan based on schema and heapfiel
			it = new SimpleJoin(it, temp1);	//join the current iterator and the new one
			
		}else{
			it = new FileScan(our_Schemas[i], heapFile);	//no current just set the current to the filescan
		}
	}
	
	for(int i = 0; i < our_predicates.length; i ++) {		//for all the precicates
		it = new Selection(it, our_predicates[i]);	//set it to the result of the Selction
	}
	
	if (our_columns.length > 0) {		//if the columns are more than 0
		it = new Projection(it, our_col_id);	//set it to the result of the projection
	}
	
	int affRows = 0;		//number of rows affected
	our_iterator = it;		//set the global iterator to the result set
	if(our_iterator != null) {	// if the global iterator is not null ()
		if(our_explainer) {		//if we are explaining
			our_iterator.explain(0);	//explain it
			System.out.println("SELECT Explained");
		}
		else {
			affRows = our_iterator.execute();	//otherwise just print the rows affected
			System.out.println(affRows + " rows affected."); //print num affected rows
		}
	}

  } // public void execute()

} // class Select implements Plan
 