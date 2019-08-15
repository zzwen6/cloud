package top.hting.cloud.service;

import top.hting.cloud.dto.JwtUserDto;

public interface AuthTokenService {

    /**
     * 用户登录验证
     * @param userDto
     * @return
     */
    String login(JwtUserDto userDto);

}
