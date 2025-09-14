package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private final String serverName = "localhost";
	private final String dbName = "jpact5st7";
	private final String portNumber = "1433";
	private final String instance = ""; // để trống nếu không có instance
	private final String userID = "sa";
	private final String password = "123456";

	public Connection getConnection() throws Exception{
		Connection conn = null;

		try {
			String url;
			if (instance == null || instance.trim().isEmpty()) {
			    // không có instance
			    url = "jdbc:sqlserver://" + serverName + ":" + portNumber 
			        + ";databaseName=" + dbName 
			        + ";encrypt=false;trustServerCertificate=true";
			} else {
			    // có instance
			    url = "jdbc:sqlserver://" + serverName + ":" + portNumber 
			        + "\\" + instance + ";databaseName=" + dbName 
			        + ";encrypt=false;trustServerCertificate=true";
			}
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");


			conn = DriverManager.getConnection(url, userID, password);

			if (conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
				return conn;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		try {
			Connection conn = new DBConnection().getConnection();
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
