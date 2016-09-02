
package bufmgr;
import java.util.*;
import global.GlobalConst;
import global.Minibase;
import global.PageId;
import global.Page;
import java.util.LinkedList;
import java.util.Arrays;
import diskmgr.*;

import java.io.IOException;

public class MyNode {
	PageId pid;
	int frame;
	
	public MyNode(PageId pid, int frame){
		this.pid = pid;
		this.frame = frame;
	}
	
	public PageId getPageId(){
		return this.pid;
	}
	
	public int getFrame(){
		return this.frame;
	}
	
};
