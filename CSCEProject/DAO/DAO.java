package DAO;

import java.sql.Connection;
import java.sql.Date;
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
	private boolean expectRS;
	private ResultSet rs;
	private int paramNumber;
	public DAO() throws SQLException{
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://csce3613.database.windows.net:1433;database=SoftwareEngineeringProjectDB;user=EngineeringGroup@csce3613;password=Th1s1sS3cur3;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

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
	public boolean getExpectRS(){
		return expectRS;
	}
	public void setExpectRS(boolean expectRS){
		this.expectRS = expectRS;
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
	public void SetParameter(int param) throws SQLException{
		stmt.setInt(paramNumber, param);
		paramNumber++;
	}
	public void SetParameter(Date param) throws SQLException{
		stmt.setDate(paramNumber, param);
		paramNumber++;
	}
	
	public ResultSet executeQuery() throws SQLException{
		if(expectRS == true) {
			rs = stmt.executeQuery();
		}
		else
			stmt.execute();
		paramNumber = 1;
		return rs;
	}

	public void SetParameter(boolean b) throws SQLException {
		stmt.setBoolean(paramNumber, b);
		paramNumber++;
		
	}
}
