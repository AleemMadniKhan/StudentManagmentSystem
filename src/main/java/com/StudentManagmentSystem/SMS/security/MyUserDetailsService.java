package com.StudentManagmentSystem.SMS.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.StudentManagmentSystem.SMS.model.User;
import com.StudentManagmentSystem.SMS.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepo repo;
    public MyUserDetailsService(UserRepo repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByEmail(username);
        if(user == null)
        throw new UsernameNotFoundException("User with Email " + username + " not found.");

        return new UserPrincipal(user);
    }


    
}
