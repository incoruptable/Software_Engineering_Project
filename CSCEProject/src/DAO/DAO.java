package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Map;

public class DAO {

	private String query;
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	private int paramNumber;
	public DAO() throws SQLException{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://192.168.0.13:1433;DatabaseName=SoftwareEngineeringProjectDB", "EngineeringGroup", "thisissecure");

			paramNumber = 1;
		} catch (ClassNotFoundException e) {
			System.out.println("data access error occured");
			e.printStackTrace();
		}
	}
	
	public ResultSet getresultSet(){
		return rs;
	}
	public void setresultSet(ResultSet rs){
		this.rs = rs;
	}
	
	public String getquery(){
		return query;
	}
	public void setquery(String query) throws SQLException{
		this.query = query;
		stmt = conn.prepareStatement(query);
	}
	
	public void SetParameter(String param) throws SQLException{
		stmt.setString(paramNumber, param);
		paramNumber++;
	}
	
	
	public ResultSet executeQuery() throws SQLException{
		rs = stmt.executeQuery();
		return rs;
	}
}
