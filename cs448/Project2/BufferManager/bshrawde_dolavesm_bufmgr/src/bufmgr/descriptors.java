
package bufmgr;
import global.GlobalConst;
import global.PageId;
import global.Page;


import java.io.IOException;

public class descriptors{
	PageId page_num;
	int pin_count;
	boolean dirtybit;
	public descriptors(PageId page_num, int pin_count,boolean dirtybit){
		this.page_num = page_num;
		this.pin_count = pin_count;
		this.dirtybit = dirtybit;
	}
	public descriptors(){
		this.page_num = new PageId(-1);
		//System.out.println("in des: "+this.page_num.pid);
	}
}
