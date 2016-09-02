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

public class HashMap {
	private final static int HTSIZE = 2017;
	private MyNode table[] = new MyNode[HTSIZE];
	
	public void HashMap(){
		for(int i=0;i<HTSIZE;i++){
			table[i] = null;
		}
	}
	
	public int hash(PageId pid){
		int value = pid.hashCode();
		return (1*value+0) % HTSIZE;
	}
	
	public boolean insert(PageId pid, int frame){
		MyNode node = new MyNode(pid,frame);
		int value = hash(pid);
				//System.out.println("hash value: "+value);
		if(table[value]!=null){
			return false;
		}
		table[value] = node;
		return true;
	}
	
	public boolean remove(PageId pid){
		int value = hash(pid);
		if(table[value]==null){
			return false;
		}
		table[value] = null;
		return true;
	}
	
	public int get(PageId pid){
		int value = hash(pid);
			//System.out.println("inside pid: "+pid);
		MyNode node = table[value];
		if(node==null){
			return -1;
		}
		return node.frame;
	}
	
}
