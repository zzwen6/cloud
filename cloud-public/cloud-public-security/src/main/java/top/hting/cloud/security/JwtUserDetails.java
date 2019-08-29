package top.hting.cloud.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JwtUserDetails implements UserDetails {
    private String password;
    private String username;
    private List<String> permissions;
    public JwtUserDetails(String password, String username,List<String> permissions) {
        this.password = password;
        this.username = username;
        this.permissions = permissions;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simples = new ArrayList<>();

        permissions.forEach(p -> {
            simples.add(new SimpleGrantedAuthority(p));
        });

        return simples;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
