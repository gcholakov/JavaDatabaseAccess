package org.uniplovdiv.fmi.javadbaccess.ex4;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import org.uniplovdiv.fmi.javadbaccess.ex1.Connecting;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class CallRoutines {
    public static void main(String[] args) {

        callFunction();
        //callProcedure();
        //callFunctionCursor();
    }

    public static void callFunction() {

        try (Connection conn = Connecting.connectJdbc4Oracle();
             CallableStatement cstmt = conn.prepareCall("{? = call get_employee(?)}")) {
            cstmt.registerOutParameter(1, Types.VARCHAR);
            cstmt.setInt(2, 101);
            cstmt.execute();

            String employeeName = cstmt.getString(1);

            System.out.println("Employee: " + employeeName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callProcedure() {

        try (Connection conn = Connecting.connectJdbc4Oracle();
             CallableStatement cstmt = conn.prepareCall("{call find_country(?,?)}")) {
            cstmt.setString("ip_country_id", "BG");
            cstmt.registerOutParameter("op_country_name", Types.VARCHAR);
            cstmt.execute();

            String country = cstmt.getString("op_country_name");

            System.out.println("Country: " + country);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void callFunctionCursor() {

        try (Connection conn = Connecting.connectJdbc4Oracle();
             CallableStatement cstmt = conn.prepareCall("{? = call get_depts()}")) {
            cstmt.registerOutParameter(1, OracleTypes.CURSOR);
            cstmt.execute();

            ResultSet rset = ((OracleCallableStatement) cstmt).getCursor(1);
            while (rset.next()) {
                System.out.println(rset.getString("name") + " - " + rset.getString("city"));
            }
            rset.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
