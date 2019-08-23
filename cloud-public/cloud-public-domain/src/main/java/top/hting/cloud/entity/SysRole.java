package top.hting.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色列表
 */
@Data
@TableName("sys_role")
public class SysRole {
    @TableId("roleId")
    private long roleId;

    private String roleName;

}
