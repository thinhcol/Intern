package com.fis.sdc.checkfile;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class UserCustomDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private User user;

    Set<GrantedAuthority> authorities=null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities)
    {
        this.authorities=authorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    public boolean isEnabled() {
        return user.isEnabled();
    }
}
