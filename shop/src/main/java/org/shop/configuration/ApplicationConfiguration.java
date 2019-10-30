package org.shop.configuration;

import org.shop.processor.InjectRandomIntBeanPostProcessor;
import org.shop.util.SessionRandomValuesHolder;
import org.springframework.context.annotation.*;

import static org.shop.common.Sellers.*;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"org.shop.aspect"})
@EnableAspectJAutoProxy
@Import({RepositoryConfiguration.class, InitializerConfiguration.class, ServiceConfiguration.class})
public class ApplicationConfiguration {

    @Bean("seller.list")
    public Map<Long, String> sellerNames() {
        Map<Long, String> sellerNames = new HashMap<>();
        sellerNames.put(1L, AMAZON);
        sellerNames.put(2L, SAMSUNG);
        return sellerNames;
    }

    @Bean
    public InjectRandomIntBeanPostProcessor injectRandomIntBeanPostProcessor() {
        return new InjectRandomIntBeanPostProcessor();
    }

    @Bean
    public SessionRandomValuesHolder randomValuesHolder() {
        return new SessionRandomValuesHolder();
    }
}