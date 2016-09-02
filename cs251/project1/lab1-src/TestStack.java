
public class TestStack {

    public static String help() {
	String s = "";
	s += "Usage:\n";
	s += "  java TestStack (array|list) (test1|test2|test3|test4|test5|test6)\n";

	return s;
    }

    public static Stack newStack( String stackType, int numberOfElements  ) {
	if ( stackType.equals( "array" ) ) {
	    return new StackArray( numberOfElements );
	}
	else if ( stackType.equals( "list" ) ) {
	    return new StackList();
	}

	return null;
    }
    
    public static void main( String [] args ) {
	if (args.length < 2 ) {
	    System.out.println( help() );
	    System.exit(1);
	}

	if ( !args[ 0 ].equals( "array" ) && 
	     !args[ 0 ].equals( "list" )) {
	    System.out.println( help() );
	    System.exit(1);
	}

	String stackType = args[ 0 ];
	String testName = args[ 1 ];

	if ( testName.equals( "test1" )  ) {
	    System.out.println("test1: Simple push/pop");
	    Stack stack = newStack( stackType, 10);
	    try {
		stack.push(5);
		double val = stack.pop();
		if ( val == 5 ) {
		    System.out.println(">>> test1 passed");
		    System.exit(0);
		}
		else {
		    System.out.println("*** test1 failed");
		}
	    }
	    catch ( StackEmptyException e ) {
		System.out.println("*** Stack Empty");
	    }
	    catch ( StackFullException e ) {
		System.out.println("*** Stack Overflow");
	    }
	}
	else if ( testName.equals( "test2" )  ) {
	    System.out.println("test2: Multiple push/pop");
	    Stack stack = newStack( stackType, 10);
	    try {
		stack.push(6);
		stack.push(7);
		stack.push(8);
		double val1 = stack.pop();
		double val2 = stack.pop();
		double val3 = stack.pop();
		if ( val1 != 8 ||
		     val2 != 7 ||
		     val3 != 6 ) {
		    System.out.println("*** test2 failed");
		}
		else {
		    System.out.println(">>> test2 passed");
		    System.exit(0);
		}
	    }
	    catch ( StackEmptyException e ) {
		System.out.println("*** Stack Empty");
	    }
	    catch ( StackFullException e ) {
		System.out.println("*** Stack Overflow");
	    }
	}
	else if ( testName.equals( "test3" )  ) {
	    System.out.println("test3: Stack empty");
	    Stack stack = newStack( stackType, 10);
	    try {
		double val = stack.pop();
		System.out.println("*** test3 failed");
	    }
	    catch ( StackEmptyException e ) {
		System.out.println(">>> test3 passed");
		System.exit(0);
	    }
	}
	else if ( testName.equals( "test4" )  ) {
	    System.out.println("test4: Stack full");
	    
	    if ( stackType.equals( "list" ) ) {
		System.out.println(">>> test4 passed (stackList has no limit)");
		System.exit(0);
	    }

	    Stack stack = newStack( stackType, 1);
	    try {
		stack.push(6);
		stack.push(7);
		System.out.println("*** test4 failed");
	    }
	    catch ( StackFullException e ) {
		System.out.println(">>> test4 passed");
		System.exit(0);
	    }
	}
	else if ( testName.equals( "test5" )  ) {
	    System.out.println("test5: Test numberOfEntries");
	    Stack stack = newStack( stackType, 10);
	    try {
		stack.push(5);
		stack.push(6);
		double val = stack.pop();
		stack.push(6);
		stack.push(7);
		stack.push(8);
		if ( stack.numberOfEntries() == 4 ) {
		    System.out.println(">>> test5 passed");
		    System.exit(0);
		}
		else {
		    System.out.println("*** test5 failed");
		}
	    }
	    catch ( StackEmptyException e ) {
		System.out.println("*** Stack Empty");
	    }
	    catch ( StackFullException e ) {
		System.out.println("*** Stack Overflow");
	    }
	}
	else if ( testName.equals( "test6" )  ) {
	    System.out.println("test6: Test output");
	    Stack stack = newStack( stackType, 10);
	    try {
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);

		// Expected output
		String s = "";
		s += "---Stack---\n";
		s += "4: 9.0\n";
		s += "3: 8.0\n";
		s += "2: 7.0\n";
		s += "1: 6.0\n";
		s += "0: 5.0\n";

		if ( s.equals( stack.toString() ) ) {
		    System.out.println(">>> test6 passed");
		    System.exit( 0 );
		}
		else {
		    System.out.println(">>> test6 failed");
		    System.out.print( "Expected:\n"+ s );
		    System.out.print( "Current:\n " + stack.toString() );
		}
	    }
	    catch ( StackFullException e ) {
		System.out.println("*** Stack Overflow");
	    }
	}
	else {
	    System.out.println( help() );
	    System.exit(1);
	}

	// Test failed
	System.exit(1);
    }
}

