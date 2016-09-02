package heap;

import java.io.*;
import bufmgr.*;
import diskmgr.*;
import global.*;
import chainexception.ChainException;

public class HeapFile{
	private String name;
	private int recordCount;
	PageId pageno;
	HFPage hfpage;

	
	public HeapFile(String name){
		this.name = name;
		recordCount=0;
		hfpage = new HFPage();
		if(name != null && !(name.equals(""))){
			pageno = Minibase.DiskManager.get_file_entry(name);
			if(pageno == null){	
				pageno = Minibase.BufferManager.newPage(hfpage, 1);
				Minibase.DiskManager.add_file_entry(name, pageno);
				//hfpage.init(pageno, hfpage);
				Minibase.BufferManager.unpinPage(pageno, true);
			}
			/*else{
				Minibase.BufferManager.pinPage(pageno, hfpage, false);
				Minibase.BufferManager.unpinPage(pageno, true);
				//start counting records
				HeapScan scanner = this.openScan();
				while(scanner.hasNext()){
					recordCount++;
				}
				scanner.close();
			}*/
		}else{
			name = "temp";
			pageno = Minibase.BufferManager.newPage(hfpage, 1);
			Minibase.DiskManager.add_file_entry(name, pageno);
			//hpage.init(pageno, hfpage);
			Minibase.BufferManager.unpinPage(pageno, true);
		}
	}
	public RID insertRecord(byte[] record){
		RID rid = new RID();
		/*if(record.length > Minibase.BufferManager.MAX_SPACE){
			throw new SpaceNotAvailableException(null, "space not available");
		}
		HFPage insertpage = new HFPage();
		PageId insertpid = insertpage.getNextPage();
		while(insertpid != -1){
			Minibase.BufferManager.pinPage(insertpid, insertpage, false);
			
			if(insertpage.available_space() >= record.length){
				//RID temprid = insertpage.insertRecord(record);
				//rid.pageNo.pid = temp.pageNo.pid;
				//rid.slotNo = temp.slotNo;
				recordCount++;
				if(insertpage.availablwe_space() == 0){// the page doesnt have anymore space
				
				}
				Minibase.BufferManager.unpinPage(insertpid, true);
				return rid;
			}
			
			Minibase.BufferManager.unpinPage(insertpid, true);
			insertpid = insertpage.getNextPage();
		}*/
		//create a new page
		
		
		
		return rid;
	}
	public Tuple getRecord(RID rid){
		
		/*PageId pid = rid.pageNo;
		HFPage hfpage = new HFPage();
		pinPage(new PageId(id.pid), hfpage, false);
		Tuple tup = hfpage.getRecord();*/
		byte[] b = new byte[100];
		Tuple tup = new Tuple(b,0,1);
		return tup;
	}
	public boolean updateRecord(RID rid, Tuple newRecord){
		return true;
	}
	public boolean deleteRecord(RID rid){
		recordCount--;
		return true;
	}
	public int getRecCnt(){ //get number of records in the file
		return recordCount;
	}
	public HeapScan openScan(){
		HeapScan heapscanner = new HeapScan(this);
		return heapscanner;
	}
}
