package Controller;

import Model.History;
import Model.Logs;
import Model.Product;
import Model.User;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite {

    public int DEBUG_MODE = 0;
    String driverURL = "jdbc:sqlite:" + "database.db";
//    private Logger logger;

    public User toUser(ResultSet rs) throws SQLException {
        return new User(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("role"),
                rs.getInt("locked"));
    }

    public Product toProduct(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("stock"),
                rs.getFloat("price"));
    }

    public Logs toLog(ResultSet rs) throws SQLException {
        return new Logs(rs.getInt("id"),
                rs.getString("event"),
                rs.getString("username"),
                rs.getString("desc"),
                rs.getString("timestamp"));
    }

    public History toHistory(ResultSet rs) throws SQLException {
        return new History(rs.getInt("id"),
                rs.getString("username"),
                rs.getString("name"),
                rs.getInt("stock"),
                rs.getString("timestamp"));
    }

    // creates new DB connection
    public void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(driverURL)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database database.db created.");
            }
        } catch (Exception ex) {
        }
    }

    // creates Tables
    public void createHistoryTable() {
        String sql = "CREATE TABLE IF NOT EXISTS history (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " username TEXT NOT NULL,\n"
                + " name TEXT NOT NULL,\n"
                + " stock INTEGER DEFAULT 0,\n"
                + " timestamp TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db created.");
        } catch (Exception ex) {
        }
    }

    public void createLogsTable() {
        String sql = "CREATE TABLE IF NOT EXISTS logs (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " event TEXT NOT NULL,\n"
                + " username TEXT NOT NULL,\n"
                + " desc TEXT NOT NULL,\n"
                + " timestamp TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db created.");
        } catch (Exception ex) {
        }
    }

    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " name TEXT NOT NULL UNIQUE,\n"
                + " stock INTEGER DEFAULT 0,\n"
                + " price REAL DEFAULT 0.00\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db created.");
        } catch (Exception ex) {
        }
    }

    public void createUserTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " username TEXT NOT NULL UNIQUE,\n"
                + " password TEXT NOT NULL,\n"
                + " role INTEGER DEFAULT 2,\n"
                + " locked INTEGER DEFAULT 0\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db created.");
        } catch (Exception ex) {
        }
    }

    // drops Tables
    public void dropHistoryTable() {
        String sql = "DROP TABLE IF EXISTS history;";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table history in database.db dropped.");
        } catch (Exception ex) {
        }
    }

    public void dropLogsTable() {
        String sql = "DROP TABLE IF EXISTS logs;";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table logs in database.db dropped.");
        } catch (Exception ex) {
        }
    }

    public void dropProductTable() {
        String sql = "DROP TABLE IF EXISTS product;";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table product in database.db dropped.");
        } catch (Exception ex) {
        }
    }

    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users;";

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table users in database.db dropped.");
        } catch (Exception ex) {
        }
    }

    // add entry to Tables
    public void addHistory(String username, String name, int stock, String timestamp) {
//        String sql = "INSERT INTO history(username,name,stock,timestamp) VALUES('" + username + "','" + name + "','" + stock + "','" + timestamp + "')";
        String sql = "INSERT INTO history(username,name,stock,timestamp) VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, name);
            pstmt.setInt(3, stock);
            pstmt.setString(4, timestamp);

            pstmt.executeUpdate();
        } catch (Exception ex) {
        }
    }

    public void addLogs(String event, String username, String desc, String timestamp) {
//        String sql = "INSERT INTO logs(event,username,desc,timestamp) VALUES('" + event + "','" + username + "','" + desc + "','" + timestamp + "')";
        String sql = "INSERT INTO logs(event,username,desc,timestamp) VALUES(?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, event);
            pstmt.setString(2, username);
            pstmt.setString(3, desc);
            pstmt.setString(4, timestamp);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addProduct(String name, int stock, double price) {
//        String sql = "INSERT INTO product(name,stock,price) VALUES('" + name + "','" + stock + "','" + price + "')";
        String sql = "INSERT INTO product(name,stock,price) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, name);
            pstmt.setInt(2, stock);
            pstmt.setDouble(3, price);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(String username, String password) {
//        String sql = "INSERT INTO users(username,password) VALUES('" + username + "','" + password + "')";
        String sql = "INSERT INTO users(username,password) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

//      PREPARED STATEMENT EXAMPLE
            pstmt.setString(1, username);
            pstmt.setString(2, hashFunction(password));
            pstmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(String username, String password, int role) {
//        String sql = "INSERT INTO users(username,password,role) VALUES('" + username + "','" + password + "','" + role + "')";
        String sql = "INSERT INTO users(username,password,role) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, username);
            pstmt.setString(2, hashFunction(password));
            pstmt.setInt(3, role);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<History> getHistory() {
        String sql = "SELECT id, username, name, stock, timestamp FROM history";
        ArrayList<History> histories = new ArrayList<History>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                histories.add(new History(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return histories;
    }

    public ArrayList<History> getHistoryByUsername(String username) {
        String sql = "SELECT id, username, name, stock, timestamp FROM history where username = ?";
        ArrayList<History> histories = new ArrayList<History>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                histories.add(toHistory(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return histories;
    }

    public ArrayList<Logs> getLogs() {
        String sql = "SELECT id, event, username, desc, timestamp FROM logs";
        ArrayList<Logs> logs = new ArrayList<Logs>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                logs.add(new Logs(rs.getInt("id"),
                        rs.getString("event"),
                        rs.getString("username"),
                        rs.getString("desc"),
                        rs.getString("timestamp")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return logs;
    }

    public ArrayList<Product> getProduct() {
        String sql = "SELECT id, name, stock, price FROM product";
        ArrayList<Product> products = new ArrayList<Product>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("stock"),
                        rs.getFloat("price")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return products;
    }

    public ArrayList<User> getUsers() {
        String sql = "SELECT id, username, password, role, locked FROM users";
        ArrayList<User> users = new ArrayList<User>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(toUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> getUsersByUsername(String username) {
        username = username.toLowerCase();
        String sql = "SELECT id, username, password, role, locked FROM users WHERE username = ?";
        ArrayList<User> users = new ArrayList<User>();

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement();
                //                ResultSet rs = stmt.executeQuery(sql)
                PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                users.add(toUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public ArrayList<User> getUsersByUsernameAndPassword(String username, String password) {
        //Please hash password before query code is executed
        String hashPassword = hashFunction(password);
        username = username.toLowerCase();
        ArrayList<User> users = new ArrayList<>();
//        String sql = "SELECT id, username, password, role FROM users where username ='" + username + "' and password ='" + hashPassword + "'";
        String sql = "SELECT id, username, password, role, locked FROM users where username = ? and password = ?";

        System.out.println("CREDS");
        System.out.println(username);
        System.out.println(hashPassword);
        try (Connection conn = DriverManager.getConnection(driverURL); //                Statement stmt = conn.createStatement();
                //                ResultSet rs = stmt.executeQuery(sql)
                ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword);
            ResultSet rs = pstmt.executeQuery();

//            ResultSet rs = stmt.executeQuery("SELECT id, username, password, role FROM users where username ='" + username + "' and password ='" + hashPassword + "'");
            while (rs.next()) {
                users.add(toUser(rs));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public void removeUser(String username) {
//        String sql = "DELETE FROM users WHERE username='" + username + "';";
        String sql = "DELETE FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, username);
            pstmt.executeUpdate();
            System.out.println("User " + username + " has been deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void removeLogs(Logs log) {
//        String sql = "DELETE FROM users WHERE username='" + username + "';";
        String sql = "DELETE FROM logs WHERE event = ? and username = ? and desc = ? and timestamp = ?";

        try (Connection conn = DriverManager.getConnection(driverURL);
                //                Statement stmt = conn.createStatement()
                PreparedStatement pstmt = conn.prepareStatement(sql);) {
//            stmt.execute(sql);

            pstmt.setString(1, log.getEvent());
            pstmt.setString(2, log.getUsername());
            pstmt.setString(3, log.getDesc());
            pstmt.setString(4, log.getTimestamp()+"");
            pstmt.executeUpdate();
            System.out.println("Event Log " + log.getEvent() + " has been deleted.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Product getProduct(String name) {
        String sql = "SELECT name, stock, price FROM product WHERE name='" + name + "';";
        Product product = null;
        try (Connection conn = DriverManager.getConnection(driverURL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            product = new Product(rs.getString("name"),
                    rs.getInt("stock"),
                    rs.getFloat("price"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    private String hashFunction(String password) {
        //link to how to do SHA-512 encryption (https://www.geeksforgeeks.org/sha-512-hash-in-java/)
        try {
            // getInstance() method is called with algorithm SHA-512 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            byte[] messageDigest = md.digest(password.getBytes());
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value 
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            password = hashtext;
            return password;
        } // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
