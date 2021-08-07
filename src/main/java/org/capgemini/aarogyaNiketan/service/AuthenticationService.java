package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.Repository.UserRepository;
import org.capgemini.aarogyaNiketan.dto.request.AuthenticationLoginRequest;
import org.capgemini.aarogyaNiketan.dto.request.AuthenticationRegisterRequest;
import org.capgemini.aarogyaNiketan.model.User;
import org.capgemini.aarogyaNiketan.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String register(@RequestBody AuthenticationRegisterRequest authenticationRequest) throws Exception {
        User user = new User();
        user.setUserName(authenticationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        user.setRoles(authenticationRequest.getUserType().toString());
        user.setActive(Boolean.TRUE);
        User savedUser = userRepository.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getUserName());
        return jwtTokenUtil.generateToken(userDetails);
    }


    public String login(@RequestBody AuthenticationLoginRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }
}
