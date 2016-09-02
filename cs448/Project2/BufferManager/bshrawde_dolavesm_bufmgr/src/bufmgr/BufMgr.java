package bufmgr;					//set the package to be bufmgr

import global.GlobalConst;		//import the GlobalConst packgae so we can use the parts of it and some exceptions
import global.Convert;			//import the convert package I used this to during testing where I would convert the byte array to an int. 
import global.Minibase;			//import the mimibase package so we can access the disk manager
import global.PageId;			//import the PageId package so we can have PageId objects and thier childern
import global.Page;				//import the Page package so we can have Page objects and thier childern
import java.util.LinkedList;	//import the Linked List package, we use this for the replacement cannidate list
import java.util.Arrays;		//import the array package.
import diskmgr.*;				//import the disk manager package, used to Allocate and deallocate pages
import chainexception.ChainException;	//import chain exception class so we can throw chain exceptions

import java.io.IOException;			//import the IOException package so we can throw IOExceptions

public class BufMgr {
	/**
	 * Create the BufMgr object.
	 * Allocate pages (frames) for the buffer pool in main memory and
	 * make the buffer manage aware that the replacement policy is
	 * specified by replacerArg (e.g., LH, Clock, LRU, MRU, LFU, etc.).
	 *
	 * @param numbufs number of buffers in the buffer pool
	 * @param lookAheadSize: Please ignore this parameter
	 * @param replacementPolicy Name of the replacement policy, that parameter will be set to "LFU" (you
	 can safely ignore this parameter as you will implement only one policy)
	 */
	private final static int HTSIZE = 2017; //HTSIZE must be a prime number

	private Page frames[];							//array of pages,the index they are in is the frame they are in
	private descriptors bufDescr[];					//array of object type descriptors, has three parts PageId,pin count and dirty bit
	private int numbufs, lookAheadSize, pin_count;	//set some ints to be used later
	private String replacementPolicy;				//String for the Replacement plocy we use LFU so this does not really matter
	private int counter = 0;						//have a counter here for the number of frames in use dont really use this taht much
	private int numBufs;							//int to have the number of buffers total used in the return num buffers method
	private HashMap hashy = new HashMap();			//create the hash table for the page id /frame numbers
	DiskMgr disk = new DiskMgr();					//instance of diskmanager
	private LinkedList<Integer> replacelist = new LinkedList<Integer>();	//create the linkedlist for the replacement cannidates


	public BufMgr(int numbufs, int lookAheadSize, String replacementPolicy) {	
		this.frames = new Page[numbufs];						//this area sets global variables and inililizes the descriptor array,page array and replacecannidate linked list
		this.numBufs = numbufs;							
		this.bufDescr = new descriptors[numbufs];
		this.replacementPolicy = replacementPolicy;
		this.lookAheadSize = lookAheadSize;
		this.numbufs = numbufs;


		for(int v = 0; v < numbufs;v++){
			bufDescr[v] = new descriptors();
			frames[v] = new Page();
			replacelist.add(new Integer(v));
		}
	}

	/**
	 * Pin a page.
	 * First check if this page is already in the buffer pool.
	 * If it is, increment the pin_count and return a pointer to this
	 * page.
	 * If the pin_count was 0 before the call, the page was a
	 * replacement candidate, but is no longer a candidate.
	 *
	 * If the page is not in the pool, choose a frame (from the
	 * set of replacement candidates) to hold this page, read the
	 * page (using the appropriate method from {\em diskmgr} package) and pin it.
	 *
	 * Also, must write out the old page in chosen frame if it is dirty
	 * before reading new page.__ (You can assume that emptyPage==false for
	 * this assignment.)
	 *
	 * @param pageno page number in the Minibase.
	 * @param page the pointer point to the page.
	 * @param emptyPage true (empty page); false (non-empty page)
	 */

	
	public void pinPage(PageId pageno, Page page, boolean emptyPage)throws IOException,
							BufferPoolExceededException, 
							HashEntryNotFoundException,
							PagePinnedException,
	       						InvalidPageNumberException, 
							FileIOException,DiskMgrException
	{
		
		try{
			int frameno;
			if((frameno = hashy.get(pageno))!=-1){
				if(bufDescr[frameno].pin_count==0){
					replacelist.remove(new Integer(frameno));					//if the pin count was 0 remove from relplaement list
				}
				bufDescr[frameno].pin_count++;									//increase the pin count

			page.setPage(frames[frameno]);										//set the page refrence to the correct location found by PageId
		       	}else{
				if(replacelist.peekFirst() == null){							//peek the linked list to see if it is null if so throw error
				 	throw new BufferPoolExceededException(null,
					"buffer is full in pin page");
				}else{
					frameno = replacelist.remove();	
					if(bufDescr[frameno].page_num.pid !=-1){					//if page is in descriptor array
						if(bufDescr[frameno].dirtybit==true){  					//if dirty flush
						     flushPage(bufDescr[frameno].page_num);
							//System.out.println("flushy");
						}	       
						if((hashy.get(bufDescr[frameno].page_num)) !=-1) 		//if page is in hash table remove
						{
								//System.out.println("herhehe");
							hashy.remove(bufDescr[frameno].page_num);
						}

						//page.setPage(frames[frameno]);
					}

					page.setPage(frames[frameno]);								//set the page refrence to the correct location
				
					Minibase.DiskManager.read_page(pageno,page);				//read the new page from the disk
					PageId id = new PageId(pageno.pid);							//make a new pageId for the new page
					bufDescr[frameno] = new descriptors(id,1,false);			//set the descriptor for the new page
					hashy.insert(pageno,frameno);								// insert into the hash table
				}
		 	}
		}catch (InvalidPageNumberException e){									//throw exceptions caught in try catch block
			throw new InvalidPageNumberException(e,
			"invalid page number in pin page");
		}catch (FileIOException e){
			throw new FileIOException(e,"File IO exception in pin page");
		}
	}


	/**
	 * Unpin a page specified by a pageId.
	 * This method should be called with dirty==true if the client has
	 * modified the page.
	 *
	 * If so, this call should set the dirty bit
	 * for this frame.
	 * Further, if pin_count>0, this method should
	 * decrement it.
	 *If pin_count=0 before this call, throw an exception
	 * to report error.
	 *(For testing purposes, we ask you to throw
	 * an exception named PageUnpinnedException in case of error.)
	 *
	 * @param pageno page number in the Minibase.
	 * @param dirty the dirty bit of the frame
	 */
	public void unpinPage(PageId pageno, boolean dirty) throws PageUnpinnedException,
							    HashEntryNotFoundException
	{
		int frameno;
		if((frameno = hashy.get(pageno)) == -1){							//if the page does not does not exsist in the hash table throw an error
			throw new HashEntryNotFoundException(null,"no hash");
		}else{

			if(bufDescr[frameno].pin_count == 0){							//if the old pin count = 0 throw an error 
				throw new PageUnpinnedException(null,"page unpinned");
			}
			if(dirty ==true){												//if the dirty parameter is true, set the dirty bit to be true
				bufDescr[frameno].dirtybit=true;
			}
			bufDescr[frameno].pin_count--;									//decrement the pin count
			if(bufDescr[frameno].pin_count==0){								//if the new pin count = 0 page is now replace cannidate add it to the linked list 
				replacelist.add(new Integer(frameno));

			}
		}
	}
	/**
	 * Allocate new pages.
	 * Call DB object to allocate a run of new pages and
	 * find a frame in the buffer pool for the first page
	 * and pin it. (This call allows a client of the Buffer Manager
	 * to allocate pages on disk.)
	 * If buffer is full, i.e., you
	 * can't find a frame for the first page, ask DB to deallocate
	 * all these pages, and return null.
	 *
	 * @param firstpage the address of the first page.
	 * @param howmany total number of allocated new pages.
	 *
	 * @return the first page id of the new pages.__ null, if error.
	 */
	public PageId newPage(Page firstpage, int howmany)throws BufferPoolExceededException 
							   ,HashEntryNotFoundException,
							   PagePinnedException,
							   InvalidPageNumberException,
							   FileIOException,DiskMgrException,
							   IOException,ChainException
	{ 
		PageId pid = null;

		try{
			//System.out.println("before allocate:");
			pid = Minibase.DiskManager.allocate_page(howmany);							//try to allocate the pages
				//System.out.println("new: "+pid.pid);

		}catch (Exception e){throw new DiskMgrException(e,"allocate page error");}		//if we have issue allocating throw Exception

		try{
			pinPage(pid,firstpage,false);												//try to pin the first page allocated

		}catch(Exception e){															//throw exceptions caught in try block
			try{
				Minibase.DiskManager.deallocate_page(pid,howmany);
			}catch (Exception b){
				throw new DiskMgrException(e,"pin error in new page");
			}
			throw new DiskMgrException(e,"pin error in new page");
		}	
			
		return pid;																		//return the PageId of the first page allocated

	}
	/**
	 * This method should be called to delete a page that is on disk.
	 * This routine must call the method in diskmgr package to
	 * deallocate the page.
	 *
	 * @param globalPageId the page number in the data base.
	 */
	public void freePage(PageId globalPageId) throws BufferPoolExceededException, //unknonwn
						HashEntryNotFoundException,PageUnpinnedException,ChainException,
						PagePinnedException 
	{
		int frameno;			
		int pin_temp;
		PageId replacement = new PageId(-1);		
		try{
			if((frameno = hashy.get(globalPageId)) !=-1){						//get the frame number from hash table
				pin_temp = bufDescr[frameno].pin_count;							//get the pin count
					//System.out.println("pin count: "+pin_temp);
				if(pin_temp>1){
					throw new PagePinnedException(null,							//if the pin count is greater than 1 throw an exception
					"page pinned error in free page");
				}else{
					if(pin_temp==1){

						unpinPage(globalPageId,false);							//if the pin count is 1  unpin and contiune
					}
					if(pin_temp==0){											//if the pin count is 0 do contiune
						//replacelist.add(new Integer(frameno));
					}
					Minibase.DiskManager.deallocate_page(globalPageId);			//deallocate the page given a page id 
					hashy.remove(globalPageId);									//remove from the hash table because it is no loger relevant
					bufDescr[frameno].page_num = replacement;					// set the descritor values back to default
					bufDescr[frameno].pin_count = 0;
					bufDescr[frameno].dirtybit = false;
				}
			}
		}catch(PagePinnedException e){											//throw excetptions caught in the try statment
			throw new PagePinnedException(e,"Page Pinned Exception");
		}catch(PageUnpinnedException e){
			throw new PageUnpinnedException(e,"Page Unpinned Exception");
		}catch(ChainException e){
			throw new ChainException(e,"Chain Exception");
		}
	}
	/**
	 * Used to flush a particular page of the buffer pool to disk.
	 * This method calls the write_page method of the diskmgr package.
	 *
	 * @param pageid the page number in the database.
	 */
	public void flushPage(PageId pageid) throws InvalidPageNumberException,
						FileIOException ,DiskMgrException,
						IOException
	{
		try{
			int frame = hashy.get(pageid);				//get the frame number given the PageID
			Minibase.DiskManager.write_page(pageid,frames[frame]);	//writes page to the disk
		}catch (InvalidPageNumberException e){
			throw new InvalidPageNumberException(e,"invalid page num in flush"); //throws invalid page num 
		}catch (FileIOException e){
			throw new FileIOException(e,"File IO exception in flush"); //throw File IO exception
		}catch (Exception e){

		}
	}
	/**
	 *
	 * Used to flush all dirty pages in the buffer pool to disk
	 *
	 */
	public void flushAllPages() throws InvalidPageNumberException,FileIOException,
					DiskMgrException,IOException { // this will simply iterate through all the frames and send the PageId in them to flush
		PageId check = new PageId(-1);
		for(int i=0;i<bufDescr.length;i++){
			if(bufDescr[i].page_num.equals(check)){
				flushPage(bufDescr[i].page_num);
			}
		}
	}
	/**
	 * Returns the total number of buffer frames.
	 */
	public int getNumBuffers() {  //the number of buffers is the same as the number of frames. 
		return frames.length;
	}
	/**
	 * Returns the total number of unpinned buffer frames.
	 */
	public int getNumUnpinned(){ //the number of unpined is directly related to the size of our replace list set to int and return
		int unpinned = 0;					
		unpinned = replacelist.size();			
		return unpinned;
	}
}