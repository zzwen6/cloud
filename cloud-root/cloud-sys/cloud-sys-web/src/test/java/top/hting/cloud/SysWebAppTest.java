package top.hting.cloud;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Unit test for simple App.
 */
public class SysWebAppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        String encode = bCryptPasswordEncoder.encode("123456");

        System.out.println(bCryptPasswordEncoder.matches("123456", encode));

        System.out.println(encode);

    }
}
