package org.capgemini.aarogyaNiketan.controller;

import org.capgemini.aarogyaNiketan.dto.request.ServicesPatchRequest;
import org.capgemini.aarogyaNiketan.dto.response.ServicesPostResponse;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.ServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ServiceController {

    @Autowired
    ServiceService service;

    @PatchMapping(path = "/v1/service")
    public ResponseEntity<ServicesPostResponse> edit(@RequestBody ServicesPatchRequest servicesPatchRequest) throws Exception {
        Services services = service.update(servicesPatchRequest);
        ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
        BeanUtils.copyProperties(services, servicesPostResponse);
        return new ResponseEntity<>(servicesPostResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/v1/service")
    public ResponseEntity<ServicesPostResponse> delete(@RequestBody ServicesPatchRequest servicesPatchRequest) throws Exception {
        Services services = service.update(servicesPatchRequest);
        ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
        BeanUtils.copyProperties(services, servicesPostResponse);
        return new ResponseEntity<>(servicesPostResponse, HttpStatus.OK);
    }


}
