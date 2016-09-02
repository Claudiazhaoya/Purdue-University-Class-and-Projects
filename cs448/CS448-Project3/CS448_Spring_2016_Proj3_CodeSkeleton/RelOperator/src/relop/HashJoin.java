package relop;
import global.SearchKey;
import heap.HeapFile;
import index.HashIndex;
import index.*;

public class HashJoin extends Iterator {


	IndexScan left_scan;	//joining so need scan for left Iterator
	IndexScan right_scan;	//joining so need scan for right iterator


	Iterator our_left;		//temp iterator used to join the schemas
	Iterator our_right;		//temp iterator used to join the schemas

	Integer our_left_col;	//Int for the left column for use in comparing and join
	Integer our_right_col;	//Int for the right coumn for use in comparing and join

	HashTableDup dupHash;	//Hash memory to store one side of the join to check against
							//able to have duplicats

	int curr_hash;			//the current hash function diffrent partitions in the iterators must match

	Tuple found_tuples[] = null;	//array of tuples for one side to hold and the other to check against
	Tuple to_return;				//tuple to return to the user
	Tuple checker;					// checks against the found tuples array
	boolean isopen = false;			//boolean to have status of join
	int counter = 0;				// counter for the join tables
	
	public HashJoin(Iterator left, Iterator right, Integer left_col, Integer right_col) {
	this.our_left = left;		//sets the left iterator
	this.our_right = right;		//sets the right iterator

	this.our_left_col = left_col;	//sets the left column
	this.our_right_col = right_col;	//esets the right column
	schema = Schema.join(our_left.schema,our_right.schema);
	//joins the two schemas useing local iterators and sets it

	converter(left,left_col,true);		//make sure we are using an index scan for left side
	converter(right,right_col,false);	//make sure we are using an index scan for right side
	//explain(1);
	dupHash = new HashTableDup();		//create instance of the dup hash memory location




	}

	public void converter(Iterator start_iter,Integer col,boolean left){
		//System.out.println("in converter");
		IndexScan temp_scan = null;		//temp index scan to finish with
		FileScan temp_file = null;		//temp file scan for filescan conversion
		HeapFile temp_heap = null;		//temp heapfile for other conversons
		HashIndex index = new HashIndex(null);	//hash index to help move data


		if(start_iter instanceof IndexScan){ 
			temp_scan = (IndexScan)start_iter;	//if index scan just set the temp to the iteratro
		}else{
			if(start_iter instanceof FileScan){ // it is a FileScan 
				temp_file = (FileScan)start_iter;	//set temp file scan based on given iterator
				temp_heap = temp_file.our_heap;		//set temp heamp based on new temp file scan

					while(temp_file.hasNext()){	
						index.insertEntry(new SearchKey(temp_file.getNext().getField(col)),temp_file.getLastRID());						
					}
					//while the file scan has tuples insert data into temp hash index
					temp_scan = new IndexScan(start_iter.schema,index,temp_heap);
					//create index scan based on start schema and the hash index and the original temp heap

			}
			else{ 
					temp_heap = new HeapFile(null);	//set temp heap file to null
					while(start_iter.hasNext()){	//while the file still has data
						temp_heap.insertRecord(start_iter.getNext().data);	//put data into the temp heap file
					}
					temp_file = new FileScan(start_iter.schema,temp_heap);	//create file scan based on new data
					while(temp_file.hasNext()){
						index.insertEntry(new SearchKey(temp_file.getNext().getField(col)),temp_file.getLastRID());						
					}
					temp_scan = new IndexScan(start_iter.schema,index,temp_heap);
					//create index scan based on file scan and other data
					
			}
		}if(left){
			left_scan = temp_scan;
			//if left side set to left

		}else{
			//otherwise set to right
			right_scan =temp_scan;
			
		}
	}
	
	public void explain(int depth) {	//single line explains the hash join and depth
    System.out.println("HashJoin Iterator");
    left_scan.explain(depth+1);
    right_scan.explain(depth+1);
	}
	
	public void restart() {
		left_scan.restart();	//restarts the left side
		right_scan.restart();	//restarts the right side

		//nexTuple = null;
	}
	
	public boolean isOpen() {
		boolean both = left_scan.isOpen() && right_scan.isOpen(); //have boolean set based on values of both sets
		return both;	//return the status of that boolean
	}
	
	public void close() {
		left_scan.close();	//closes and release pinned pages of left side
		right_scan.close();	//closes and release pinned pages of right side
	}
	

	public boolean hasNext() {
		
		if(found_tuples!=null){ //the array is not null check against these before starting new partition
			if(counter == found_tuples.length-1){ //if at the end of the array
				counter = 0;	//set counter to 0
				found_tuples = null;	//set array to null
				return hasNext();	//restart hasNext
			}else{
				for(;counter<found_tuples.length;counter++){ //for all the elements in the array
					if(checker.getField(our_right_col).equals(found_tuples[counter].getField(our_left_col))){
						//cheker is the tuple from right side 
						//check against all tuples in array on similar column
						to_return = Tuple.join(found_tuples[counter++],checker,schema); 
						//if match set the return tuple to the join of the checker and the found tuple
						return true; 
						//return true;
					}
				}
			counter = 0;	//after end of for loop we reset check
			found_tuples = null;	//set array to null
			return hasNext();	//restart has Next
			}
		}else{			//array was null so we need a new partition
			int hash_check = right_scan.getNextHash();	//get the has of the right side
			if(curr_hash != hash_check){	//if it does not match the current hash partition
				curr_hash = hash_check;		//set the current
				left_scan.restart();		//restart the left side we want the begining of it

				dupHash.clear();			//clear out the duplicate has memory location

				while(left_scan.hasNext() && left_scan.getNextHash()!=curr_hash){
					//while the left still has data and the hash does not match we are not in correct partion
					left_scan.getNext();   
					//keep going
				}
				while(left_scan.hasNext() && left_scan.getNextHash() == curr_hash){
					//while in correct partition
					Tuple left_tup = left_scan.getNext();
					//get the next tuple of the left side
					SearchKey temp = new SearchKey(left_tup.getField(our_left_col).toString());
					//create a serach key for that tuple
					dupHash.add(temp,left_tup);
					//insert it into the dup hash memory location
				}

			}if(right_scan.hasNext()){
				//if the right side has a next tuple
				checker = right_scan.getNext();
				//set the checker tuple to the right get next
				SearchKey compare = new SearchKey(checker.getField(our_right_col).toString());
				//create a search key for that tuple
				found_tuples = dupHash.getAll(compare);
				//find all tuple of left side from the dup hash memory location
				//add them to the global array to check against
				if(found_tuples !=null){
					//if we found at least one tuple to check agains
					for(;counter<found_tuples.length;counter++){
							//for every tuple in the array
						if(checker.getField(our_right_col).equals(found_tuples[counter].getField(our_left_col))){
							//check the right tuple against all tuples in array
							to_return = Tuple.join(found_tuples[counter++],checker,schema);
							//if match set return tuple to the join of the two
							return true; //return true
						}
					}
					counter = 0;			//no matches were found
					found_tuples = null;	//set array to null
					return hasNext();		//go back trhough to check again
				}
				counter = 0;				//no match reset compares
				found_tuples = null;		//set to null
				return hasNext();					//restart hasNext();
			}else{   					//right side has no more left so we are "done" 
				found_tuples = null;	//set array to null to clear out any lefovers
				return false;		//return false
			}
		}
	}
	
	public Tuple getNext(){
		if(to_return !=null){	//if the tuple to reuturn is not null
			return to_return;	//return it
		}else{
			throw new IllegalStateException("no more tuples"); //otherwise throw error
		}
	}
}
