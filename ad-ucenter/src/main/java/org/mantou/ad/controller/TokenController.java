package org.mantou.ad.controller;

import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("token")
    public RespWrapper getToken(String uid){
        String token = tokenService.getToken(uid);
        return RespWrapper.ok(token);
    }
}
