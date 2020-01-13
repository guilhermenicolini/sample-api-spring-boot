package com.github.guilhermenicolini.sampleapispringboot.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HealthCheckJob {

    private static final Logger logger = LoggerFactory.getLogger(HealthCheckJob.class);

    @Value("${api.url}")
    private String url;

    @Scheduled(fixedDelayString = "${api.delay}")
    public void checkHealth() {

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
