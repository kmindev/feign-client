package dev_be.feign_client.feign.config;

import dev_be.feign_client.feign.decoder.DemoFeignErrorDecoder;
import dev_be.feign_client.feign.interceptor.DemoFeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFeignConfig {

    @Bean
    public DemoFeignInterceptor feignInterceptor() {
        return new DemoFeignInterceptor();
    }

    @Bean
    public DemoFeignErrorDecoder demoErrorDecoder() {
        return new DemoFeignErrorDecoder();
    }

}
