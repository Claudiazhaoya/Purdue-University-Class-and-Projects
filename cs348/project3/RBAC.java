import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class RBAC{
	//Scanner input = new Scanner(System.in);
	Connection con;
	String current_user = "";

	public RBAC(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException e){e.printStackTrace();}
		try{
			con = DriverManager.getConnection("jdbc:oracle:thin:@claros.cs.purdue.edu:1524:strep","bshrawde","CSEagle08");
		}catch(SQLException e){e.printStackTrace();}
	}
	public void getUSERS(){
		String query = "SELECT * FROM USERS";
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				String name = rs.getString("username");
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){e.printStackTrace( );}
	}
	public void LogIn(String username, String password){
		
		String query = "SELECT password FROM USERS WHERE username ='"+username+"'";
		String passw="";
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()){
				String pass = rs.getString("password");
				passw=pass;
			}
			rs.close();
			stmt.close();
		}catch(SQLException e){e.printStackTrace();}
		if(passw.equals(password)){
			current_user = username;
			System.out.println("Login successfull");
		}else{
			System.out.println("Invalid Login");
		}
	}
	public void CreateRole(String roleName, int encKey){
		if(current_user.equals("admin")){
			String update = "INSERT into ROLES values('"+roleName +"',"+encKey+")";
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate(update);
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}

		}else{
			System.out.println("Authorization failure");
		}
	}
	public void CreateUser(String username, String password){
		if(current_user.equals("admin")){
			String update = "INSERT into USERS values('"+username +"','"+password+"')";
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate(update);
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
			System.out.println("User created successfully");
		}else{
			System.out.println("Authorization failure");
		}
	}
	public void GrantRole(String username, String roleName){
		if(current_user.equals("admin")){
			String update = "INSERT into USERROLES values('"+username +"','"+roleName+"')";
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate(update);
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
			System.out.println("Role assigned successfully");
		}else{
			System.out.println("Authorization failure");
		}
	}
	public void GrantPrivilege(String privName, String roleName,String tableName){
		if(current_user.equals("admin")){
			String update = "INSERT into ROLEPRIVILEGES values('"+roleName +"','"+tableName+"','"+privName+"')";
			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate(update);
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
		}else{
			System.out.println("Authorization failure");
		}
	}
	public void RevokePrivilege(String privName, String roleName,String tableName){
		if(current_user.equals("admin")){
			String update = "DELETE from ROLEPRIVILEGES where privName = '"+privName+"' AND roleName ='"+roleName+"' AND tableName ='"+tableName+"'";

			try{
				Statement stmt = con.createStatement();
				stmt.executeUpdate(update);
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
		}else{
			System.out.println("Authorization failure");
		}
	}
	public void InsertInto(String tableName,String valueList,int column_No, String ownerRole){
		String query = "SELECT roleName FROM USERROLES where username = '"+current_user+"'";
		String role="";
		String priv="";
		String table="";
		int enc = 0;
		boolean ok = false;
		boolean encrypt = false;
		if(column_No>0){
			encrypt = true;
			String encNum = "SELECT encryptionKey FROM ROLES where roleName = '"+ownerRole+"'";
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(encNum);

				while(rs.next()){
					enc = rs.getInt("encryptionKey");
				}rs.close();
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
		}
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while(rs.next()){
					role = rs.getString("roleName");
					String nestedQuery = "Select privName,tableName FROM ROLEPRIVILEGES where roleName = '"+role+"'";
					Statement stmtNested = con.createStatement();
					ResultSet rsNested = stmtNested.executeQuery(nestedQuery);

					while(rsNested.next()){
						priv = rsNested.getString("privName");
						table = rsNested.getString("tableName");
						if(priv.equals("INSERT")&&table.equals(tableName)){
							ok = true;
							break;
						}else{
							ok = false;
						}
					}rsNested.close();
					stmtNested.close();
					if(ok){
						break;
					}
				}
				rs.close();
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
			if(ok){
				String test[] = valueList.split("[\\W]");
				if(encrypt){
					char[] shifter = test[column_No].toCharArray();
					for(int i=0;i<shifter.length;i++){
						shifter[i] += enc;
					}
					test[column_No] = String.valueOf(shifter);
				}
				if(test.length>4){
					String update = "INSERT into "+tableName+" values ('"+test[1]+"','"+test[2]+"','"+test[3]+"','"+test[4]+"',"+column_No+",'"+ownerRole+"')";
					try{
						
						Statement stmt = con.createStatement();
						stmt.executeUpdate(update);
						stmt.close();
					}catch(SQLException e){e.printStackTrace();}
				}else{
				String update = "INSERT into "+tableName+" values ('"+test[1]+"','"+test[2]+"',"+column_No+",'"+ownerRole+"')";
				try{
					Statement stmt = con.createStatement();
					stmt.executeUpdate(update);
					stmt.close();
				}catch(SQLException e){e.printStackTrace();}

				}
			}else{
				System.out.println("Authorization failure");
			}
			
	}
	public void SelectFrom(String tableName){
		String query = "SELECT roleName FROM USERROLES where username = '"+current_user+"'";
		String role="";
		String priv="";
		String table="";
		String temp = "";
		String[] role_array = new String[20];//all user roles
		String[] attributes = new String[20];
		int location =0;
		int col=0;
		boolean ok =false;
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while(rs.next()){
					role = rs.getString("roleName");
					role_array[location] = role;
					location ++;
					String nestedQuery = "SELECT privName,tableName FROM ROLEPRIVILEGES where roleName = '"+role+"'";
					Statement stmtNested = con.createStatement();
					ResultSet rsNested = stmtNested.executeQuery(nestedQuery);

					while(rsNested.next()){
						priv = rsNested.getString("privName");
						table = rsNested.getString("tableName");
						if(priv.equals("SELECT")&&table.equals(tableName)){
							ok = true;
						}
					}if(!ok){
						System.out.println("Authorization failure");
						break;
					}rsNested.close();
					stmtNested.close();
				}
				rs.close();
				stmt.close();

			}catch(SQLException e){e.printStackTrace();}
		if(ok){
			boolean decrypt = false;
			String name[] = new String[50];//has owner of tupple
			String check = "SELECT column_name FROM USER_TAB_COLUMNS WHERE table_name = '"+tableName+"'";
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(check);

				while(rs.next()){
					temp = rs.getString("column_name");
					attributes[col] = temp;
					col++;
				}rs.close();
				stmt.close();
			}catch(SQLException e){e.printStackTrace();}
			for(int i=0;i<col-2;i++){
				System.out.print(attributes[i]+", ");
			}
			System.out.println();
			
			String[][] data = new String[4][50];
			String getIt = "SELECT * FROM "+tableName;
			int column[] = new int[50];
			int space = 0;
			try{
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(getIt);

				while(rs.next()){
					data[0][space] = rs.getString(attributes[0]);
					data[1][space] = rs.getString(attributes[1]);
					data[2][space] = rs.getString(attributes[2]);
					data[3][space] = rs.getString(attributes[3]);
					name[space] = rs.getString("owner");
					column[space] = rs.getInt("encryptedColumn");
					space++;
				}rs.close();
				stmt.close();
			}catch(SQLException e){e.printStackTrace();} 
			int d=space;
			int[] encKey = new int[location];
			int[] num = new int[space];
			for(int a=0;a<location;a++){
				String key = "SELECT encryptionKey FROM ROLES WHERE roleName = '"+role_array[a]+"'";
				try{
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(key);
					while(rs.next()){
						encKey[a] = rs.getInt("encryptionKey");
					}rs.close();
					stmt.close();
				}catch(SQLException e){e.printStackTrace();}
			}
			for(int u=0;u<space;u++){ //go through all tuples
				for(int l=0;l<location;l++){ //all user roles
					if(name[u].equals(role_array[l])&&column[u]>0){
						char[] shift = data[column[u]-1][u].toCharArray();
						for(int c=0;c<shift.length;c++){
							shift[c] -= encKey[l];
						}
						data[column[u]-1][u] = String.valueOf(shift);
						decrypt = true;
						break;
					}
				}
			}
			for(int g=0;g<d;g++){
				for(int b=0;b<col-2;b++){
					System.out.print(data[b][g]+", ");
					
				}
				System.out.println();
			}
		}

	}
	public void WorkHorse(String input){
		String in[] = input.split("\\s+");
		if(input.startsWith("LOGIN")){
			LogIn(in[1],in[2]);
		}else if(input.startsWith("CREATE ROLE")){
			int key = Integer.parseInt(in[3]);
			CreateRole(in[2],key);
		}else if(input.startsWith("CREATE USER")){
			CreateUser(in[2],in[3]);
		}else if(input.startsWith("GRANT ROLE")){
			GrantRole(in[2],in[3]);
		}else if(input.startsWith("GRANT PRIVILEGE")){
			GrantPrivilege(in[2],in[4],in[6]);
		}else if(input.startsWith("REVOKE PRIVILEGE")){
			RevokePrivilege(in[2],in[4],in[6]);
		}else if(input.startsWith("INSERT INTO")){
			int column = Integer.parseInt(in[5]);
			InsertInto(in[2],in[3],column,in[6]);
		}else if(input.startsWith("SELECT * FROM")){
			SelectFrom(in[3]);
		}else if(input.startsWith("QUIT")){
			return;
		}else{
			System.out.println("invalid command");
		}
	}
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		RBAC go = new RBAC();
		String data = "";
		while(!data.equals("QUIT")){
			data = input.nextLine();
			go.WorkHorse(data);
		}
	}
}
