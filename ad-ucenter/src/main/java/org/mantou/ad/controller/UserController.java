package org.mantou.ad.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mantou.ad.common.RespWrapper;
import org.mantou.ad.dto.UserDto;
import org.mantou.ad.model.AdUser;
import org.mantou.ad.service.UserService;
import org.mantou.ad.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户接口")
@RestController
@RequestMapping("ucenter")
public class UserController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    @ApiOperation(value = "系统登陆", httpMethod = "GET")
    public RespWrapper login(@ApiParam(value = "用户对象", name = "username", required = true) UserDto userDto) {
        AdUser user = userService.findUser(userDto);

        HttpServletResponse respone = WebUtils.getRespone();
        respone.setHeader("accessId", user.getId());
        return RespWrapper.ok(user);
    }

}
