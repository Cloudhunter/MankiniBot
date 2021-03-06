package mattmc.mankini.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Project Mankini
 * Created by MattsMc on 7/11/14.
 */
public class ModUtils {
    public static ArrayList<String> moderators = new ArrayList<String>();

    public static Thread updateMods = new Thread("ModUtils"){
        @Override
        public void run() {
            while(true){
            try {
                updateModerators();
                updateMods.sleep(300000);
            } catch (Exception e) {
                e.printStackTrace();
        }
    }}};

    public static void updateModerators() throws Exception {
        moderators.clear();
        JSONObject json = new JSONObject(JSONParser.readUrl("http://tmi.twitch.tv/group/user/runew0lf/chatters"));
        for(int i = 0; i < json.length(); i++){
            JSONArray mods = json.getJSONObject("chatters").getJSONArray("moderators");
            for(int j = 0; j < mods.length(); j++){

                moderators.add(mods.getString(j));
            }
        }
    }
}
