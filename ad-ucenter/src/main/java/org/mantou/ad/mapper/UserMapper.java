package org.mantou.ad.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mantou.ad.dto.UserDto;
import org.mantou.ad.model.AdUser;

public interface UserMapper extends BaseMapper<AdUser> {

    AdUser findUser(UserDto userDto);

}
