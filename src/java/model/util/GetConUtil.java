package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConUtil {

    private GetConUtil() {
    }
    public static Connection con;


    public static Connection getCon() throws ClassNotFoundException, SQLException {
        String url = System.getProperty("appconsole.db.url", "jdbc:oracle:thin:@db-host:1521/service-name");
        String username = System.getProperty("appconsole.db.username", "app_user");
        String password = System.getProperty("appconsole.db.password", "change-me");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(url, username, password);
        return con;
    }
}
