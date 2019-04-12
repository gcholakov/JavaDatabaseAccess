/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uniplovdiv.fmi.javadbaccess.ex4;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import oracle.jdbc.pool.*;
import oracle.ucp.jdbc.*;

/**
 *
 * @author gcholakov
 */
public class NewClass2
{
  public static void main(String[] args)
  {
    bindPoolDataSource();
  }

  static void bindPoolDataSource()
  {
    Context ctx = null;
    try
    {
      Properties prop = new Properties();
      prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
      prop.setProperty(Context.PROVIDER_URL, "file:C:/Temp");
      ctx = new InitialContext(prop);

      PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
      pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
      pds.setURL("jdbc:oracle:thin:@//localhost:1521/orcl");
      pds.setUser("user1");
      pds.setPassword("pass1");

      Connection conn = pds.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery("select last_name from employees");

      while (rset.next())
      {
        System.out.println(rset.getString(1));
      }
      
      System.out.println("Stats: " + pds.getStatistics());

      //ctx.bind("HumanResources", pds);
      System.out.println("Binding successful.");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }

  static void bindDataSource()
  {
    Context ctx = null;
    try
    {
      Properties prop = new Properties();
      prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
      prop.setProperty(Context.PROVIDER_URL, "file:C:/Temp");
      ctx = new InitialContext(prop);

      OracleDataSource ds = new OracleDataSource();
      ds.setDriverType("thin");
      ds.setServerName("localhost");
      ds.setPortNumber(1521);
      ds.setDatabaseName("orcl");
      ds.setUser("user1");
      ds.setPassword("pass1");

      ctx.bind("HumanResources", ds);
      System.out.println("Binding successful.");
    }
    catch (NamingException ne)
    {
      ne.printStackTrace();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
