package top.hting.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 权限列表
 */
@Data
@TableName("sys_permession")
public class SysPermission {
    @TableId("pid")
    private long pid;

    /**
     * 资源路径
     */
    private String resourceUrl;

}
