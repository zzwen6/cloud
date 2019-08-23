package top.hting.cloud.service;

import top.hting.cloud.entity.SysUser;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;


public interface SysUserService {


    SysUser findById(Long userId);

    /**
     * 账号查询用户并进行验证后返回
     * @param userDto
     * @return
     */
    UserDto findJwtUserByAccountThenValid(JwtUserDto userDto);


    /**
     * 直接查询某账号信息，不作验证
     * @param account
     * @return
     */
    UserDto findByAccount(String account);

    UserDto findUserPermission(Long userId);
}
