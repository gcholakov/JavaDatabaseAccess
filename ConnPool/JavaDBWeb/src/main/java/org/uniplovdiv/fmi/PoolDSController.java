package org.uniplovdiv.fmi;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import oracle.ucp.jdbc.*;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PoolDSController
{
  private Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping("/pool")
  public String pool() throws NamingException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/OraclePool");

      PoolDataSourceImpl pds = (PoolDataSourceImpl) ds;

      StringBuilder result = new StringBuilder();

      if (pds.getStatistics() != null)
      {
        result.append("RemainingPoolCapacityCount: " + pds.getStatistics().getRemainingPoolCapacityCount() + "<br/>");
        result.append("AvailableConnectionsCount: " + pds.getStatistics().getAvailableConnectionsCount() + "<br/>");
        result.append("TotalConnectionsCount: " + pds.getStatistics().getTotalConnectionsCount() + "<br/>");
        result.append("<br/>");

        result.append("BorrowedConnectionsCount: " + pds.getStatistics().getBorrowedConnectionsCount() + "<br/>");
        result.append("PendingRequestsCount: " + pds.getStatistics().getPendingRequestsCount() + "<br/>");
        result.append("<br/>");

        result.append("ConnectionsCreatedCount: " + pds.getStatistics().getConnectionsCreatedCount() + "<br/>");
        result.append("ConnectionsClosedCount: " + pds.getStatistics().getConnectionsClosedCount() + "<br/>");
        result.append("PeakConnectionWaitTime: " + pds.getStatistics().getPeakConnectionWaitTime() + "<br/>");
        result.append("PeakConnectionsCount: " + pds.getStatistics().getPeakConnectionsCount() + "<br/>");
        result.append("<br/>");

      }
      else
      {
        result.append("Pool has no connections yet.");
      }

      return result.toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return "Error = " + e.getMessage();
    }
  }

  @GetMapping("/pool/select")
  public String poolSelect() throws NamingException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      PoolDataSourceImpl pds = (PoolDataSourceImpl) ctx.lookup("java:/comp/env/jdbc/OraclePool");

      StringBuilder result = new StringBuilder();

      try (Connection conn = pds.getConnection();
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
      return "Error = " + e.getMessage();
    }
  }

  @GetMapping("/pool/borrow")
  public String poolBorrow() throws NamingException
  {
    try
    {
      InitialContext ctx = new InitialContext();
      PoolDataSourceImpl pds = (PoolDataSourceImpl) ctx.lookup("java:/comp/env/jdbc/OraclePool");

      StringBuilder result = new StringBuilder();

      Connection conn = pds.getConnection();

      try (Statement st = conn.createStatement();
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
      return "Error = " + e.getMessage();
    }
  }

  @GetMapping("/pool/threads/{count}")
  public String poolSelect(@PathVariable("count") int count)
  {
    for (int i = 0; i < count; i++)
    {
      RequestConnectionThread rct = new RequestConnectionThread();
      rct.start();
    }

    return "Started " + count + " threads.";
  }
}
