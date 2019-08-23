package top.hting.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户角色关联
 */
@Data
@TableName("user_role_relation")
public class UserRoleRelation {
    @TableId("userId")
    private long userId;
    @TableId("roleId")
    private long roleId;

}
