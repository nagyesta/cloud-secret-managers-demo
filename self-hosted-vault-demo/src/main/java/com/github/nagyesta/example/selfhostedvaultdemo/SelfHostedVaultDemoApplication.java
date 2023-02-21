package com.github.nagyesta.example.selfhostedvaultdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class SelfHostedVaultDemoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SelfHostedVaultDemoApplication.class, args)
                .getBean(MySqlConnectionCheck.class).verifyConnectivity();
    }
}
