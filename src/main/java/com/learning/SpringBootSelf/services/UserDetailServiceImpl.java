package com.learning.SpringBootSelf.services;

import com.learning.SpringBootSelf.entaties.User;
import com.learning.SpringBootSelf.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= repo.findByUserName(username);

        if(user==null) throw new UsernameNotFoundException("User not found");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();

    }
}




//@Component
//public class UserDetailServiceImpl implements UserDetailsService {
//
//    @Autowired
//    UserRepo userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRepo.findByUserName(username);
//
//        if(user !=null){
//            return org.springframework.security.core.userdetails.User
//                    .builder()
//                    .username(user.getUserName())
//                    .password(user.getPassword())
//                    .roles(user.getRole())
//                    .build();
//        }
//
//        throw new UsernameNotFoundException("User not found");
//    }
//}
