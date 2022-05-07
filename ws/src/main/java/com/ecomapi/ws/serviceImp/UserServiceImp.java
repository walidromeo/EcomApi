package com.ecomapi.ws.serviceImp;

import com.ecomapi.ws.entities.RoleEntity;
import com.ecomapi.ws.entities.UserEntity;
import com.ecomapi.ws.repositories.RoleRepo;
import com.ecomapi.ws.repositories.UserRepo;
import com.ecomapi.ws.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImp implements UserService, UserDetailsService {


    private final UserRepo userRepo;

    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username);
        if(user==null){
            log.info("User introuvable");
            throw new UsernameNotFoundException("user name not found");
        }

        else{
            log.info("User trouver in db {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new User(user.getUsername(),user.getEncryptedPassword(),authorities);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("saving new user {}",user.getUsername());
        user.setEncryptedPassword(passwordEncoder.encode(user.getEncryptedPassword()));
        return userRepo.save(user);
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleName) {
        log.info("saving new user {}",roleName.getName());
        return roleRepo.save(roleName);
    }


    @Override
    public void addRoleToUser(String username, String role) {
        log.info("adding role {} to user {}",role,username);
        UserEntity user = userRepo.findByUsername(username);
        RoleEntity roleName = roleRepo.findByName(role);
        user.getRoles().add(roleName);
    }

    @Override
    public UserEntity getUser(String username) {
        log.info("Fetching new user {}",username);
        return userRepo.findByUsername(username);
    }

}
