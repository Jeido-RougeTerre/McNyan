package com.jeido.mcnyan.http;

import com.google.gson.Gson;
import com.jeido.mcnyan.Config;
import com.jeido.mcnyan.McNyan;
import com.jeido.mcnyan.message.VnyanMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;

import java.io.IOException;

public class HttpRequestHandler {
    private static HttpRequestHandler instance;

    private static final Logger LOGGER = McNyan.LOGGER;
    private static final String URL = Config.host + ":" + Config.port;


    private HttpRequestHandler() {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpGet get = new HttpGet(URL);
            HttpResponse resp = client.execute(get);

            int sCode = resp.getStatusLine().getStatusCode();
            if (sCode != 200) {
                throw new IOException("request returned " + sCode + " : " + resp.getStatusLine());
            }

            LOGGER.info("Nyaaaa ~ !! Associated to '{}' ᓚᘏᗢ", URL);
        } catch (IOException e) {
            LOGGER.error("Nyaaa :< ! Something went wrong '{}'", e.getMessage());
        }
    }

    public static HttpRequestHandler getInstance() {
        if (instance == null) {
            instance = new HttpRequestHandler();
        }
        return instance;
    }

    public void sendMessage(VnyanMessage message) {
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            Gson gson = new Gson();
            HttpPost post = new HttpPost(URL);
            StringEntity stringEntity = new StringEntity(gson.toJson(message));
            post.setEntity(stringEntity);
            post.setHeader("Content-type", "application/json");
            HttpResponse resp = client.execute(post);

            int sCode = resp.getStatusLine().getStatusCode();
            if (sCode != 200) {
                throw new IOException("request returned " + sCode + " : " + resp.getStatusLine());
            }

            if (Config.debugMode) {
                LOGGER.info("Sent {}", gson.toJson(message));
            }

        } catch (IOException e) {
            LOGGER.warn("Nyaa :/ ! Could not send Message '{}' to Vnyan : {}", message.action, e.getMessage());
        }
    }
}
