package config;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager { //groupe offraqnt des methodes pour aller chercher des infos dans les fichier de config
    private static JsonObject getJsonObject(String path) {
        File jsonInputFile = new File(path);
        InputStream is;
        JsonObject jsonObject = null;

        try {
            is = new FileInputStream(jsonInputFile);

            JsonReader reader = Json.createReader(is);
            jsonObject = reader.readObject();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    public static String[] getMessages() {
        JsonObject jsonMessage = getJsonObject("./src/main/java/config/messages.json");

        if (jsonMessage == null) {
            return new String[0];
        }

        JsonArray jsonArrayMessages = jsonMessage.getJsonArray("messages");
        List<String> messages = new ArrayList<>();

        for (int i = 0; i < jsonArrayMessages.size(); i++ ) {
            messages.add(jsonArrayMessages.getString(i));
        }

        return messages.toArray(new String[0]);
    }
}
