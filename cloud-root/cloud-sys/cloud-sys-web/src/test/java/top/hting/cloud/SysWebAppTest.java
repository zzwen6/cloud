package top.hting.cloud;

import com.alibaba.fastjson.JSON;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import top.hting.cloud.dto.UserDto;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysWebAppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String json = "{\"permissions\":[{\"pid\":1,\"resourceUrl\":\"/sysUser/getUserPermissionById\"},{\"pid\":2,\"resourceUrl\":\"/sysUser/get1\"},{\"pid\":3,\"resourceUrl\":\"/sysUser/remove\"}],\"user\":{\"account\":\"zzw\",\"userId\":1,\"userName\":\"听不见\"}}";

        UserDto userDto = JSON.parseObject(json, UserDto.class);

        System.out.println(userDto.getPermissions().size());


    }
}
