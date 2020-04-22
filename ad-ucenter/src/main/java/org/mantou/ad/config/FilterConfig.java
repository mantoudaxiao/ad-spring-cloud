package org.mantou.ad.config;

import org.mantou.ad.fiter.HttpBasicAuthorizeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class FilterConfig {

    //@Bean
    public FilterRegistrationBean<HttpBasicAuthorizeFilter> filterRegistrationBean(){
        FilterRegistrationBean<HttpBasicAuthorizeFilter> registrationBean = new FilterRegistrationBean<HttpBasicAuthorizeFilter>();
        HttpBasicAuthorizeFilter httpBasicFilter = new HttpBasicAuthorizeFilter();
        registrationBean.setFilter(httpBasicFilter);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

}
