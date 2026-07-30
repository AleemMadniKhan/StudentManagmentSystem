package com.StudentManagmentSystem.SMS.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.StudentManagmentSystem.SMS.model.Permissions;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.StudentManagmentSystem.SMS.model.User;

public class UserPrincipal implements UserDetails{

    User user = new User();
    
    public UserPrincipal(User user){
        this.user = user;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority> authorities = new ArrayList<>();
      for (Permissions permission : user.getRole().getPermissions()) {
        authorities.add(new SimpleGrantedAuthority(permission.name()));
      }
      authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
      return authorities;
    }
    public int getId(){
        return user.getId();
    }
    @Override
    public @Nullable String getPassword() {
      return user.getPassword();
    }
    @Override
    public String getUsername() {
       return user.getEmail();
    }
        @Override
    public boolean isAccountNonExpired() {
        return true; // Tells Spring the account hasn't expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Tells Spring the account isn't locked/banned
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Tells Spring the password credentials haven't expired
    }

    @Override
    public boolean isEnabled() {
        return true; // Tells Spring the user profile account is active
    }
}
