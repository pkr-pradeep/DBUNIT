package org.dbunit.pkr;
import java.sql.*;

public class FirstExample {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";  
	static final String DB_URL = "jdbc:oracle:thin:admin/amin123@localhost:1521:xe";
	static final String USER = "admin";
	static final String PASS = "admin123";
	static Statement stmt;

	public static void main(String[] args) {
		Connection conn = null;

		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			doGetData(conn);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!");
	}

	public static void doGetData(Connection conn) {
		try {
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT emp_id, emp_name, emp_dept FROM PKR.T_PKR_EMPLOYEE";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
				String id  = rs.getString("emp_id");
				String name = rs.getString("emp_name");
				String dept = rs.getString("emp_dept");

				System.out.print("ID: " + id);
				System.out.print(", Name: " + name);
				System.out.print(", Dept: " + dept);
				System.out.println();
			}
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}