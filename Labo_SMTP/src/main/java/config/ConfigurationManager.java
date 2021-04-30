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

    public ConfigurationManager(String file) throws FileNotFoundException {
        is = this.getClass().getClassLoader().getResourceAsStream(file);

        if (is == null) {
            throw new FileNotFoundException("file '" + file + "' not found");
        }
    }

    private JsonObject getJsonObject() {
        JsonReader reader = Json.createReader(is);
        JsonObject jsonObject = reader.readObject();
        reader.close();
        return jsonObject;
    }

    private String[] getJsonArrayValue(String property) {
        JsonObject jsonObject = getJsonObject();

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
        return getJsonArrayValue( "messages");
    }

    public String[] getVictims() {
        return getJsonArrayValue( "victims");
    }

    public String getPropValue(String name) throws IOException {
        Properties properties = new Properties();
        properties.load(is);

        return properties.getProperty(name);
    }
}
