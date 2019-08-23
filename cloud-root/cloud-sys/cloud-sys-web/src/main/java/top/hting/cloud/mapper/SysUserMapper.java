package top.hting.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.hting.cloud.entity.SysUser;
import top.hting.cloud.dto.UserDto;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findById(@Param("userId") Long userId);

    UserDto findByAccount(@Param("account") String account);

    UserDto findUserPermissionByUserId(@Param("userId") Long userId);
}
