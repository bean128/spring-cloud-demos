package com.abc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Consumer03Foreign8080 {

    public static void main(String[] args) {
        SpringApplication.run(Consumer03Foreign8080.class, args);
    }

}
