package config;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;
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

    private String[] getJsonArrayValue(String file, String property) throws FileNotFoundException {
        JsonObject jsonObject = getJsonObject(file);

        if (jsonObject == null) {
            return new String[0];
        }

        JsonArray jsonArray = jsonObject.getJsonArray(property);
        List<String> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++ ) {
            result.add(jsonArray.getString(i));
        }

        return result.toArray(new String[0]);
    }

    public String[] getMessages() {
        try {
            return getJsonArrayValue("messages.json", "messages");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public String[] getVictims() {
        try {
            return getJsonArrayValue( "victims.json", "victims");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new String[0];
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
