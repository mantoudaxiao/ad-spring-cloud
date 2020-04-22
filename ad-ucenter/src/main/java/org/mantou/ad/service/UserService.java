package org.mantou.ad.service;

import org.mantou.ad.dto.UserDto;
import org.mantou.ad.mapper.UserMapper;
import org.mantou.ad.model.AdUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public AdUser findUser(UserDto userDto) {
        AdUser user = userMapper.findUser(userDto);
        return user;
    }
}
