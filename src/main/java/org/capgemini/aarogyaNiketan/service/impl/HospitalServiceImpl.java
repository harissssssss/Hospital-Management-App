package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.HospitalRepository;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.dto.request.ServicesPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.model.User;
import org.capgemini.aarogyaNiketan.service.HospitalService;
import org.capgemini.aarogyaNiketan.util.UserHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    UserHandler userHandler;

    @Override
    public Hospital create(HospitalPostRequest hospitalPostRequest) throws Exception {
        User user = userHandler.getLoggedInUser();
        if (user == null || user.getId() == null){
            throw new Exception("Login to continue");
        }
        Hospital hospital = new Hospital();
        hospital.setUserId(user.getId());
        BeanUtils.copyProperties(hospitalPostRequest, hospital);
        List<Services> services = new ArrayList<>();
        for (ServicesPostRequest servicesPostRequest : hospitalPostRequest.getServices()) {
            Services s = new Services();
            BeanUtils.copyProperties(servicesPostRequest, s);
            services.add(s);
        }
        hospital.setServices(services);
        return hospitalRepository.save(hospital);
    }

    @Override
    public Hospital get(Long id) throws Exception {
        Optional<Hospital> hospital = hospitalRepository.findById(id);
        if (hospital.isPresent()) {
            return hospital.get();
        } else {
            throw new Exception("No such data present");
        }
    }

    @Override
    public Hospital update(Hospital hospital, Long id) {
        return null;
    }

    @Override
    public List<Hospital> getAll(Long userId) throws Exception {
        List<Hospital> hospital = hospitalRepository.findAllByUserId(userId);
        if (!hospital.isEmpty()) {
            return hospital;
        } else {
            throw new Exception("No such data present");
        }
    }
}
