package com.github.nagyesta.example.azurekeyvaultdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class AzureKeyVaultDemoApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(AzureKeyVaultDemoApplication.class, args)
                .getBean(MySqlConnectionCheck.class).verifyConnectivity();
    }

}
