package tests;

import java.io.*;
import global.AttrOperator;
import global.AttrType;
import global.RID;
import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import relop.FileScan;
import relop.HashJoin;
import relop.IndexScan;
import relop.KeyScan;
import relop.Predicate;
import relop.Projection;
import relop.Schema;
import relop.Selection;
import relop.SimpleJoin;
import relop.Tuple;

// YOUR CODE FOR PART3 SHOULD GO HERE.

public class QEPTest extends TestDriver {
	/** The display name of the test suite. */
	private static final String TEST_NAME = "Query Evaluation Pipelines tests";

	/** Size of tables in test3. */
	private static final int SUPER_SIZE = 2000;

	/** Department table schema. */
	private static Schema s_department;

	/** Employee table schema. */
	private static Schema s_employee;

	/** HeapFile Department variable */
	private static HeapFile department;
	
	/** HeapFile Department variable */
	private static HeapFile employee;
	
	public static void main(String argv[]) {

		// create a clean Minibase instance
		QEPTest qep = new QEPTest();
		qep.create_minibase();

		
		//String directory = argv[1];
		//directory = "/home/u88/dolavesm/CS/cs448/Project3/CS448-Project3/CS448_Spring_2016_Proj3_CodeSkeleton/RelOperator/src/tests/SampleData/Department.txt";
		BufferedReader br = null;
		try{
			String sCurrentLine;

			br = new BufferedReader(new FileReader(argv[0]+"/Department.txt"));
			String[] dep = new String[4];
			boolean first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println("["+sCurrentLine+"]");
				dep = sCurrentLine.split(", ");
				
				if(first){
					s_department = new Schema(4);
					s_department.initField(0, AttrType.INTEGER, 4, dep[0]);
					s_department.initField(1, AttrType.STRING, 20, dep[1]);
					s_department.initField(2, AttrType.FLOAT, 4, dep[2]);
					s_department.initField(3, AttrType.FLOAT, 4, dep[3]);
					first=false;
					department = new HeapFile(null);
				}else{
					//initCounts();

					// create and populate the department table
					//saveCounts(null);
					
					Tuple tuple = new Tuple(s_department);
					tuple.setAllFields(Integer.parseInt(dep[0]), dep[1], Float.parseFloat(dep[2]), Float.parseFloat(dep[3]));
					tuple.insertIntoFile(department);
					//saveCounts("department");
				}
			}

			br = new BufferedReader(new FileReader(argv[0]+"/Employee.txt"));
			String[] emp = new String[5];
			first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println("["+sCurrentLine+"]");
				emp = sCurrentLine.split(", ");
				
				if(first){
					s_employee = new Schema(5);
					s_employee.initField(0, AttrType.INTEGER, 4, emp[0]);
					s_employee.initField(1, AttrType.STRING, 20, emp[1]);
					s_employee.initField(2, AttrType.INTEGER, 4, emp[2]);
					s_employee.initField(3, AttrType.FLOAT, 4, emp[3]);
					s_employee.initField(4, AttrType.INTEGER, 4, emp[4]);
					first=false;
					employee = new HeapFile(null);
				}else{
					//initCounts();

					// create and populate the employee table
					//saveCounts(null);
					
					Tuple tuple = new Tuple(s_employee);
					tuple.setAllFields(Integer.parseInt(emp[0]), emp[1], 
					Integer.parseInt(emp[2]), Float.parseFloat(emp[3]), Integer.parseInt(emp[4]));
					tuple.insertIntoFile(employee);
					//saveCounts("employee");
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
		// run all the test cases
		System.out.println("\n" + "Running " + TEST_NAME + "...");
		boolean status = PASS;
		status &= qep.test1();
		status &= qep.test2();
		status &= qep.test3();
		status &= qep.test4();
		
		// display the final results
		System.out.println();
		if (status != PASS) {
			System.out.println("Error(s) encountered during " + TEST_NAME + ".");
		} else {
			System.out.println("All " + TEST_NAME
					+ " completed; verify output for correctness.");
		}

	} // public static void main (String argv[])
	
	protected boolean test1() {
		try {

			System.out.println("\nTest 1: Display for each employee his ID, Name and Age\n");
			
			FileScan scan = new FileScan(s_employee, employee);
			Projection pro = new Projection(scan, 0, 1, 2);
			pro.execute();

			// that's all folks!
			System.out.print("\n\nTest 1 completed without exception.");
			return PASS;

		} catch (Exception exc) {

			exc.printStackTrace(System.out);
			System.out.print("\n\nTest 1 terminated because of exception.");
			return FAIL;

		} finally {
			//printSummary(6);
			System.out.println();
		}
	} // protected boolean test1()
	
	protected boolean test2() {
		try {

			System.out.println("\nTest 2: Display the Name for the departments with MinSalary = MaxSalary\n");
			
			FileScan scan = new FileScan(s_department, department);
			Predicate[] preds = new Predicate[] {
			new Predicate(AttrOperator.EQ, AttrType.FIELDNO, 2, AttrType.FIELDNO, 3)};
			Selection sel = new Selection(scan, preds);
			Projection pro = new Projection(sel, 1);
			pro.execute();
			
			// that's all folks!
			System.out.print("\n\nTest 2 completed without exception.");
			return PASS;

		} catch (Exception exc) {

			exc.printStackTrace(System.out);
			System.out.print("\n\nTest 2 terminated because of exception.");
			return FAIL;

		} finally {
			//printSummary(6);
			System.out.println();
		}
	} // protected boolean test2()
	
	protected boolean test3() {
		try {

			System.out.println("\nTest 3: For each employee, display his Name and the Name of his department as well as the maximum salary of his department\n");
			
			HashJoin join = new HashJoin(new FileScan(s_department, department),
					new FileScan(s_employee, employee), 0, 4);
			Projection pro = new Projection(join, 5, 1, 3);
			pro.execute();
			
			// that's all folks!
			System.out.print("\n\nTest 3 completed without exception.");
			return PASS;

		} catch (Exception exc) {

			exc.printStackTrace(System.out);
			System.out.print("\n\nTest 3 terminated because of exception.");
			return FAIL;

		} finally {
			//printSummary(6);
			System.out.println();
		}
	} // protected boolean test3()
	
	protected boolean test4() {
		try {

			System.out.println("\nTest 4: Display the Name for each employee whose Salary is greater than the maximum salary of his department\n");
		
			HashJoin join = new HashJoin(new FileScan(s_department, department),
					new FileScan(s_employee, employee), 0, 4);
			//join.execute();
			Selection sel = new Selection(join, new Predicate(AttrOperator.GT,
					AttrType.FIELDNO, 7, AttrType.FIELDNO, 3));
			Projection pro = new Projection(sel, 5);
			pro.execute();
			
			// that's all folks!
			System.out.print("\n\nTest 4 completed without exception.");
			return PASS;

		} catch (Exception exc) {

			exc.printStackTrace(System.out);
			System.out.print("\n\nTest 4 terminated because of exception.");
			return FAIL;

		} finally {
			//printSummary(6);
			System.out.println();
		}
	} // protected boolean test4()
}
