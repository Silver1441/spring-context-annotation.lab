package org.shop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import static org.shop.common.Sellers.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@Import({RepositoryConfiguration.class, InitializerConfiguration.class, ServiceConfiguration.class})
public class ApplicationConfiguration {

    @Bean("seller.list")
    public Map<Long, String> sellerNames() {
        Map<Long, String> sellerNames = new HashMap<>();
        sellerNames.put(1L, AMAZON);
        sellerNames.put(2L, SAMSUNG);
        return sellerNames;
    }
}