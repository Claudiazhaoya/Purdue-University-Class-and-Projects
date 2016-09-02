package query;

import parser.AST_Describe;
import java.util.*;
import global.*;
import relop.*;

/**
 * Execution plan for describing tables.
 */
class Describe implements Plan {
	Map<Integer, String> hashMap;
	Catalog catlog;
	Schema schema;

  /**
   * Optimizes the plan, given the parsed query.
   * 
   * @throws QueryException if table doesn't exist
   */
  public Describe(AST_Describe tree) throws QueryException {
	hashMap = new HashMap<Integer, String>();		//initialize hashMap
	catlog = new Catalog(true);						//initialize catalog
	schema = catlog.getSchema(tree.getFileName());	//initialize schema
	
	hashMap.put(11, "Integer");				//insert 11,Integer into hashMap
	hashMap.put(12, "Float");				//insert 12, Float into hashMap
	hashMap.put(13, "String");				//insert 13, String into hashMap
  } // public Describe(AST_Describe tree) throws QueryException

  /**
   * Executes the plan and prints applicable output.
   */
  public void execute() {

    // print the output message
    if (schema != null) {		
			int count = schema.getCount();			//get the count from the Schenma
			System.out.println("ColName\t\tColType\t\tLength\n-------\t\t-------\t\t------");		//print columns of the table
			for (int i = 0; i < count; i++) {	//for every row
				int fieldType = schema.fieldType(i);	//grab field Type
				if (hashMap.containsKey(fieldType)) {	//check schema if it contains fieldtype
					System.out.println(schema.fieldName(i)+"\t\t"+hashMap.get(fieldType)+"\t\t"+schema.fieldLength(i)); //print data
				} else { //print fieldType number instead
					System.out.println(schema.fieldName(i)+"\t\t"+fieldType+"\t\t"+schema.fieldLength(i));//print data
				}

			}
	}
	System.out.println(""); //EXTRA SPACE!!!!!!!!

  } // public void execute()

	
} // class Describe implements Plan
