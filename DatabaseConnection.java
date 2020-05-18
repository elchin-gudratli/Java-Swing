package com.bilgeadam.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection connection;
	private String url="jdbc:mysql://46.28.239.130/opendart_?useUnicode=yes&characterEncoding=UTF-8";
	private String username="opendart";
	private String password="Hercai123";
	
	private DatabaseConnection(){
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			this.connection=(Connection) DriverManager.getConnection(url,username,password);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Database Connection Creation Failed:"+ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Database Connection Creation Failed:"+ex.getMessage());
		}
	}

	public Connection getConnection()
	{
		return connection;
	}
	public static DatabaseConnection getInstance() throws SQLException
	{
		if(instance==null) {
			instance=new DatabaseConnection();
		}
		else if (instance.getConnection().isClosed()) {
			instance=new DatabaseConnection();
		}
		return instance;
	}
}
