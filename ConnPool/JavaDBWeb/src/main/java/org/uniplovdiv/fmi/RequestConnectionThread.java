package org.uniplovdiv.fmi;

import java.sql.*;
import javax.naming.*;
import oracle.ucp.jdbc.*;
import org.slf4j.*;

public class RequestConnectionThread extends Thread
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public void run()
  {
    try
    {
      InitialContext ctx = new InitialContext();
      PoolDataSourceImpl pds = (PoolDataSourceImpl) ctx.lookup("java:/comp/env/jdbc/OraclePool");

      try (Connection conn = pds.getConnection();
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery("select * from employees"))
      {
        while (rs.next())
        {
          log.debug(rs.getString("first_name"));
        }
      }
    }
    catch (Exception ex)
    {
      log.error("Error!", ex);
    }

  }

}
