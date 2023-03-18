package com.github.nagyesta.example.azurekeyvaultdemo.config.dev;

import com.azure.core.credential.BasicAuthenticationCredential;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.SecretServiceVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevClientConfig {

    @Value("${app.secrets.url}")
    private String vaultUrl;
    @Value("${app.secrets.user}")
    private String vaultUser;
    @Value("${app.secrets.pass}")
    private String vaultPass;

    @Bean
    public SecretClient secretClient() {
        return new SecretClientBuilder()
                .credential(new BasicAuthenticationCredential(vaultUser, vaultPass))
                .vaultUrl(vaultUrl)
                .serviceVersion(SecretServiceVersion.V7_3)
                .disableChallengeResourceVerification()
                .buildClient();
    }
}
