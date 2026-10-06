public class ID{
    private String id;
    private static int counter = 1000;
    public ID(){
        this.id = genID();
    }
    private static String genID(){
        counter++;
        return "account-id-" + counter;
    }
    public String getIDString(){
        return this.id;
    }
}