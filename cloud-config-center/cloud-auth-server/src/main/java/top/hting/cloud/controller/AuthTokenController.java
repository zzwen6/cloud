package top.hting.cloud.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * jwt相关接口
 */
@RestController
@RequestMapping("/auth")
public class AuthTokenController {

    @RequestMapping(value = "token",method = RequestMethod.POST)
    public void createToken(){


    }

}
