package top.hting.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.hting.cloud.entity.SysUser;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.dto.UserDto;
import top.hting.cloud.mapper.SysUserMapper;
import top.hting.cloud.redis.RedisUtils;
import top.hting.cloud.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public SysUser findById(Long userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public UserDto findJwtUserByAccountThenValid(JwtUserDto userDto) {
        UserDto db = sysUserMapper.findByAccount(userDto.getAccount());
        if (encoder.matches(userDto.getPassword(), db.getUser().getPassword())) {
            return db;
        }
        return new UserDto();
    }

    @Override
    public UserDto findByAccount(String account) {
        redisUtils.set("userId:account:" + account , account);
        return sysUserMapper.findByAccount(account);
    }
}
