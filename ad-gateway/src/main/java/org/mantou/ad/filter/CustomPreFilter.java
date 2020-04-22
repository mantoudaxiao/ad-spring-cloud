package org.mantou.ad.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.config.BasicConfig;
import org.mantou.ad.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class CustomPreFilter extends ZuulFilter {

    @Autowired
    private BasicConfig basicConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.error("进入网关方法........");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String requestURI = request.getRequestURI();
        log.error("请求的url为：{}",requestURI);
        String whiteUrl = basicConfig.getWhiteUrl();
        log.error("白名单的url为：{}",whiteUrl);
        if (whiteUrl.equals(requestURI)){
            log.error("请求的url为：{}",requestURI);
            return null;
        }
        String accessId = request.getHeader("accessId");
        if (StringUtils.isBlank(accessId) || stringRedisTemplate.getExpire("token:"+accessId) == -2){
            log.error("未登录系统.....");
            requestContext.setSendZuulResponse(false);
            requestContext.set("isSuccess", false);
            RespWrapper respWrapper = RespWrapper.error("未登陆，请登录！");
            requestContext.setResponseBody(JsonUtils.toJson(respWrapper));
            requestContext.getResponse().setContentType("application/json;charset=utf-8");
            return null;
        }
        log.error("认证头信息为：{}",stringRedisTemplate.opsForValue().get("token:"+accessId));
        requestContext.addZuulRequestHeader("Authorization",stringRedisTemplate.opsForValue().get("token:"+accessId));
        return null;
    }
}
