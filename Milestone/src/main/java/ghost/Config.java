package ghost;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class Config {

  private String mapFile;
  private int lives;
  private int speed;
  private ArrayList<Integer> modeLengths;

  public Config(String configFilename) {
    ParseConfig(configFilename);
  }

  public String getMap() {
    return this.mapFile;
  }

  public int getLives() {
    return this.lives;
  }

  public int getSpeed() {
    return this.speed;
  }

  public ArrayList<Integer> getMode() {
    return this.modeLengths;
  }

  private void ParseConfig(String configFilename) {
    // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();
      
    try {
      FileReader reader = new FileReader(configFilename);

      // Read JSON file
      Object obj = jsonParser.parse(reader);

      // typecasting obj to JSONObject
      JSONObject jo = (JSONObject) obj;

      // parse JSON object
      parseConfigObject(jo);

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
  }

  private void parseConfigObject(JSONObject config) {
    // Get Map
    this.mapFile = (String) config.get("map");
      
    // Get lives
    this.lives = ((Long) config.get("lives")).intValue();  
      
    // Get speed
    this.speed = ((Long) config.get("speed")).intValue();

    // Create arraylist to store modeLengths
    this.modeLengths = new ArrayList<Integer>();
    // Get modeLengths
    JSONArray ja = (JSONArray) config.get("modeLengths");
    for (int i = 0; i < ja.size(); i++) {
      modeLengths.add(((Long) ja.get(i)).intValue());
    }
  }

}