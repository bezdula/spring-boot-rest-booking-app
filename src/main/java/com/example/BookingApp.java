package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example")
public class BookingApp {

    // TODO: actions:
    //  - add new
    //  - query (endpoint) for selecting Units by all specified criteria, with the date range and cost
    //  - book + payment (cancelled after 15 min)

    public static void main(String[] args) {
        SpringApplication.run(BookingApp.class, args);
    }
}
