import java.util.Scanner;
public class InternationalStudent extends Student{
    public String country;
    public String visaStatus;
    
    public InternationalStudent(String name, int year, double GPA,String country,String visaStatus){
        super(name,year,GPA);
        setCountry(country);
        setVisaStatus(visaStatus);
    }
    
    public void setCountry(String country){this.country = country;}
    public void setVisaStatus(String visaStatus){this.visaStatus=visaStatus;}
    
    public String getCountry(){return country;}
    public String getVisaStatus(){return visaStatus;}
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String name="";
        int year = 0;
        double GPA = 0.0;
        String country = "";
        String visaStatus="";
        
        
        System.out.println("enter the name:");
        name = in.nextLine();
        System.out.println("enter the year:");
        year = in.nextInt();
        System.out.println("enter the GPA:");
        GPA = in.nextDouble();
        in.nextLine();
        System.out.println("enter the country of origin:");
        country = in.nextLine();
        System.out.println("enter the visa status:");
        visaStatus = in.nextLine();
        
        InternationalStudent test = new InternationalStudent(name,year,GPA,country,visaStatus);
        
        String grade = test.YEARS[year-1];
        
        System.out.println("********");
        System.out.println("Student Details:");
        System.out.println("Name: "+test.getName());
        System.out.println("Year: "+grade);
        System.out.println("GPA: "+test.getGPA());
        System.out.println("Country: "+test.getCountry());
        System.out.println("Visa Status: "+test.getVisaStatus());
        
        
        
    }
}
    