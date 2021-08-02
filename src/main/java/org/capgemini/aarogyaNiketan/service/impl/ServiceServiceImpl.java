package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.ServiceRepository;
import org.capgemini.aarogyaNiketan.dto.request.ServicesPatchRequest;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.ServiceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public Services update(ServicesPatchRequest servicesPatchRequest) {
        Optional<Services> servicesOptional = serviceRepository.findById(servicesPatchRequest.getId());
        if(servicesOptional.isPresent()){
            Services service = servicesOptional.get();
            BeanUtils.copyProperties(servicesPatchRequest, service);
            return serviceRepository.save(service);
        }
        return null;
    }
}
