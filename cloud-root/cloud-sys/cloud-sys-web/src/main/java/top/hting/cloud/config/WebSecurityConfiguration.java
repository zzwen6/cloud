package top.hting.cloud.config;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.hting.cloud.security.AuthenticationAccessDeniedHandler;
import top.hting.cloud.security.AuthenticationTokenFilter;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private String LOGIN_URL = "/sysUser/login";

    private String SECURE_URL="/**";
    private static final String[] AUTH_WHITELIST = {
        // -- register url
        "/users/signup",
        // -- swagger ui
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/sysUser/login"
    };
    // @Autowired
    // private AbstractAuthenticationProcessingFilter tokenAuthenticationFilter;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(AUTH_WHITELIST).permitAll()
            .antMatchers(LOGIN_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic().disable()
            .formLogin().disable()
            .rememberMe().disable()
            .addFilterBefore(getTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationTokenFilter getTokenAuthenticationFilter(){
        return new AuthenticationTokenFilter(userDetailsService);
    }




}
