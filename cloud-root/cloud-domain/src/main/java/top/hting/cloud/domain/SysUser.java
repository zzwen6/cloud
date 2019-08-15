package top.hting.cloud.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {

    @TableId("userId")
    private Long userId;

    private String userName;

    private String account;

    private String password;

}
