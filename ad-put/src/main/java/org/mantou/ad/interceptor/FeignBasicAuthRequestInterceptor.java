package org.mantou.ad.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.mantou.ad.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * feign的请求拦截器
 */
@Slf4j
public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public FeignBasicAuthRequestInterceptor(){

    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String token = WebUtils.getRequest().getHeader("Authorization");
        log.error("feign的统一token值为：{}",token);
//        String token = stringRedisTemplate.opsForValue().get("token:" + accessId);
        requestTemplate.header("Authorization",token);
    }
}
