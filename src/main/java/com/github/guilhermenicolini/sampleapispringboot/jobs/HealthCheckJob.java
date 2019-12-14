package com.github.guilhermenicolini.sampleapispringboot.jobs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HealthCheckJob {

    @Value("${api.url}")
    private String url;

    @Scheduled(fixedDelay = 60000)
    public void checkHealth() throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        System.out.println(con.getResponseCode());
    }
}
