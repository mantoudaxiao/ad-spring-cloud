package org.mantou.ad.config;

import org.mantou.ad.fiter.HttpBasicAuthorizeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<HttpBasicAuthorizeFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        HttpBasicAuthorizeFilter httpBasicAuthorizeFilter = new HttpBasicAuthorizeFilter();
        filterRegistrationBean.setFilter(httpBasicAuthorizeFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
