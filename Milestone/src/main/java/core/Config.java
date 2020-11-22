package core;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Config {

  private String mapFile;
  private int lives;
  private int speed;
  private ArrayList<Integer> modeLengths;

  /**
  * Constructs Config from given config file
  *
  * @param  configFilename  the name of config file
  */
  public Config(String configFilename) {
    readConfig(configFilename);
  }

  /**
  * Returns name of map file
  *
  * @return  name of map file
  */
  public String getMapGrid() {
    return this.mapFile;
  }

  /**
  * Returns number of player lives
  *
  * @return  number of player lives
  */
  public int getLives() {
    return this.lives;
  }

  /**
  * Returns speed of Actor movement
  *
  * @return  speed of Actor movement
  */
  public int getSpeed() {
    return this.speed;
  }

  /*
  * Returns list of modes
  *
  * @return  list of modes
  */
  public ArrayList<Integer> getMode() {
    return this.modeLengths;
  }

  /*
  * Reads config file from filename. Prints exceptions if they arise.
  */
  private void readConfig(String configFilename) {
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

  /*
  * Set config properties from config JSON file
  */
  private void parseConfigObject(JSONObject config) {
    // Get MapGrid
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
