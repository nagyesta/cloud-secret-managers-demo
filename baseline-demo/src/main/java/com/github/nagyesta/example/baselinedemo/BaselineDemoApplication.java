package com.github.nagyesta.example.baselinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class BaselineDemoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(BaselineDemoApplication.class, args)
                .getBean(MySqlConnectionCheck.class).verifyConnectivity();
    }
}
