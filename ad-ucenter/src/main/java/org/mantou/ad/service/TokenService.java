package org.mantou.ad.service;

import com.google.common.collect.Maps;
import org.mantou.ad.dto.UserDto;
import org.mantou.ad.mapper.UserMapper;
import org.mantou.ad.model.AdUser;
import org.mantou.ad.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserMapper userMapper;

    JWTUtils jwt = JWTUtils.getInstance();

    public String getToken(String uid) {
        UserDto userDto = UserDto.builder().id(uid).build();
        AdUser user = userMapper.findUser(userDto);
        Map<String, Object> claims = Maps.newHashMap();
        claims.put("address", user.getAddress());
        claims.put("idcard", user.getIdcard());
        String token = JWTUtils.getInstance()
                .getToken(user.getId(), claims);
        stringRedisTemplate.opsForValue().set("token:" + user.getId(), token);
        return token;
    }
}
