package Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations {

    public static Properties props(){
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            return prop;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
