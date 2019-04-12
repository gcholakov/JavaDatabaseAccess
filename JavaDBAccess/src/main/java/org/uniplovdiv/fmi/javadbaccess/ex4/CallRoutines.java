package org.uniplovdiv.fmi.javadbaccess.ex4;

import java.sql.*;
import oracle.jdbc.*;
import org.uniplovdiv.fmi.javadbaccess.ex1.*;

public class CallRoutines
{
  public static void main(String[] args)
  {
    //callFunction();
    //callProcedure();
    //callFunctionCursor();
  }

  public static void callProcedure()
  {
    try (Connection conn = Connecting.connectJdbc4Oracle();
            CallableStatement cstmt = conn.prepareCall("{call find_country(?,?)}"))
    {
      cstmt.setString("ip_id", "RO");
      cstmt.registerOutParameter("op_name", Types.VARCHAR);
      cstmt.execute();

      String country = cstmt.getString("op_name");

      System.out.println("Country: " + country);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  public static void callFunction()
  {
    try (Connection conn = Connecting.connectJdbc4Oracle();
            CallableStatement cstmt = conn.prepareCall("{? = call get_employee(?)}"))
    {
      cstmt.registerOutParameter(1, Types.VARCHAR);
      cstmt.setInt(2, 200);
      cstmt.execute();

      String employeeName = cstmt.getString(1);

      System.out.println("Employee: " + employeeName);
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }

  public static void callFunctionCursor()
  {
    try (Connection conn = Connecting.connectJdbc4Oracle();
            CallableStatement cstmt = conn.prepareCall("{? = call get_departments()}"))
    {
      cstmt.registerOutParameter(1, OracleTypes.CURSOR);
      cstmt.execute();

      ResultSet rset = ((OracleCallableStatement) cstmt).getCursor(1);
      while (rset.next())
      {
        System.out.println(rset.getString("department_name") + " - " + rset.getString("location_id"));
      }
      rset.close();
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
  }
}
