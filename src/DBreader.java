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
public class DBreader {

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

    public boolean login(String u, String p) {
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

// Average Temperature
    public String getTempAvg(String s, String e, String r) {
        String room = r;
        String start = s;
        String end = e;
        String value = "";

        try {

            stmt = conn.createStatement();
            //rs = stmt.executeQuery("SELECT HeadOfState FROM Country WHERE Name LIKE 'Iraq'");
            rs = stmt.executeQuery("SELECT AVG(`value`) FROM `" + room + "` WHERE `date` BETWEEN '" + start + " 00:00:00' AND '" + end + " 23:59:59'");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            /* if (stmt.execute("SELECT foo FROM bar")) {
                rs = stmt.getResultSet();
            }
             */
            // Now do something with the ResultSet ....
            rs.next();

            // Check if there's data to be fetched and assign data to value string
            if (rs.getBoolean(1)) {
                value = ("The average temperature between " + start + " and " + end + " was " + rs.getString(1) + "°C");
                System.out.println("FETCH Average temperature from " + room + " OK: " + rs.getString(1));
            }
            if (!rs.getBoolean(1)) {
                value = ("Couldn't find any data in Database for this time period");
                System.out.println("FETCH Average temperature from " + room + " FAIL: no data");
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
        return value;

    }

// Maximum Temperature
    public String getTempMax(String s, String e, String r) {
        String room = r;
        String start = s;
        String end = e;
        String value = "";
        String valueCheck = "";
        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(`value`) FROM `" + room + "` WHERE `date` BETWEEN '" + start + " 00:00:00' AND '" + end + " 23:59:59'");
            rs.next();

            // Check if there's data to be fetched and assign data to value string
            if (rs.getBoolean(1)) {
                value = ("The maximum temperature between " + start + " and " + end + " was " + rs.getString(1) + "°C");
                System.out.println("FETCH Maximum temperature from " + room + " OK: " + rs.getString(1));
            }
            if (!rs.getBoolean(1)) {
                value = ("Couldn't find any data in Database for this time period");
                System.out.println("FETCH Average temperature from " + room + " FAIL: no data");
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
        return value;
    }
    // Minimum Temperature

    public String getTempMin(String s, String e, String r) {
        String room = r;
        String start = s;
        String end = e;
        String value = "";

        try {

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MIN(`value`) FROM `" + room + "` WHERE `date` BETWEEN '" + start + " 00:00:00' AND '" + end + " 23:59:59'");
            rs.next();

            // Check if there's data to be fetched and assign data to value string
            if (rs.getBoolean(1)) {
                value = ("The minimum temperature between " + start + " and " + end + " was " + rs.getString(1) + "°C");
                System.out.println("FETCH Minimum temperature from " + room + " OK: " + rs.getString(1));
            }
            if (!rs.getBoolean(1)) {
                value = ("Couldn't find any data in Database for this time period");
                System.out.println("FETCH Average temperature from " + room + " FAIL: no data");
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
        return value;
    }

}
