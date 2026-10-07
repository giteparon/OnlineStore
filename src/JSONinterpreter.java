import java.util.ArrayList;

public class JSONinterpreter {
  public static ArrayList<String> dataFromString(String s){
    String finalS = "";
    ArrayList<Character> disallowed = new ArrayList<>();
    disallowed.add('{');
    disallowed.add('}');
    disallowed.add(',');
    disallowed.add(':');
    disallowed.add('\"');
    for(int i = 0; i < s.length(); i++){
      if(!disallowed.contains(s.charAt(i))){
        finalS = finalS + s.charAt(i);
      }
    }
    finalS = finalS.replace("username","");
    finalS = finalS.replace("password",",");
    String[] split = finalS.split(",");
    ArrayList<String> list = new ArrayList<>();
    for(int i = 0; i < split.length; i++){
      list.add(split[i]);
    }
    return list;
  }
}
