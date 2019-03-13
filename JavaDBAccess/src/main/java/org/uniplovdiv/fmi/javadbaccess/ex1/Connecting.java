package org.uniplovdiv.fmi.javadbaccess.ex1;

import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;
import oracle.jdbc.*;

public class Connecting
{
  public static void main(String a[])
  {
    System.out.println("Trying to connect...");
    connectJdbc4Oracle();
    connectJdbc4MSSQL();
  }

  public static Connection connectJdbc4Oracle()
  {
    try
    {
      //Class.forName("oracle.jdbc.driver.OracleDriver"); not required since JDBC 4.0
      OracleConnection conn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
      System.out.println("JDBC 4 connection done: " + conn.getMetaData().getDatabaseProductName());
      return conn;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static Connection connectJdbc4MSSQL()
  {
    try
    {
      SQLServerConnection conn = (SQLServerConnection) DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=javadb", "javadbuser", "javadbpass");
      System.out.println("JDBC 4 connection done: " + conn.getMetaData().getDatabaseProductName());
      return conn;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
}