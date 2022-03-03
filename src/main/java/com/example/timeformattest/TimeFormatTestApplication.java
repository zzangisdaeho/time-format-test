package com.example.timeformattest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class TimeFormatTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeFormatTestApplication.class, args);
    }


    @PostConstruct
    public void started() {
        // timezone UTC 셋팅
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
