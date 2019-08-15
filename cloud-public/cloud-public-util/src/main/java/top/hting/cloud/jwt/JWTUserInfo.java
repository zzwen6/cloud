package top.hting.cloud.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTUserInfo {

    /**用户的账号*/
    private String account;

    /**用户显示名称*/
    private String userName;

    /**用户userId(为保证通用性用string类型)，数据库设置为数值型的请自行转换*/
    private String userId;

}
