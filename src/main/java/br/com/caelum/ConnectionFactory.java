package br.com.caelum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			return DriverManager.getConnection("jdbc:postgresql://localhost/fj21","administrador","wcf220481");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}

}
