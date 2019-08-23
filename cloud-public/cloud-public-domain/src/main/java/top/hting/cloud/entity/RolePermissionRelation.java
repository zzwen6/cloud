package top.hting.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色权限关联
 */
@Data
@TableName("role_permission_relation")
public class RolePermissionRelation {

    @TableId("roleId")
    private long roleId;
    @TableId("pid")
    private long pid;

}
