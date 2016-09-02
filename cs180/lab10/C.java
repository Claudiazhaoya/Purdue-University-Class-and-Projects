public class C extends B{
    public C(String s){super("C"+s+"C");}
    public static void main(String[] args){
        C c = new C("ABC");
        System.out.println(c);
    }
}