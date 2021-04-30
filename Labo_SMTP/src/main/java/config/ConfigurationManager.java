package config;

import mail.Person;

import javax.json.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigurationManager { //groupe offrant des methodes pour aller chercher des infos dans les fichier de config
    private InputStream is;

    private void setInputStream(String file) {
        is = this.getClass().getClassLoader().getResourceAsStream(file);
    }
    private void closeInputStream() {
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonObject getJsonObject(String file) throws FileNotFoundException {
        setInputStream(file);
        JsonReader reader = Json.createReader(is);
        JsonObject jsonObject = reader.readObject();
        reader.close();
        closeInputStream();
        return jsonObject;
    }

    private JsonArray getJsonArrayValue(String file, String property) throws FileNotFoundException {
        JsonObject jsonObject = getJsonObject(file);
        return jsonObject.getJsonArray(property);
    }

    public List<String> getMessages() {
        List<String> result = new ArrayList<>();

        JsonArray jsonArray = null;
        try {
            jsonArray = getJsonArrayValue("messages.json", "messages");
            for (int i = 0; i < jsonArray.size(); i++ ) {
                result.add(jsonArray.getString(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //return getJsonArrayValue("messages.json", "messages");
        return result;
    }

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
