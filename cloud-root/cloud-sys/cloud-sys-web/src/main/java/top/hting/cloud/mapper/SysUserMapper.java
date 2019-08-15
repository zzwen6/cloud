package top.hting.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.hting.cloud.domain.SysUser;
import top.hting.cloud.dto.UserDto;

public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser findById(Long userId);

    UserDto findByAccount(@Param("account") String account);
}
