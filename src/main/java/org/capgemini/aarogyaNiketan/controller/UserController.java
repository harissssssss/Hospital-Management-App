package org.capgemini.aarogyaNiketan.controller;

import org.capgemini.aarogyaNiketan.dto.request.AuthenticationLoginRequest;
import org.capgemini.aarogyaNiketan.dto.request.AuthenticationRegisterRequest;
import org.capgemini.aarogyaNiketan.dto.response.AuthenticationResponse;
import org.capgemini.aarogyaNiketan.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthenticationRegisterRequest authenticationRequest) throws Exception {
        String jwt = authenticationService.register(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationLoginRequest authenticationRequest) throws Exception {
        String jwt = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
