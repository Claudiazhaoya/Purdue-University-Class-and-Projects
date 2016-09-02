package heap;

import java.util.Iterator;

import chainexception.ChainException;
import global.*;
import java.util.*;

public class HeapScan {


	public HeapFile current_heap_file;
	public HFPage current_Heap_page;

	public RID current_RID;
	public Iterator<PageId> pid_List;

	protected HeapScan(HeapFile hf) {

		this.current_heap_file = hf;

		pid_List = hf.iterator();
		PageId currentId = pid_List.next();
		Page page=new Page();
		Minibase.BufferManager.pinPage(currentId, page, false);
		current_Heap_page = new HFPage(page);
		current_RID = current_Heap_page.firstRecord();

	}
	protected void finalize()throws ChainException{
		//try{
			pid_List = null;
			current_Heap_page = null;
			current_RID = null;
		//}catch(ChainException e){
		//	throw new ChainException(e,"ChainException");
		//}
		

	}
	public void close()throws ChainException
	{
		try{
			finalize();
			current_heap_file = null;
		}catch(ChainException e){
			throw new ChainException(e,"ChainException");
		}
	}
	public boolean hasNext() {
			boolean is_there = pid_List.hasNext();
			return is_there;

	}

	public Tuple getNext(RID rid){
		if (current_RID != null) {
			rid.copyRID(current_RID);
			current_RID = current_Heap_page.nextRecord(current_RID);
			byte[] temp =  current_heap_file.selectRecord(rid);

			return new Tuple(temp);//need to change this	
		}
		if (current_RID == null) {
			if (pid_List.hasNext()) {
				Minibase.BufferManager.unpinPage(current_Heap_page.getCurPage(), false);
				PageId temp = pid_List.next();

				Minibase.BufferManager.pinPage(temp, current_Heap_page, false);
				current_RID = current_Heap_page.firstRecord();

				if (current_RID == null)
						Minibase.BufferManager.unpinPage(temp, false);
			} else {
				Minibase.BufferManager.unpinPage(current_Heap_page.getCurPage(), false);
			}
		}
		return null;
	}
}