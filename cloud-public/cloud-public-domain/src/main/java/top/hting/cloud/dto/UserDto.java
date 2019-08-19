package top.hting.cloud.dto;

import lombok.Data;
import top.hting.cloud.entity.SysUser;

/**
 * 用户，包含权限，角色等信息
 */
@Data
public class UserDto {

    private SysUser user;

    // private SysRole role;


}
