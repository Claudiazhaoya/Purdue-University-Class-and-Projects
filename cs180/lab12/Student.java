public class Student extends Employee{
	public static void main(String[] args){
		System.out.println("Begin test");
		Student test = new Student();
		//Student test2 = (Student) Employee;
		double out;
		out=test.ammount(10000);
		System.out.println(out);
		System.out.println(test.getClass());
	//	System.out.println(test2.getClass());
	}
}
