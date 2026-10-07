import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) throws SQLException {
        Account idk = new Account("Amir", "1234");
        System.out.println(SQLfetcher.fetchByNameAndPassword("Amir", "1234"));
        String loginInfo = "NotFound";
        while(loginInfo == "NotFound"){
            try{
                Thread.sleep(5000);
                String s = API.getLogin();
                ArrayList<String> sigma = JSONinterpreter.dataFromString(s);
                System.out.println(sigma.toString());
                String a = sigma.get(0);
                String b = sigma.get(1);
                loginInfo = SQLfetcher.fetchByNameAndPassword(a, b);
                API.postLoginNotFound();
            }catch(InterruptedException e){}
        }
        API.postLoginFound();



    }
}
