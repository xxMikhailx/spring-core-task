package com.epam.spring.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(LoggersConfig.class)
@PropertySource("classpath:client.properties")
@EnableAspectJAutoProxy
public class AppConfig {

    /*@Autowired
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource(){
        return new DriverManagerDataSource(environment.getRequiredProperty("jdbc.url"),
                environment.getRequiredProperty("jdbc.username"),
                environment.getRequiredProperty("jdbc.password"));
    }*/
}
