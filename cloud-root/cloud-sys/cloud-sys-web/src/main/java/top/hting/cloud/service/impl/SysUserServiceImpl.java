package top.hting.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.hting.cloud.domain.SysUser;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.mapper.SysUserMapper;
import top.hting.cloud.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public SysUser findById(Long userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public UserDto findByAccount(JwtUserDto userDto) {
        UserDto db = sysUserMapper.findByAccount(userDto.getAccount());
        if (encoder.matches(userDto.getPassword(), db.getUser().getPassword())) {
            return db;
        }
        return new UserDto();
    }
}
