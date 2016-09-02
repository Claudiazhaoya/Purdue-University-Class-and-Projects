
class TestRPNSimpleCalculator {

    public static String help() {
	String s = "";
	s += "Usage:\n";
	s += "  java TestRPNSimpleCalculator (test1|test2|test3|test4|test5)\n";

	return s;
    }

    public static void main( String [] args ) {
	if (args.length < 1 ) {
	    System.out.println( help() );
	    System.exit(1);
	}

	String testName = args[ 0 ];
	RPNSimpleCalculator calc = new RPNSimpleCalculator();

	if ( testName.equals( "test1" )  ) {
	    System.out.println("test1: 2 3 +");
	    try {
		double d = calc.evaluate( "2 3 +" );
		System.out.println(d);
		if ( d == 5 ) {
		    System.out.println(">>> test1 passed");
		    System.exit( 0 );
		}
		else {
		    System.out.println("*** test1 failed");
		    System.exit( 0 );
		}		    
	    }
	    catch ( RPNEvaluationException e ) {
		System.out.println("*** RPN Evaluation Exception");
	    }
	}
	else if ( testName.equals( "test2" )  ) {
	    System.out.println("test2: 2 3 * 4 * 4 - 2 / 6 8 + +");
	    try {
		double d = calc.evaluate( "2 3 * 4 * 4 - 2 / 6 8 + +" );
		System.out.println(d);
		if ( d == 24 ) {
		    System.out.println(">>> test2 passed");
		    System.exit( 0 );
		}
		else {
		    System.out.println("*** test2 failed");
		    System.exit( 0 );
		}		    
	    }
	    catch ( RPNEvaluationException e ) {
		System.out.println("*** RPN Evaluation Exception");
	    }
	}
	else if ( testName.equals( "test3" )  ) {
	    System.out.println("test3: 2 3 + +");
	    try {
		double d = calc.evaluate( "2 3 + +" );
		System.out.println("*** test3 failed");
	    }
	    catch ( RPNEvaluationException e ) {
		System.out.println("Not enough operands");
		System.out.println(">>> test3 passed");
	    }
	}
	else if ( testName.equals( "test4" )  ) {
	    System.out.println("test4: 2 3 4 +");
	    try {
		double d = calc.evaluate( "2 3 4 +" );
		System.out.println("*** test4 failed");
	    }
	    catch ( RPNEvaluationException e ) {
		System.out.println("Not enough opertors");
		System.out.println(">>> test4 passed");
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
