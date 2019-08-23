package top.hting.cloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.hting.cloud.dto.JwtUserDto;
import top.hting.cloud.response.domain.RestfulResponse;
import top.hting.cloud.service.AuthTokenService;

/**
 * jwt相关接口
 */
@RestController
@RequestMapping("/jwt")
public class AuthTokenController {

    @Autowired
    AuthTokenService authTokenService;

    /**
     * 生成授权token
     */
    @RequestMapping(value = "token",method = RequestMethod.POST)
    public RestfulResponse createToken(@RequestBody JwtUserDto userDto){

        // 去验证账号密码是否正确
        String result =  authTokenService.login(userDto);

        return new RestfulResponse().success(result);

    }

}
