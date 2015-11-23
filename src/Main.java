/**
 * Created by Olcha on 23.11.2015.
 */
public class Main {
    public static void main(String[] args){
        DatabaseHelper databaseHelper = new DatabaseHelper();
        System.out.println(databaseHelper.checkUser("aaaa","qwerty"));
        //System.out.println(databaseHelper.insertUser(1,"aaaa","qwerty","aira","fdsfsd","w","1","1"));
    }
}
