package com.github.nagyesta.example.selfhostedvaultdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@DependsOn("SecretPropertySource")
@Component
public class MySqlConnectionCheck {

    private final DataSource dataSource;

    @Autowired
    public MySqlConnectionCheck(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void verifyConnectivity() throws SQLException {
        try (final Connection connection = dataSource.getConnection()) {
            query(connection);
        }
    }

    private void query(Connection connection) throws SQLException {
        final String sql = "SELECT CONCAT(@@version_comment, ' - ', VERSION()) FROM DUAL";
        try (final ResultSet resultSet = connection.prepareStatement(sql).executeQuery()) {
            resultSet.next();
            final String value = resultSet.getString(1);
            //write something that will be visible on the Gradle output
            System.err.println(value);
        }
    }
}
