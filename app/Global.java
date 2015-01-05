import play.*;
import play.libs.*;
import java.util.*;
import com.avaje.ebean.*;
import models.*;

public class Global extends GlobalSettings {

    public void onStart(Application app) {
        InitialData.insert(app);
    }

    static class InitialData {

        public static void insert(Application app) {

            Map<String,List<Object>> all =
                    (Map<String,List<Object>>)Yaml.load("initial-data.yml");

            if(Ebean.find(User.class).findRowCount() == 0) {
                Ebean.save(all.get("users"));
            }


        }
    }
}
