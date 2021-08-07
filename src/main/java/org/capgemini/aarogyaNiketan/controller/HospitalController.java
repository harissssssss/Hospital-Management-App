package org.capgemini.aarogyaNiketan.controller;

import org.capgemini.aarogyaNiketan.dto.request.HospitalPatchRequest;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.dto.response.ApprovalsResponse;
import org.capgemini.aarogyaNiketan.dto.response.HospitalPostResponse;
import org.capgemini.aarogyaNiketan.dto.response.ServicesPostResponse;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.HospitalService;
import org.capgemini.aarogyaNiketan.util.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private UserHandler userHandler;

    @PostMapping(path = "/v1/hospital")
    public ResponseEntity<HospitalPostResponse> create(@RequestBody HospitalPostRequest hospitalPostRequest) throws Exception {
        Hospital hospital = hospitalService.create(hospitalPostRequest);
        HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
        BeanUtils.copyProperties(hospital, hospitalPostResponse);
        List<ServicesPostResponse> servicesPostResponses = new ArrayList<>();
        for (Services services : hospital.getServices()) {
            ServicesPostResponse s = new ServicesPostResponse();
            BeanUtils.copyProperties(services, s);
            servicesPostResponses.add(s);
        }
        hospitalPostResponse.setServices(servicesPostResponses);
        return new ResponseEntity<>(hospitalPostResponse, HttpStatus.OK);
    }

    @PatchMapping(path = "/v1/hospital")
    public ResponseEntity<HospitalPostResponse> edit(@RequestBody HospitalPatchRequest hospitalPatchRequest) throws Exception {
        Hospital hospital = hospitalService.update(hospitalPatchRequest);
        HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
        BeanUtils.copyProperties(hospital, hospitalPostResponse);
        List<ServicesPostResponse> servicesPostResponses = new ArrayList<>();
        for (Services services : hospital.getServices()) {
            ServicesPostResponse s = new ServicesPostResponse();
            BeanUtils.copyProperties(services, s);
            servicesPostResponses.add(s);
        }
        hospitalPostResponse.setServices(servicesPostResponses);
        return new ResponseEntity<>(hospitalPostResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/hospital/{hospitalId}")
    public ResponseEntity<HospitalPostResponse> get(@PathVariable Long hospitalId) throws Exception {
        Hospital hospital = hospitalService.get(hospitalId);
        HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
        BeanUtils.copyProperties(hospital, hospitalPostResponse);
        List<ServicesPostResponse> servicesPostResponses = new ArrayList<>();
        for (Services services : hospital.getServices()) {
            ServicesPostResponse s = new ServicesPostResponse();
            BeanUtils.copyProperties(services, s);
            servicesPostResponses.add(s);
        }
        hospitalPostResponse.setServices(servicesPostResponses);
        return new ResponseEntity<>(hospitalPostResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/hospital")
    public ResponseEntity<List<Hospital>> getAll() throws Exception {
        Long userId = userHandler.getLoggedInUser().getId();

        List<Hospital> hospital = new ArrayList<>();

        if (userId != null) {
            hospital = hospitalService.getAll(userId);
        }

        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/hospital/location")
    public ResponseEntity<List<Hospital>> getAllByLocation(
            @RequestParam String location) throws Exception {

        List<Hospital> hospital = new ArrayList<>();

        if (location != null) {
            hospital = hospitalService.getAllByLocation(location);
        }

        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/hospital/approvals")
    public ResponseEntity<List<ApprovalsResponse>> getAllApprovals() throws Exception {
        List<ApprovalsResponse> approvals = hospitalService.getAllApprovals();
        return new ResponseEntity<>(approvals, HttpStatus.OK);
    }
}
