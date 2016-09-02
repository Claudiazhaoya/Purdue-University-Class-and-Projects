import java.util.Scanner;
public class InternationalStudent extends Student{
    public String country;
    public String visaStatus;
    
    public InternationalStudent(String country,String visaStatus){
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
        GPA = in.nextInt();
        System.out.println("enter the country of origin:");
        country = in.nextLine();
        System.out.println("enter the visa status:");
        visaStatus = in.nextLine();
        
        
    }
}
    