package top.hting.cloud.service;

import top.hting.cloud.domain.SysUser;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;


public interface SysUserService {


    SysUser findById(Long userId);


    UserDto findByAccount(JwtUserDto userDto);
}
