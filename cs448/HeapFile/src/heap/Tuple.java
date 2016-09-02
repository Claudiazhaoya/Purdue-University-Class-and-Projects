package heap;

import java.util.*;
import global.*;
import diskmgr.*;
import java.io.*;

public class Tuple {
	protected byte[] data;
	protected int length;
	
	public Tuple(byte[] data, int ridIndex, int length){
		this.data = data;
		this.length = data.length;
	}
	public int getLength(){
		return this.length;
	}
	public byte[] getTupleByteArray(){
		return data;
	}
}