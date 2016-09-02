import java.util.*;
import java.lang.String;

class RPNSimpleCalculator {
StackList stack = new StackList();
    double x;
    double out;
    double value;
    double temp1;
    double temp2;
    public double evaluate( String s ) throws RPNEvaluationException {
    if(s.length()==0){
    	return 0;
    }
	String[] information = s.split(" ");
	for(int i=0;i<information.length;i++){
		if(information[i].equals("+")){
			try{
					temp1=stack.pop();
					temp2=stack.pop();
					value=temp1+temp2;
					stack.push(value);
				}
				catch(StackFullException e){
					throw new RPNEvaluationException();			
			}
			catch (StackEmptyException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("-")){
			try{
				temp1=stack.pop();
				temp2=stack.pop();
				value=temp2-temp1;
				stack.push(value);
			}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}catch(StackFullException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("x")){
			try{
				stack.push(x);
			}
			catch(StackFullException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("*")){
			try{
					temp1=stack.pop();
					temp2=stack.pop();
					value=temp1*temp2;
					stack.push(value);
			}
					catch(StackFullException e){
					throw new RPNEvaluationException();
					}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("/")){
			try{
				temp1=stack.pop();
				temp2=stack.pop();
				value=temp2/temp1;
				stack.push(value);
			}
				catch(StackFullException e){
				throw new RPNEvaluationException();
					}
				catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("sin")){
			try{
				temp1=stack.pop();
				value=Math.sin(temp1);
				stack.push(value);
			}
					catch(StackFullException e){
						throw new RPNEvaluationException();
			}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}
		}
		else if(information[i].equals("cos")){
			try{
				temp1=stack.pop();
				value=Math.cos(temp1);
				stack.push(value);
			}
					catch(StackFullException e){
						throw new RPNEvaluationException();
					}
				catch(StackEmptyException e){
					throw new RPNEvaluationException();
				}
			}
		else if(information[i].equals("pow")){
			try{
				temp1=stack.pop();
				temp2=stack.pop();
				value=Math.pow(temp2,temp1);
						stack.push(value);
				}
					catch(StackFullException e){
						throw new RPNEvaluationException();
			}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}	
		}
		else if(information[i].equals("ln")){
			try{
				temp1=stack.pop();
				value=Math.log(temp1);
					stack.push(value);
				}
				catch(StackFullException e){
				throw new RPNEvaluationException();
			}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}	
		}
		else if(information[i].equals("exp")){
			try{
				temp1=stack.pop();
				value=Math.exp(temp1);
					stack.push(value);
				}
				catch(StackFullException e){
					throw new RPNEvaluationException();
				}
			catch(StackEmptyException e){
				throw new RPNEvaluationException();
			}
		}
		
		else{//changes chars in the string to doubles for evalutation
			try{
				value = Double.parseDouble(information[i]);	
					stack.push(value);
			}
			catch (StackFullException e ){
				throw new RPNEvaluationException();
			}		
				}
		}
	if(stack.numberOfEntries()==1){
		try{
		out=stack.pop();
	}
			catch(StackEmptyException e){
			
		throw new RPNEvaluationException();
		}
		return out;
	}
	else{
		throw new RPNEvaluationException();
	}
   }
    public void setX(double _x) {
	x = _x;
    }
    public String help() {
	String s = "";
	s += "Usage:\n";
	s += "  java RPNSimpleCalculator \"rpn-expression\"\n\n";
	s += "Example:\n";
	s += "     java RPNSimpleCalculator \"3 2 - 5 +\"\n";

	return s;
    }
    public static void main( String [] args ) {

	RPNSimpleCalculator rpn = new RPNSimpleCalculator();

	if ( args.length == 0 || args.length > 1 ) {
	    System.out.println( rpn.help() );
	    System.exit(1);
	}

	try {
	    double d = rpn.evaluate( args[ 0 ] );
	    System.out.println( d );
	}
	catch ( RPNEvaluationException e ) {
	     System.out.println( "*** Error in evaluation" );
	}
    }
}
