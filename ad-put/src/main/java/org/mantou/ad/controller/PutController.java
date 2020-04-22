package org.mantou.ad.controller;

import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.feign.SearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PutController {

    @Autowired
    private SearchClient ucenterClient;

    @RequestMapping("/put/test")
    public RespWrapper hello(){
        String rest = ucenterClient.hello();
        return RespWrapper.ok(rest);
    }
}
