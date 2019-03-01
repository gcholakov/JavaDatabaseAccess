package org.uniplovdiv.fmi.javadbaccess;

import com.microsoft.sqlserver.jdbc.*;
import java.sql.*;
import oracle.jdbc.*;

public class NewClass
{
  public static void main(String a[])
  {
    System.out.println("org.uniplovdiv.fmi.javadbaccess.NewClass.main()");
    connectJdbc4MSSQL();
  }

  public static void connectJdbc4Oracle()
  {
    try
    {
      //Class.forName("oracle.jdbc.driver.OracleDriver");
      OracleConnection conn = (OracleConnection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
      System.out.println("JDBC 4 connection done.");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public static void connectJdbc4MSSQL()
  {
    try
    {
      SQLServerConnection conn = (SQLServerConnection) DriverManager.getConnection("jdbc:sqlserver://194.141.98.98:1433", "sa", "Sirma-1234");
      System.out.println("JDBC 4 connection done.");
      System.out.println("1 - " + conn.getClass().getName());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

}
