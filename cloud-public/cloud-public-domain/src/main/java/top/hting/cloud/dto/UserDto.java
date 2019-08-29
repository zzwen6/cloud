package top.hting.cloud.dto;

import lombok.Data;
import top.hting.cloud.entity.SysPermission;
import top.hting.cloud.entity.SysUser;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 用户，包含权限，角色等信息
 */
@Data
public class UserDto implements Serializable {

    private SysUser user;

    // private SysRole role;

    private List<SysPermission> permissions;
}
