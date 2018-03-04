/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Kenneth
 */
public class DBwriter {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public void LoadDriver() {

        try {
            // The newInstance() call is a work around for some
            // broken Java implementatio

            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
            System.out.println(ex.getMessage());
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javaprog?"
                    + "user=root&password=");
            // Do something with the Connection

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public boolean valueChecker(String u, String p) {
        String user = u;
        String pass = p;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM user_table WHERE user='" + user + "' AND pass='" + pass + "'");
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return false;
    }

    //Set temperature
    public int setTemp(String r, String d, String t, String v) {
        String room = r;
        String date = d;
        String time = t;
        String value = v;
        try {

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM `" + room + "` WHERE `date` = \"" + date + " " + time + "\"");
            if (rs.next()) {
                System.out.println("SEND Temperature data  " + date + " " + time + ", value " + value + ", FAIL: Value exists");
                return 0;
            }

            stmt.executeUpdate("INSERT INTO `" + room + "` (`id`, `date`, `value`) VALUES (NULL, '" + date + " " + time + "', '" + value + "')");
            //    INSERT INTO `temperature` (`id`, `date`, `value`) VALUES (NULL, CURRENT_TIMESTAMP, '20.1');
            System.out.println("SEND Temperature data  " + date + " " + time + ", value " + value + ", OK");
            return 1;

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
        return 0;
    }
}
