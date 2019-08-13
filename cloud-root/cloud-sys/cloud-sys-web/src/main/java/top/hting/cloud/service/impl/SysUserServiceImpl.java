package top.hting.cloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hting.cloud.domain.SysUser;
import top.hting.cloud.mapper.SysUserMapper;
import top.hting.cloud.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public SysUser findById(Long userId) {
        return sysUserMapper.selectById(userId);
    }
}
