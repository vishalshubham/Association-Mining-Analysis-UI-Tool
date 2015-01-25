package associationmininganalysis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author VISHAL
 */
public class ConnDB
{
	protected Connection connection;
	 protected Statement statement;
	 static ResultSet rs=null;
	
	 public void Connect(){};
    public Connection getConn()
    {
   	 return connection;
    }
	 public void disconnect()
         {
		 try
                 {
			 if (rs!=null)
			    rs.close(); 
			 if (statement!=null)
			    statement.close(); 
		     if (connection!=null)
			    connection.close();
		 }
		 catch(SQLException e){}
	 }
}
class SQLDB extends ConnDB
{
	public void Connect()
        {
	    Connect("","","foodmart");	
	}
	public void Connect(String DBName)
        {
	    Connect("","",DBName);	
	}
	public void Connect(String user,String pw,String DBName)
        {
		String username;
		String password;
		try {
	    	 
	    	 Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
	     }
	     catch (Exception e) {
	         System.out.println(
	         "Unable to register the JDBC Driver.\n" +
	         "Make sure the JDBC driver is in the\n" +
	         "classpath.\n");
	         System.exit(1);
	     }

	     String url="jdbc:microsoft:sqlserver://localhost:1433;DatabaseName="+DBName; 
	     username = "sa";
	     password = "990414";
	     try {
	         connection = DriverManager.getConnection(url, username, password);
	         statement=connection.createStatement();
	         
	   
	     }
	     catch (SQLException e) {
	         System.out.println(
	         "Unable to make a connection to the database.\n" +
	         "The reason: " + e.getMessage());
	         System.exit(1);
	         return;
	     }
	    
	}
}