// Skeleton version of StockData.java that links to a database.
// NOTE: You should not have to make any changes to the other
// Java GUI classes for this to work, if you complete it correctly.
// Indeed these classes shouldn't even need to be recompiled

import java.sql.*; // DB handling package
import javax.swing.JOptionPane;

public class StockData {

    private static Connection conn;
    private static Statement stmt;

    public StockData() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/StockControl", "sm851", "2gianna2");
            stmt = conn.createStatement();

        } // The following exceptions must be caught
        catch (Exception ex) {
            System.out.println(ex);

        }
    }
    // You could make methods getName, getPrice and getQuantity simpler by using an auxiliary
    // private String method getField(String key, int fieldNo) to return the appropriate field as a String


    public static String getName(String key) {
        try {
            // Need single quote marks ' around the key field in SQL. This is easy to get wrong!
            // For instance if key was "1111111" the SELECT statement would be:
            // SELECT * FROM Stock WHERE key = '1111111'
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockID = '" + key + "'");
            if (res.next()) { // there is a result
                // the name field is the second one in the ResultSet
                // Note that with  ResultSet we count the fields starting from 1
                return res.getString("name");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String getDescription(String key) {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockID = '" + key + "'");
            if (res.next()) {
                return res.getString("description");
                } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static double getPrice(String key) {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockID = '" + key + "'");
            if (res.next()) {
                double a = Double.parseDouble(res.getString("price"));
                return a;
            } else {
                return 0.0;
            }
        } catch (Exception e) {
            System.out.println(e);
            return 0.0;
        }
    }

    public static int getQuantity(String key) {
        try {
            ResultSet res = stmt.executeQuery("SELECT * FROM Stock WHERE stockID = '" + key + "'");
            if (res.next()) {
                int a = Integer.parseInt(res.getString("quantity"));
                return a;
            } else {
                return -1;
            }
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }

    // update stock levels
    // extra is +ve if adding stock
    // extra is -ve if selling stock
    public static void update(String key, int extra) {
        // SQL UPDATE statement required. For instance if extra is 5 and key is "1111111" then updateStr is
        // UPDATE Stock SET stockQuantity = stockQuantity + 5 WHERE key = '1111111'
        String updateStr = "UPDATE Stock SET Quantity = Quantity + " + extra + " WHERE stockID = '" + key + "'";
        System.out.println(updateStr);
        try {
            stmt.executeUpdate(updateStr);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateDescription(String key, String name, String description, String price) {
        // SQL UPDATE statement required. For instance if extra is 5 and key is "1111111" then updateStr is
        // UPDATE Stock SET stockQuantity = stockQuantity + 5 WHERE key = '1111111'
        String updateName = "UPDATE Stock SET Name = '" + name + "' WHERE stockID = '" + key + "'";
        String updateDescription = "UPDATE Stock SET Description = '" + description + "' WHERE stockID = '" + key + "'";
        String updatePrice = "UPDATE Stock SET Price = '" + price + "' WHERE stockID = '" + key + "'";
        System.out.println(updateName);
        System.out.println(updateDescription);
        System.out.println(updatePrice);
        try {
            stmt.executeUpdate(updateName);
            stmt.executeUpdate(updateDescription);
            stmt.executeUpdate(updatePrice);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void addNewItem(String key, String name, String description, String price, String quantity) {
        // SQL UPDATE statement required. For instance if extra is 5 and key is "1111111" then updateStr is
        // UPDATE Stock SET stockQuantity = stockQuantity + 5 WHERE key = '1111111'
//        INSERT INTO Room (Location , Capacity, RoomType) VALUES ('QA328', 40,'Lecture' );
//        INSERT INTO Staff VALUES ('Chris','Walshaw','Lecturer','QM354',4857,'NO' );

        String addNewItem = "INSERT INTO Stock VALUES ('" + key + "' , '" + name + "' , '" + description + "' ,'" + price + "' , '" + quantity + "')";
        System.out.println(addNewItem);
        try {
            stmt.executeUpdate(addNewItem);
        } catch (Exception e) {
            System.out.println(" SQL error found \n" + e);
            JOptionPane.showMessageDialog(null, "Please don't use any special characters");
        }

    }

    public static void deleteItem(String key) {
        // SQL UPDATE statement required. For instance if extra is 5 and key is "1111111" then updateStr is
        // UPDATE Stock SET stockQuantity = stockQuantity + 5 WHERE key = '1111111'
//        INSERT INTO Room (Location , Capacity, RoomType) VALUES ('QA328', 40,'Lecture' );
//        INSERT INTO Staff VALUES ('Chris','Walshaw','Lecturer','QM354',4857,'NO' );

        String addNewItem = "DELETE FROM Stock WHERE stockID = ('" + key + "' )";
        System.out.println(addNewItem);
        try {
            stmt.executeUpdate(addNewItem);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // close the database
    public static void close() {
        try {
            conn.close();
        } catch (Exception e) {
            // this shouldn't happen
            System.out.println(e);
        }
    }

    public static int getNewKey() {
        int keyCounter = 1;
        for (int i = 0; i < 100; ++i){
            if ( getName(i+"") == null){
                keyCounter = i;
                break;
            } else {
                keyCounter++;
            }
        }
        return keyCounter;
    }
}