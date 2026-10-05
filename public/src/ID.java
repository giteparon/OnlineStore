public class ID{
    private String id;
    private static int counter = 0;
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