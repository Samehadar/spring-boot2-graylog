package com.logging.springboot2graylog.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.logging.springboot2graylog.SpringBoot2GraylogApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class ServiceController {
    AtomicInteger asd = new AtomicInteger(0);

    private  Logger logger = LoggerFactory.getLogger(SpringBoot2GraylogApplication.class);


    @GetMapping("/app/status")
    public String controllerStatus() {
        return "Service controller is up";
    }

    @PostConstruct
    public void ping() {
        new Thread(() -> {
            while (true) {
                try {
                    logger.info("PING " + asd.incrementAndGet());
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {}
            }
        }).start();
    }
}
