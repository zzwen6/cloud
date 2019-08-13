package top.hting.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.cloud.domain.SysUser;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findById(Long userId);
}
