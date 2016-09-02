import java.util.Scanner;

/**
 * Project 3 -- Simple Date Calculator
 * This program will take to dates from the user in the form of start year month and day
 * and end year month day, then it will check to make sure that the dates are valid, then
 * will find the number of days between the two dates, and dispay it for the user.
 * 
 * @author Brian Shrawder
 * 
 * @recitation R01 (Mehdi Azarmi)
 * 
 * @date October 5, 2012
 *
 */


public class Project3 {
	//these methods handle error in input by the user
	
	
/**
 * this method checks to see if the end year is less than the start year, if so it 
 * returns true
 * @param endYear the end year entered by the user
 * @param startYear the start year entered by the user
 * @return
 */
	
	
    public static boolean yearError (int endYear,int startYear) {
    	if(startYear>endYear)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	}
    
    
 /**
  * this method checks to see if the months inputed by the user are less than 1 and
     * more than 12 if so it returns true
  * @param month the month entered by the user
  * @return returns true or false depeding on the result of the check
  */
    
    
    public static boolean monthError (int month){
    	
    	if(month<1||month>12)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	}
    
    
    /**this method checks to make sure that the day input by the user is within the month
     * given by the user and in the right year because of leap year
     * @param day the day entered by the user
     * @param month the month entered by the user
     * @param year the year entered by the user to check for leap year
     * @return
     */
    
    
    public static boolean dayError (int day, int month,int year){
    
        if(day<1){
    		return true;
        }
        if(month==1 && day >31){
        	return true;
        }
    			
        if(isLeapYear(year) ==true)
        {
        if(month==2 && day >29) 
        		return true;
        }
        else 
        {
          
        if(month==2 && day >28)
        		return true;
        }
    	
        if(month==3 && day>31)
    		 return true;
    	
        if(month==4 && day>30)
    		 return true; 

        if(month==5 && day>31)
    		 return true;
    	
        if(month==6 && day>30)
    		 return true;

        if(month==7 && day>31)
    	    return true;

        if(month==8 &&day>31)
    		return true;
    	
        if(month==9 &&day>30)
    		return true;

        if(month==10 &&day>31)
    		return true;

        if(month==11 &&day>30)
    		return true;

        if(month==12 &&day>31)
    		return true;
        else
    		return false;
     	
    }
    
    
    //these methods do the calculations
    
        
/**
 * this method checks to see if a given year is a leap year if it is it returns true
 * @param year the year entered by the user or every year in a date range
 * @return returns true or false depending on the result of the check
 */
    
    
    	 public static  boolean isLeapYear (int year) 
	 {  
	    if ((year % 400 == 0) || (year % 4==0))	
	    {
		 return true;
	    }
	    else 
	    {
			 return false; 
	    }
	 }
    	 
/**
 * this method finds the number of days in each month
 * @param year the year entered by the user to find if a year is a leap year
 * @param month the month entered by the user
 * @return returns the days in the given month
 */
    	 
    	 
    	 public static  int daysInMonth (int year, int month){
    		 int daysInMonth=0;
	    {
    	if(month==1)
    			 daysInMonth = 31;
        }
    		 
    	if(isLeapYear(year)==true)
    	{
    	if(month==2)
        {
    			 daysInMonth=29;
  	    }
    				 
        }
    	else
    	{
    	if(month==2)
        {
    		daysInMonth =28;
        }
    	} 
        if(month==3){
    		daysInMonth = 31; 
    	}
        if(month==4){
    		daysInMonth = 30;
    	}
        if(month==5){
    	    daysInMonth = 31;
    	}
    	if(month==6){
    		daysInMonth = 30;
    	}
    	if(month==7){
    		daysInMonth = 31;
    	}
    	if(month==8){
    		daysInMonth = 31;
    	} 
    	if(month==9){
    	    daysInMonth = 30;
    	}
    	if(month==10){
    		daysInMonth = 31;
    	}
    	if(month==11){
    		daysInMonth = 30;
    	}
    	if(month==12){
    		daysInMonth = 31;
    	}
    	return daysInMonth;
    	}
    	 
   public static int daysInYear(int year)
    	{
    	int daysInYear=0;
    	if(isLeapYear(year)==true)
    	{
    		daysInYear=366;
    	}
    	else
    	{
    		daysInYear=365;
    	}
    	return daysInYear;
    	}
    	
    	 
    
    	/**
    	 * this method finds the difference in days between to years 
    	 * @param year the year entered by the user to check for leap year
    	 * @param startYear the start year is used to find the number for the start year
    	 * @param endYear the end year is used to find the number for the end year
    	 * @return returns the number of days between the two years
    	 */
    	 
    	 
     public static int middiff(int year,int startYear,int endYear)
        {
    	int middiff=0;
		for(year=startYear; year <= endYear; year++){
    	if(isLeapYear(year)==true)
    	{
    		 middiff=middiff+366;
    	}
        if(isLeapYear(year)==false){
    		 middiff=middiff+365;
    	}
        }
    	 
		return middiff;
     }
     
     
    /**
     * this method is used to find the number of days that are not used 
     * at the end of the end year
     * @param year the year is used to find if a year is a leap year
     * @param month the month is used to find the number of days in each month
     * @param endMonth endMonth is used to find only the values for the end month
     * @param day day is used to find the day entered by the user for later use
     * @param daysInMonth is used to find the number days in the given month
     * @return returns the number of days not used at the end of the date range
     */
     
     
     public static int daysNotUsed(int year,int month,int endMonth,int day,int daysInMonth)
     {
    	 int daysNotUsed=0;
    	 month=endMonth;
    	
        if(month==1)
    	{
    			daysNotUsed=(daysInMonth(year,1))+(daysInMonth(year,2))
    				 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    				 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    				 +(daysInMonth(year,9))+(daysInMonth(year,10))+(daysInMonth(year,11))+day;
 
    			
    	}
    	if(month==2){
    		 daysNotUsed=(daysNotUsed+(daysInMonth(year,1))+day);
    	}
    	if(month==3){
    		 daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))+day;
    	}
    	if(month==4){
    		 daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    				 +(daysInMonth(year,3))+day;
    	}
    	if(month==5){
    		 daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+day;
    	}
    	if(month==6){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +day;	 
    	}
    	if(month==7){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+day;
    			 
    	}
        if(month==8){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+day;
    			 
    	}
    	if(month==9){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))+day;
    			 
    	}
    	if(month==10){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +(daysInMonth(year,9))+day;
    	}
    	if(month==11){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +(daysInMonth(year,9))+(daysInMonth(year,10))+day;
    	}
    	if(month==12){
    		daysNotUsed=daysNotUsed+(daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +(daysInMonth(year,9))+(daysInMonth(year,10))+(daysInMonth(year,11))+day;
    	}
    		daysNotUsed=(daysInYear(year))-daysNotUsed;
    	 return daysNotUsed;
     }

     
    /**
     * this method finds the number of days that are used in the start year 
     * before the date given
     * @param year year is used to find if the given year is a leap year
     * @param month is used to set month for the month method
     * @param startMonth uses the month first entered by the user
     * @param day uses the first day entered by the user
     * @param daysInMonth used to find the number of days in the month given
     * @return returns the number of days already used at the beginning of the year
     */
     
     
     public static int daysUsed(int year,int month,int startMonth,int day,int daysInMonth)
            {
    	 int daysUsed=0;
    	 month=startMonth;
    		 if(month==12){
    			 daysUsed=((daysInYear(year))-(daysInMonth(year,12))-day);
    	
    	}
    	if(month==11){
    		 daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +(daysInMonth(year,9))+(daysInMonth(year,10))+day);
    	}
    	if(month==10){
    		 daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +(daysInMonth(year,9))+day); 
        }
    	if(month==9){
    		 daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+(daysInMonth(year,8))
    					 +day);
    	}
    	if(month==8){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+(daysInMonth(year,7))+day);
    	}
    	if(month==7){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +(daysInMonth(year,6))+day); 			 
    	}
    	if(month==6){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+(daysInMonth(year,5))
    					 +day);
    	}
    	if(month==5){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+(daysInMonth(year,4))+day);
    	}
    	if(month==4){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +(daysInMonth(year,3))+day);
    	}
    	if(month==3){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+(daysInMonth(year,2))
    					 +day);
    	}
    	if(month==2){
    		daysUsed=daysInYear(year)-((daysInMonth(year,1))+day);
    	}
    	if(month==1){
    			 daysUsed=day;
    	}
    	if(month==1)
    		daysUsed=daysUsed;
    	else
    			 daysUsed=(daysInYear(year)-daysUsed);
 
    	return daysUsed;
     }
    	 
     
    	 
    	 
    	 
    	/**
    	 * the main method gets the input from the user and calls the other methods
    	  * to check input and get the number of days between the dates 
    	 * @param args
    	 */
     
	public static void main(String[] args){
		int startYear,endYear,startMonth,endMonth,startDay,endDay,
				 daysInMonth=0;	
		
		
	
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter the start year: ");
		startYear = scanner.nextInt();
		
		System.out.println("Please enter the start month: ");
		startMonth = scanner.nextInt();
		
		if(monthError(startMonth)==true)
		{
		    	System.out.println("error");
		    	System.exit(0);
		}
		
		System.out.println("Please enter the start day: ");
		startDay = scanner.nextInt();
	    if(dayError(startDay,startMonth,startYear)==true)
	    {
	    	 System.out.println("error");
	    	 System.exit(0);
	    }
			
		System.out.println("Please enter the end year: ");
		endYear = scanner.nextInt();
				
		System.out.println("Please enter the end month");
		endMonth= scanner.nextInt();
		if(monthError(endMonth)==true)
		{
	    	 System.out.println("error");
	    	 System.exit(0);
	    }
			
		System.out.println("Please enter the end day");
	    endDay = scanner.nextInt();	
	    if(dayError(endDay,endMonth,endYear)==true)
	    {
	    	 System.out.println("error");
	    	 System.exit(0);
	    }
	    if(yearError(endYear,startYear)==true)
	    {
	    	 System.out.println("error");
	    	 System.exit(0);
	    }
	    if(startYear==endYear&&startMonth==endMonth&&startDay==endDay){
	    	System.out.println("error");
	    	System.exit(0);
	    }
	    int year=startYear;
	    int ddiff=0;
	    int month=1;
	    ddiff=(middiff(year,startYear,endYear))-((daysUsed(year,month,startMonth,startDay,daysInMonth))
	    		 +(daysNotUsed(year,month,endMonth,endDay,daysInMonth)));

	  
	     
		System.out.println("There are  "+ddiff+" days between "+endMonth+"/"+ endDay+"/"+endYear+" and " + startMonth+"/"+startDay+"/"+startYear);
	}
	
}
		
					
				
	
	


