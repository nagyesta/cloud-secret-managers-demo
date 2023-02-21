package com.github.nagyesta.example.selfhostedvaultdemo.config;

import org.springframework.stereotype.Component;
import org.springframework.vault.annotation.VaultPropertySource;

@Component("SecretPropertySource")
@VaultPropertySource(value = "secret/datasource", propertyNamePrefix = "spring.datasource.")
public class SecretPropertySource {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

}
