package top.hting.cloud.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtUserDto {

    private String userName;

    private String account;

    private String password;

}
