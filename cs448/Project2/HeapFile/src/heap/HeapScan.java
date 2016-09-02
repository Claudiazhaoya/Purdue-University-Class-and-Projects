package heap;

import java.util.Iterator;

import chainexception.ChainException;
import global.*;

public class HeapScan {

	private HeapFile current_heap_file;
	private HFPage current_Heap_page;

	private RID current_RID;
	private Iterator<PageId> pid_List;

	protected HeapScan(HeapFile hf) {

		this.current_heap_file = hf;

		pid_List= hf.iterator();
		PageId currentId = pid_List.next();
		Page new_page=new Page();
		Minibase.BufferManager.pinPage(currentId, new_page, false);

		current_Heap_page = new HFPage(new_page);
		current_RID = current_Heap_page.firstRecord();

	}

	protected void finalize(){
	
		current_Heap_page = null;
		current_RID = null;
		pid_List = null;
	}

	public void close() throws ChainException // TODO
	{

		try {
			finalize();
			current_heap_file = null;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
	public boolean hasNext() {
			boolean is_there = pid_List.hasNext();
			return is_there;

	}
	public Tuple getNext(RID rid) throws IllegalStateException {
		//if(hf.getRecCnt() == 0)
			//throw new IllegalStateException();
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
		if (current_RID != null) {
			rid.copyRID(current_RID);
			current_RID = current_Heap_page.nextRecord(current_RID);
			return new Tuple(current_Heap_page.selectRecord(rid));
			
		}
		
	
		return null;

	}

}
