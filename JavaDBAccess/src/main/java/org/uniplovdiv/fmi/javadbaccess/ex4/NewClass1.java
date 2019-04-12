/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uniplovdiv.fmi.javadbaccess.ex4;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.*;
import oracle.jdbc.pool.*;
import oracle.ucp.jdbc.*;

/**
 *
 * @author gcholakov
 */
public class NewClass1
{
  public static void main(String[] args)
  {
    lookupDataSource();
  }

  static void lookupDataSource()
  {
    Context ctx = null;
    try
    {
      Properties prop = new Properties();
      prop.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
      prop.setProperty(Context.PROVIDER_URL, "file:C:/Temp");
      ctx = new InitialContext(prop);

      PoolDataSource  ds = (PoolDataSource) ctx.lookup("HumanResources");
      System.out.println("Borrowed: " + ds.getStatistics());
      
      Connection conn = ds.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rset = stmt.executeQuery("select last_name from employees");

      while (rset.next())
      {
        System.out.println(rset.getString(1));
      }

      rset.close();
      stmt.close();
      conn.close();
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
