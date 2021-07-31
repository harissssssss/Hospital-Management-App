package org.capgemini.aarogyaNiketan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    @PostMapping(path = "/v1/patient/register")
    public ResponseEntity<String> register(){

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @PostMapping(path = "/v1/patient/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
