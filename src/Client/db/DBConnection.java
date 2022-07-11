package Client.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String dbName = "blog";
    private static String url = "jdbc:mysql://localhost/" + dbName;
    private static String user = "root";
    private static String passwd = "";
    private static Connection cn = null;

    private DBConnection() {
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.print("Erreur Driver");
		}
		
        try {
        	
            cn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Récupérer l’instance de la connexion
    public static Connection getConnection() {
        if (cn == null) {
            System.out.println("Open new Connection");
            new DBConnection();
        }
        return cn;
    }

    public static void close() {
        if (cn != null) {
            try {
                cn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
