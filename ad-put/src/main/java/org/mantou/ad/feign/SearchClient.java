package org.mantou.ad.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ad-search")
public interface SearchClient {

    @RequestMapping("/search/hello")
    public String hello();

}
