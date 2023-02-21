package com.github.nagyesta.example.selfhostedvaultdemo.config.dev;

import com.github.nagyesta.example.selfhostedvaultdemo.config.SecretPropertySource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;
import org.springframework.vault.core.VaultTemplate;

import java.net.URI;

@Configuration
@Profile("dev")
public class DevClientConfig extends AbstractVaultConfiguration {

    @Override
    public VaultEndpoint vaultEndpoint() {
        final String uri = getEnvironment().getRequiredProperty("app.secrets.url");
        return VaultEndpoint.from(URI.create(uri));
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        final String token = getEnvironment().getRequiredProperty("app.secrets.token");
        return new TokenAuthentication(token);
    }

    @Override
    public VaultTemplate vaultTemplate() {
        final VaultTemplate vaultTemplate = super.vaultTemplate();
        final SecretPropertySource datasourceProperties = new SecretPropertySource();
        datasourceProperties.setUrl("jdbc:mysql://localhost:15306/");
        datasourceProperties.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasourceProperties.setUsername("root");
        datasourceProperties.setPassword("16276ec1-a682-4022-b859-38797969abc6");
        vaultTemplate.write("secret/datasource", datasourceProperties);
        return vaultTemplate;
    }

}
