package com.mycompany.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.Environment;
import okhttp3.*;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

public class VideoUtility {

    private static String USERNAME;
    private static String ACCESS_KEY;

    public static void downloadVideo(String sessionId, String testCaseName, boolean isWeb) throws IOException, URISyntaxException {

        OkHttpClient client = new OkHttpClient();

        URL jsonResource = Environment.class.getClassLoader().getResource("capabilities.json");
        String content = new String(Files.readAllBytes(Paths.get(jsonResource.toURI())));
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(content);
        JsonNode chromeWebconfig = rootNode.get("ChromeWeb");
        USERNAME = chromeWebconfig.get("browserstack.user").asText();
        ACCESS_KEY = chromeWebconfig.get("browserstack.key").asText();
        String url = "https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json";
        if (isWeb) {
            url = "https://www.browserstack.com/automate/sessions/" + sessionId + ".json";

        }
        System.out.println(url);


        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Credentials.basic(USERNAME, ACCESS_KEY))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            JSONObject jsonResponse = new JSONObject(response.body().string());
            String videoUrl = jsonResponse.getJSONObject("automation_session").getString("video_url");

            // Download video
            Request videoRequest = new Request.Builder().url(videoUrl).build();
            try (Response videoResponse = client.newCall(videoRequest).execute()) {
                if (!videoResponse.isSuccessful()) throw new IOException("Unexpected code " + videoResponse);
                String projectRootPath = System.getProperty("user.dir")+"\\Videos";
                File f = new File(projectRootPath);
                f.mkdir();

                Path videoPath = Paths.get(f.getPath()+"\\"+ testCaseName + ".mp4");
                Files.copy(videoResponse.body().byteStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Video downloaded to: " + videoPath.toAbsolutePath());
            }
        }
    }

    public static void downloadAllVideos() {
        HashMap<String, String> failedSessions = new HashMap<>();
        for (String name : Environment.getSessionMap().keySet()) {
            try {
                Thread.sleep(4000);
                if (name.contains("Native")) {
                    downloadVideo(Environment.getSessionMap().get(name), name, false);
                } else {
                    downloadVideo(Environment.getSessionMap().get(name), name, true);
                }
            } catch (Exception e) {
                failedSessions.put(name, Environment.getSessionMap().get(name));
            }
        }
        for (String name : failedSessions.keySet()) {
            try {
                Thread.sleep(4000);
                if (name.contains("Native")) {
                    downloadVideo(Environment.getSessionMap().get(name), name, false);
                } else {
                    downloadVideo(Environment.getSessionMap().get(name), name, true);
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }

}


