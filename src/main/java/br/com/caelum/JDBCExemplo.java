package br.com.caelum;

import java.sql.Connection;
import java.sql.SQLException;


public class JDBCExemplo {
	public static void main(String[] args) throws SQLException {
		Connection conn = new ConnectionFactory().getConnection();
		System.out.println("Conectado!");
		conn.close();
	} 

}
