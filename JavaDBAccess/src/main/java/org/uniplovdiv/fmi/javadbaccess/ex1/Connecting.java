package org.uniplovdiv.fmi.javadbaccess.ex1;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;
import oracle.jdbc.OracleConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connecting {
    public static void main(String a[]) {

        connectJdbc4Oracle();
        connectJdbc4MSSQL();
    }

    public static Connection connectJdbc4Oracle() {

        System.out.println("Trying to connect to Oracle...");

        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver"); not required since JDBC 4.0
            OracleConnection conn = (OracleConnection) DriverManager
                    .getConnection("jdbc:oracle:thin:@195.230.127.185:1521:orcluni", "user1", "pass1");
            System.out.println("JDBC 4 connection done: " + conn.getMetaData().getDatabaseProductName());
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Connection connectJdbc4MSSQL() {

        System.out.println("Trying to connect to MSSQL...");

        try {
            SQLServerConnection conn = (SQLServerConnection) DriverManager
                    .getConnection("jdbc:sqlserver://195.230.127.185:1433;databaseName=javadb;trustServerCertificate=true", "javadbuser", "javadbpass");
            System.out.println("JDBC 4 connection done: " + conn.getMetaData().getDatabaseProductName());
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
