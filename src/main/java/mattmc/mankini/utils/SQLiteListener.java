package mattmc.mankini.utils;

import mattmc.mankini.MankiniBot;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project MrBot
 * Created by MattsMc on 6/1/14.
 */

public abstract class SQLiteListener extends ListenerAdapter<PircBotX> {
    public Connection c = null;

    public SQLiteListener(){
        setupDB();
    }

    public abstract void setupDB();

    public void openConnection(String db){
        if((boolean) MankiniBot.conf.get("useSQLite")){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" +db);
        }catch (Exception e){
            e.printStackTrace();
        }
        }else{
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String sql = "jdbc:mysql://"+MankiniBot.conf.get("MySQLHost") + "/" + MankiniBot.conf.get("MySQLTable")+ "?user="+MankiniBot.conf.get("MySQLUserName")+"&password="+MankiniBot.conf.get("MySQLPassword");
                c = DriverManager.getConnection(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void closeConnection(){
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

}
