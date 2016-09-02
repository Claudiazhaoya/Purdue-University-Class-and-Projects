package heap;

import bufmgr.*;
import global.*;

public class HeapScan{// implements GlobalConst{
	protected HeapScan(HeapFile hf){

	}
	protected void finalize() throws Throwable{

	}
	public void close(){

	}
	public boolean hasNext(){
		return true;
	}
	public Tuple getNext(RID rid){
		byte[] b = new byte[100];
		Tuple tup = new Tuple(b,0,1);
		return tup;
	}
}
