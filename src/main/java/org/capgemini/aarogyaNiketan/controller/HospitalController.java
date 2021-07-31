package org.capgemini.aarogyaNiketan.controller;

import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping(path = "/v1/hospital")
    public ResponseEntity<String> create(@RequestBody HospitalPostRequest hospitalPostRequest){
        Hospital hospital = hospitalService.create(hospitalPostRequest);

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @GetMapping(path = "/v1/hospital/{hospitalId}")
    public ResponseEntity<String> get(Long hospitalId){
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

}
