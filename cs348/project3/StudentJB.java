import java.sql.*;

public class StudentJB{
	Connection con;

	public StudentJB(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e) {e.printStackTrace();}
		
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@claros.cs.purdue.edu:1524:strep","bshrawde","CSEagle08");
		}catch(SQLException e) {e.printStackTrace();}
	}
	public void getStudentsInDepartment(int department) {
		String query = "SELECT sname FROM STUDENT where depid="+department;

		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				String name = rs.getString("sname");
				System.out.println(name);
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	public void getStudentsInClass(String cname){
		String query = "Select snum from Enrolled where cname='"+cname+"'";
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				int snum = rs.getInt("snum");
				String nestedQuery = "Select sname from Student where snum="+snum;

				Statement stmtNested = con.createStatement();
				ResultSet rsNested = stmtNested.executeQuery(nestedQuery);

				while(rsNested.next()){
					String sname = rsNested.getString("sname");
					System.out.println(sname);
				}rsNested.close();
				stmtNested.close();
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){e.printStackTrace();}
	}
	public void enroll(int snum, String cname){
		String update = "insert into Enrolled values("+snum +",'"+cname+"','A')";

		try{
			Statement stmt = con.createStatement();
			stmt.executeUpdate(update);
			stmt.close();
		}catch(SQLException e){e.printStackTrace();}


	}

	public static void main(String[] args){
		StudentJB test = new StudentJB();
		test.getStudentsInDepartment(11);
		System.out.println("\nSECOND QUERY\n");
		test.getStudentsInClass("ENG40000");
		System.out.println("\nINSERT\n");
		test.enroll(191,"ENG40000");
		System.out.println("afer insert\n");
		test.getStudentsInClass("ENG40000");
	}
}
