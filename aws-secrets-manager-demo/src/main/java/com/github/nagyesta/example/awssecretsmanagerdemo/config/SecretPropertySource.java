package com.github.nagyesta.example.awssecretsmanagerdemo.config;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component("SecretPropertySource")
public class SecretPropertySource extends EnumerablePropertySource<Map<String, String>> {

    private final AWSSecretsManager client;
    private final Map<String, String> mapping;
    private final Map<String, String> cache;

    @Autowired
    public SecretPropertySource(SecretAccessProperties properties,
                                final AWSSecretsManager client,
                                final ConfigurableEnvironment environment) {
        super("aws-secrets");
        this.client = client;
        mapping = Map.of(
                "spring.datasource.driver-class-name", properties.getDriver(),
                "spring.datasource.url", properties.getUrl(),
                "spring.datasource.username", properties.getUsername(),
                "spring.datasource.password", properties.getPassword()
        );
        environment.getPropertySources().addFirst(this);
        cache = new ConcurrentHashMap<>();
    }

    @Override
    public String[] getPropertyNames() {
        return mapping.keySet()
                .toArray(new String[0]);
    }

    @Override
    public String getProperty(String property) {
        if (!Arrays.asList(getPropertyNames()).contains(property)) {
            return null;
        }
        final var key = mapping.get(property);
        //not using computeIfAbsent to avoid locking map while the value is resolved
        if (!cache.containsKey(key)) {
            cache.put(key, client.getSecretValue(new GetSecretValueRequest().withSecretId(key)).getSecretString());
        }
        return cache.get(key);
    }

}
