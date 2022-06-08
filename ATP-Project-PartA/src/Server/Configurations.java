package Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    private static Properties prop;

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
