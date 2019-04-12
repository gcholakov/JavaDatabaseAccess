package org.uniplovdiv.fmi.javadbweb.controllers;

import javax.naming.*;
import javax.sql.*;
import org.springframework.jdbc.datasource.lookup.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController
{
  @RequestMapping("/test")
  public String home()
  {
    System.out.println("test");
    return "Spring boot is working!";
  }

  @RequestMapping("/test2")
  public String test2() throws NamingException
  {
    System.out.println("test2");
    String dsName = "jdbc/ebanktecutest";
    try
    {
      InitialContext ctx = new InitialContext();
      Context initCtx = (Context) ctx.lookup("java:/comp/env");
      System.out.println("initCtx = " + initCtx);

      DataSource ds = (DataSource) initCtx.lookup(dsName);
      return "ds = " + ds;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return "Error = " + e.getMessage();
    }
  }

  @RequestMapping("/test3")
  public String test3() throws NamingException
  {
    String dsName = "jdbc/ebanktecutest";
    try
    {
      JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
 DataSource ds = dataSourceLookup.getDataSource("java:/comp/env/jdbc/ebanktecutest");

      return "ds = " + ds;
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return "Error = " + e.getMessage();
    }
  }
}
