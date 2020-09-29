import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello World!");
        Object jsonData = new JSONParser().parse(new FileReader("src/main/resources/configuration.json"));

        JSONObject configuration = (JSONObject) jsonData;
        String environment = (String) configuration.get("ENV");
        JSONObject dbDetails = (JSONObject) configuration.get(environment);

        System.out.println("Our environment is : " + environment);
        System.out.println("Environment details are : " + dbDetails);

    }

}
