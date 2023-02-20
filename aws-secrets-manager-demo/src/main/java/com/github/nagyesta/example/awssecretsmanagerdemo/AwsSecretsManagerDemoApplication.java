package com.github.nagyesta.example.awssecretsmanagerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class AwsSecretsManagerDemoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(AwsSecretsManagerDemoApplication.class, args)
                .getBean(MySqlConnectionCheck.class).verifyConnectivity();
    }
}
