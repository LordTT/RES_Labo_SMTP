package config;

import mail.Person;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Class offering methods to get infos from the config files
 */
public class ConfigurationManager {
    private InputStream is;

    /**
     * Stes the inputStream of the class
     *
     * @param file read file
     */
    private void setInputStream(String file) {
        is = this.getClass().getClassLoader().getResourceAsStream(file);
    }

    /**
     * Closes the inputstream of the class
     */
    private void closeInputStream() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a JSON object from a specifed file
     *
     * @param file File from which the json object is read
     * @return Json Object gotten from a file
     * @throws FileNotFoundException
     */
    private JsonObject getJsonObject(String file) throws FileNotFoundException {
        setInputStream(file);
        JsonReader reader = Json.createReader(is);
        JsonObject jsonObject = reader.readObject();
        reader.close();
        closeInputStream();
        return jsonObject;
    }

    /**
     * @param file     gets an array value from a specified json file
     * @param property property to find the correct value
     * @return the JsonArray
     * @throws FileNotFoundException
     */
    private JsonArray getJsonArrayValue(String file, String property) throws FileNotFoundException {
        JsonObject jsonObject = getJsonObject(file);
        return jsonObject.getJsonArray(property);
    }

    /**
     * Gets all the messsages from the messages.json file
     *
     * @return a List of all the messages
     */
    public List<String> getMessages() {
        List<String> result = new ArrayList<>();

        JsonArray jsonArray = null;
        try {
            jsonArray = getJsonArrayValue("messages.json", "messages");
            for (int i = 0; i < jsonArray.size(); i++) {
                result.add(jsonArray.getString(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //return getJsonArrayValue("messages.json", "messages");
        return result;
    }

    /**
     * Gets a random message from the messages.json file
     *
     * @return a random message
     */
    public String getRandomMessage() {
        List<String> messages = getMessages();
        Collections.shuffle(messages);
        return messages.get(0);
    }

    /**
     * Gets all the victims form the victims.json file
     *
     * @return A Person list of victims
     */
    public List<Person> getVictims() {
        List<Person> people = new ArrayList<>();
        try {
            JsonArray victims = getJsonArrayValue("victims.json", "victims");
            for (JsonValue jsonVictim :
                    victims) {
                JsonObject json = jsonVictim.asJsonObject();

                people.add(new Person(
                        json.getString("surname"),
                        json.getString("name"),
                        json.getString("email"))
                );
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return people;
    }

    /**
     * Gets a property value from the config.properties file
     *
     * @param name name of the property to get
     * @return the value of the property
     */
    public String getPropValue(String name) {
        setInputStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeInputStream();

        return properties.getProperty(name);
    }
}
