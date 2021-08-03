package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.ServiceRepository;
import org.capgemini.aarogyaNiketan.dto.request.ServicesPatchRequest;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.ServiceService;
import org.capgemini.aarogyaNiketan.util.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    UserHandler userHandler;

    @Override
    public Services update(ServicesPatchRequest servicesPatchRequest) throws Exception {
        Optional<Services> servicesOptional = serviceRepository.findById(servicesPatchRequest.getId());
        if (!servicesOptional.isPresent()) {
            throw new Exception("Data not found");
        }
        Services service = servicesOptional.get();
        BeanUtils.copyProperties(servicesPatchRequest, service);
        return serviceRepository.save(service);
    }
}
