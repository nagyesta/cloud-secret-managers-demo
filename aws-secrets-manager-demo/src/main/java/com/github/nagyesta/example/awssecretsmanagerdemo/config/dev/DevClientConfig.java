package com.github.nagyesta.example.awssecretsmanagerdemo.config.dev;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevClientConfig {

    @Value("${app.secrets.url}")
    private String managerUrl;
    @Value("${app.secrets.accessKey}")
    private String managerAccessKey;
    @Value("${app.secrets.secretKey}")
    private String managerSecretKey;

    @Bean
    public AWSSecretsManager secretClient() {
        final var endpointConfiguration =
                new EndpointConfiguration(managerUrl, Regions.DEFAULT_REGION.getName());
        final var credentials =
                new BasicAWSCredentials(managerAccessKey, managerSecretKey);
        return AWSSecretsManagerClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

}
