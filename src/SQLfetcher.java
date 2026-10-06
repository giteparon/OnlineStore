import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;


public class SQLfetcher{
    private static Connection conn = null;
    private static String url = "jdbc:sqlite:accounts.db";
    private static String urlOrders = "jdbc:sqlite:orders.db";
    public static String fetchByNameAndPassword(String name, String password) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id FROM accounts WHERE name='" + name + "'AND password = '" + password + "'");
            if (rs.next()) {
                return rs.getString("id");
            }
            else{
                return "NotFound";
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            if(conn != null){
                conn.close();
            }
        }
    }
    public static int fetchByID(ID id) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url)) {
                Statement stmt = conn.createStatement();

                ResultSet rs = stmt.executeQuery("SELECT * FROM accounts WHERE id = '" + id.getIDString() + "'");
                if(rs != null){
                    return 0;
                }
                else{
                    return 1;
                }

        } catch (SQLException e) {
          throw new RuntimeException(e);
        } finally{
            if(conn != null){
                conn.close();
            }
        }
    }
    public static int registerForDB(ID id, String name, String password) throws SQLException {

        try (Connection conn = DriverManager.getConnection(url)) { //initiallizign connectuins
            Statement stmt = conn.createStatement();
                stmt.executeUpdate("CREATE TABLE IF NOT EXISTS accounts (" +
                    "id TEXT NOT NULL," +
                    "name TEXT ," +
                    "password TEXT)");
                System.out.println("db and table initialized");
                if(fetchByID(id) == 0){
                    stmt.executeUpdate("INSERT INTO accounts (id, name, password) VALUES ('" + id.getIDString() + "','" + name + "','" + password + "')");
                    System.out.println("Inserted id");
                    return 1;
                }
                else{
                    System.out.println("Account already exists");
                    return 0;
                }


        } catch (SQLException e) {
          throw new RuntimeException(e);
        } finally{
            if(conn != null){
                conn.close();
            }
        }
    }
    public static ArrayList<ArrayList<String>> fetchOrders(ID id) throws SQLException {
        try(Connection conn = DriverManager.getConnection(urlOrders)){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS orders (id TEXT NOT NULL, item TEXT NOT NULL, price INTEGER NOT NULL, desc TEXT, seller TEXT)");
            System.out.println("db and table initialized");
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE id = '" + id.getIDString() + "'");
            ArrayList<ArrayList<String>> orders = new ArrayList<>();
            ArrayList<String> info = new ArrayList<>();
            String item = "";
            String price = "";
            String desc = "";
            String seller = "";
            while(rs.next()){
                item = rs.getString("item");
                price = "" + rs.getInt("price");
                desc = rs.getString("desc");
                seller = rs.getString("seller");
                info.add(item);
                info.add(price);
                info.add(desc);
                info.add(seller);
                orders.add(info);
            }
            return orders;
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
    }
    public static void addOrder(ID id, String item, int price, String desc, String seller) throws SQLException {
        try(Connection conn = DriverManager.getConnection(urlOrders)){
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO orders (id, name, price, desc, seller) VALUES ('"
                + id + "','" + item + "','" + price + "','" + desc + "','" + seller + "')");
            System.out.println("inserted order");

        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
    }
}