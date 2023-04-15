package com.weng.nlandapiclientsdk;

import com.weng.nlandapiclientsdk.client.NlandApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("nland.client")
@Data
@ComponentScan
public class NlandApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public NlandApiClient nlandClient() {
        return new NlandApiClient(accessKey, secretKey);
    }

}
