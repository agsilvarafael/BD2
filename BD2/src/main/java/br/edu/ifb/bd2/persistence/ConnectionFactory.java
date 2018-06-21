package br.edu.ifb.bd2.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static Connection getConnection(){
		String driver = "com.mysql.cj.jdbc.Driver";
		String user = "root";
		String senha = "admin";
		String url = "jdbc:mysql://localhost:3306/livraria?useTimezone=true&serverTimezone=UTC&useSSL=FALSE";
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
