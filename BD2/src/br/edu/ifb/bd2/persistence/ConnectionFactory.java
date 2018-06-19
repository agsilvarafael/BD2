package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection(){
		String driver = "com.mysql.jdbc.Driver";
		String user = "admin";
		String senha = "admin";
		String url = "jdbc:mysql://localhost/livraria";
		Connection con = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, senha);
		}catch (ClassNotFoundException e){
			System.err.println(e.getMessage());
		}catch (SQLException e){
			System.err.println(e.getMessage());
		}catch (Exception e){
			System.err.println(e.getMessage());
		}
		
		return con;
	}
	
	public static void close (Connection con){
		try{
			con.close();
		}
		catch(SQLException e){
			System.err.println(e.getMessage());
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
}
