

import java.util.ArrayList;
/**
 * Project 5 ---- History class
 * This class Contians two arrays that hold the shapes for the painter gui. One is used for undoing and redoing the other is used to hold the current shapes
 * when returned this array tells the Painter class what shapes should be on the gui.
 * @author Brian
 *
 *@ R01 (Medhi Azaarmi)
 *
 *@Date October 29 2012
 */

//makes the array undo and redo
public class History {
	ArrayList<Shape> undo =  new ArrayList<Shape>();
	ArrayList<Shape> redo =  new ArrayList<Shape>();


	
	// when called adds a shape to the array and clears the redo array
	public void add(Shape s){
		undo.add(s);
		redo.clear();
	}
	
	// when called takes the last element of the undo array and puts it into the redo array also the painter gui removes the last made shape it there is nothig
	//to undo nothing is done
	public void undo(){
		if(undo.size()!=0){
		redo.add(undo.get(undo.size()-1));
		undo.remove(undo.size()-1);
		}
		
	}
	
	//when called takes teh last element of the redo array and puts it into the undo array also the painter gui adds the last unded shape if there is 
	//nothing to redo nothing is done
	public void redo(){
		if(redo.size()!=0){
			
		undo.add(redo.get(redo.size()-1));
		redo.remove(redo.size()-1);
		}
	}
	
	//when called clears both the undo and redo arrays. also the painter gui remvoes all shapes from the screen 
	public void clear(){
		undo.clear();
		redo.clear();
	}

	
	//tells the painter class what shapes to draw on the screen 
	public Shape[] getShapes(){
		Shape[] myShape = new Shape[undo.size()];
		for(int i=0;i<undo.size();i++){
			myShape[i]=undo.get(i);
		}
		return myShape;
	}
}














