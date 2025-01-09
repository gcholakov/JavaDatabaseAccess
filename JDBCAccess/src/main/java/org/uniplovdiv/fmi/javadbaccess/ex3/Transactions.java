package org.uniplovdiv.fmi.javadbaccess.ex3;

import org.uniplovdiv.fmi.javadbaccess.ex1.Connecting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {
    public static void main(String[] args) {

        transaction();
    }

    public static void transaction() {

        try (Connection conn = Connecting.connectJdbc4Oracle()) {
            try {
                conn.setAutoCommit(false);

                PreparedStatement pstmt = conn.prepareStatement("insert into regions(region_id, region_name) values(?,?)");
                pstmt.setInt(1, 7);
                pstmt.setString(2, "North Europe");
                pstmt.executeUpdate();
                System.out.println("Region inserted.");

                pstmt = conn.prepareStatement("insert into countries(country_id, name, region_id) values(?,?,?)");
                pstmt.setString(1, "SE");
                pstmt.setString(2, "Sweden");
                pstmt.setInt(3, 6);
                pstmt.executeUpdate();
                System.out.println("Country inserted.");

                conn.commit();
                System.out.println("Commit completed.");
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
