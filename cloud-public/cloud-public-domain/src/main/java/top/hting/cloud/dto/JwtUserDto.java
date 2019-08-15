package top.hting.cloud.dto;

import lombok.Data;

@Data
public class JwtUserDto {

    private String userName;

    private String account;

    private String password;

}
