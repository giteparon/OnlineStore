import java.sql.SQLException;

public class Main {
    public static void main(String args[]) throws SQLException {
        Account idk = new Account("Amir", "1234");
        System.out.println(SQLfetcher.fetchByNameAndPassword("Amir", "1234"));
        String loginInfo = "{\"username\":\"\",\"password\":\"\"}";
        while(loginInfo == "{\"username\":\"\",\"password\":\"\"}"){
            try{
                Thread.sleep(5000);
                System.out.println(API.getLogin());
            }catch(InterruptedException e){}
        }


    }
}
