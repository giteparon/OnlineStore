
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account {
  private String username;
  private String password;
  private ID id;
  public Account(String username, String password) throws SQLException {
    this.username = username;
    this.password = password;
    this.id = new ID();
    System.out.println(this.id.getIDString());
    SQLfetcher.registerForDB(this.id, username, password);
  }
  public void createOrder(String item , int price , String desc, String seller) throws SQLException {
    SQLfetcher.addOrder(this.id, item, price, desc, seller);
  }
  public void listOrders() throws SQLException {
    ArrayList<ArrayList<String>> orders = SQLfetcher.fetchOrders(this.id);
    for(int i = 0; i < orders.size(); i++) {
      System.out.println("Order " + i + " : " + orders.get(i));
    }
  }

}
