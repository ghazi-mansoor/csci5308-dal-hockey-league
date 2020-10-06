import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HelloWorld {

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println("Hello World");

        String path = "configuration.json";

        JSONParser parser = new JSONParser();
        JSONObject jsonData = (JSONObject) parser.parse(new FileReader(path));

        System.out.println(jsonData);
        System.out.println((String) jsonData.get("DBNAME"));
    }
}
