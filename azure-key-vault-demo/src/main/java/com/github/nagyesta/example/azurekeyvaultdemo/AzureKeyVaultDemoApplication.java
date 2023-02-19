package com.github.nagyesta.example.azurekeyvaultdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootApplication
public class AzureKeyVaultDemoApplication {

    public static void main(String[] args) throws SQLException {
        final DataSource dataSource = SpringApplication.run(AzureKeyVaultDemoApplication.class, args)
                .getBean(DataSource.class);
        try (final Connection connection = dataSource.getConnection()) {
            query(connection);
        }
    }

    private static void query(Connection connection) throws SQLException {
        final String sql = "SELECT CONCAT(@@version_comment, ' - ', VERSION()) FROM DUAL";
        try (final ResultSet resultSet = connection.prepareStatement(sql).executeQuery()) {
            resultSet.next();
            final String value = resultSet.getString(1);
            System.err.println(value);
        }
    }

}
