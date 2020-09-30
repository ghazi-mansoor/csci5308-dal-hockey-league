import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HelloWorld {

    public static void main(String[] args)  {
        System.out.println("Hello World!");

        try (InputStream inputStream = HelloWorld.class.getClassLoader().getResourceAsStream("configuration.json")) {
            if (inputStream == null) throw new IllegalArgumentException();

            Object jsonData = new JSONParser().parse(new InputStreamReader(inputStream));
            JSONObject configuration = (JSONObject) jsonData;
            String environment = (String) configuration.get("ENV");
            JSONObject dbDetails = (JSONObject) configuration.get(environment);

            System.out.println("Our environment is : " + environment);
            System.out.println("Environment details are : " + dbDetails);

        } catch (ParseException e) {
            System.out.println("Cound not parse configuration files.");
        } catch (IllegalArgumentException e) {
            System.out.println("Cound not find configuration files.");
        } catch (IOException e) {
            System.out.println("Could not open configuartion files.");
        }

    }

}
