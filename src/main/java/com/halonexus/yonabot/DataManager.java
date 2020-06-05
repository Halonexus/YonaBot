package com.halonexus.yonabot;

import lombok.Getter;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataManager {
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final String DEFAULT_CONFIG;
    @Getter
    private static Config config;


    static {
        JSONObject json = new JSONObject();
        json.put("token", "");
        json.put("prefix", "y?");
        DEFAULT_CONFIG = json.toString(1);
    }

    public static Config readConfig(String file) {
        //try find file
        Path configPath = Paths.get(file);
        //catch no file
        //make new file throw error terminate
        if (!configPath.toFile().exists()) {
            try {
                if (configPath.toFile().createNewFile()) {
                    Files.write(configPath, DEFAULT_CONFIG.getBytes(UTF8));
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.exit(0);
        }
        //read json string
        Config config = new Config();
        try {
            JSONObject json = new JSONObject(Files.readString(configPath, UTF8));
            config.token = (String) json.get("token");
            config.prefix = (String) json.get("prefix");
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        DataManager.config = config;
        return config;
    }
}
