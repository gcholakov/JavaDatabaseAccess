package org.uniplovdiv.fmi;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class BasicDSController
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping(value = "/base")
  public String getBaseDataSource() throws NamingException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/OracleBase");
      Connection conn = ds.getConnection();
      log.debug("DatabaseProductName: " + conn.getMetaData().getDatabaseProductName());
      log.debug("DatabaseProductVersion: " + conn.getMetaData().getDatabaseProductVersion());

      StringBuilder result = new StringBuilder();
      result.append("DatabaseProductName: " + conn.getMetaData().getDatabaseProductName() + "<br/>");
      result.append("DatabaseProductVersion: " + conn.getMetaData().getDatabaseProductVersion() + "<br/>");
      result.append("DriverName: " + conn.getMetaData().getDriverName() + "<br/>");
      result.append("DriverVersion: " + conn.getMetaData().getDriverVersion() + "<br/>");
      return result.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping(value = "/base/select")
  public String getBaseDataSourceConn() throws NamingException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/OracleBase");

      StringBuilder result = new StringBuilder();

      try (Connection conn = ds.getConnection(); 
              Statement st = conn.createStatement(); 
              ResultSet rs = st.executeQuery("select * from employees"))
      {
        while (rs.next())
        {
          result.append(rs.getString("first_name") + "<br/>");
        }
      }

      return result.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }
}
