package Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    private static Properties prop;

    /**
     * Similar code to example given in the work (page 26), but changed to fit the Singleton demand.
     */
    public static Properties getProp() {
        if (prop == null){
            try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {
                prop = new Properties();
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }
}

//TODO: Make usage of the configurations.