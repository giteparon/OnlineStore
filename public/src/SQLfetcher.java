import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.sql.DataSource;


public class SQLfetcher implements DataSource{
    public static Connection conn = null;
    public static Object fetchByID(ID id){

    }
    public static void test(ID id){
        MysqlDataSource db = new MysqlDataSource();
        db.setPort(3306);
        db.setUser("u");
        db.setPassword("1234");

        try (Connection conn = db.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "SELECT id FROM accounts WHERE id='" + id.getIDString() + "'";
            )) {
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    System.out.println(rs.getInt("number"));
                }
                else{
                    System.out.println("didnt find shi");
                }
                
                
            }
        }
        finally{
            if(conn != null){
                conn.close();
            }
        }
    }
}