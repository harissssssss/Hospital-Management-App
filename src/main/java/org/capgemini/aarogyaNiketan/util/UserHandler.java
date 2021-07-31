package org.capgemini.aarogyaNiketan.util;

import org.capgemini.aarogyaNiketan.Repository.UserRepository;
import org.capgemini.aarogyaNiketan.model.MyUserDetails;
import org.capgemini.aarogyaNiketan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserHandler{

    @Autowired
    UserRepository userRepository;

    public User getLoggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails)auth.getPrincipal();
        Optional<User> user = userRepository.findByUserName(userDetails.getUsername());
        return user.orElseGet(User::new);
    }
}
