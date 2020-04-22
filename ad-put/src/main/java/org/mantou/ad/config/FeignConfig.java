package org.mantou.ad.config;

import org.mantou.ad.interceptor.FeignBasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new FeignBasicAuthRequestInterceptor();
    }

}
