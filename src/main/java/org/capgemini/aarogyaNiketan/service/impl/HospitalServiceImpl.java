package org.capgemini.aarogyaNiketan.service.impl;

import org.capgemini.aarogyaNiketan.Repository.HospitalRepository;
import org.capgemini.aarogyaNiketan.Repository.OrderRepository;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPatchRequest;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.dto.request.ServicesPostRequest;
import org.capgemini.aarogyaNiketan.dto.response.ApprovalsResponse;
import org.capgemini.aarogyaNiketan.dto.response.HospitalPostResponse;
import org.capgemini.aarogyaNiketan.dto.response.ServicesPostResponse;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Order;
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
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserHandler userHandler;

    @Override
    public Hospital create(HospitalPostRequest hospitalPostRequest) throws Exception {
        User user = userHandler.getLoggedInUser();
        if (user == null || user.getId() == null) {
            throw new Exception("Login to continue");
        }
        Hospital hospital = new Hospital();
        hospital.setUserId(user.getId());
        BeanUtils.copyProperties(hospitalPostRequest, hospital);
        List<Services> services = new ArrayList<>();
        for (ServicesPostRequest servicesPostRequest : hospitalPostRequest.getServices()) {
            Services s = new Services();
            if (servicesPostRequest.getVacancy() < 0 || servicesPostRequest.getPrice() < 0) {
                throw new Exception("Invalid details provided for the service : " + servicesPostRequest.getName());
            }
            BeanUtils.copyProperties(servicesPostRequest, s);
            services.add(s);
        }
        hospital.setServices(services);
        hospital.setLocation(hospital.getLocation().trim().toLowerCase());
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
    public Hospital update(HospitalPatchRequest hospitalPatchRequest) throws Exception {
        Optional<Hospital> hospitalOptional = hospitalRepository.findById(hospitalPatchRequest.getId());
        if (!hospitalOptional.isPresent()) {
            throw new Exception("Data not found");
        } else if (!hospitalOptional.get().getUserId().equals(userHandler.getLoggedInUser().getId())) {
            throw new Exception("Permission denied to edit");
        }
        Hospital hospital = hospitalOptional.get();
        BeanUtils.copyProperties(hospitalPatchRequest, hospital);
        return hospitalRepository.save(hospital);
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

    @Override
    public List<Hospital> getAllByLocation(String location) throws Exception {
        List<Hospital> hospital = hospitalRepository.findAllByLocation(location.trim().toLowerCase());
        if (!hospital.isEmpty()) {
            return hospital;
        } else {
            throw new Exception("No such data present");
        }
    }

    @Override
    public List<ApprovalsResponse> getAllApprovals() throws Exception {
        Long userId = userHandler.getLoggedInUser().getId();
        List<Hospital> hospital = hospitalRepository.findAllByUserId(userId);
        List<Long> hospitalId = hospital.stream().map(Hospital::getId).collect(Collectors.toList());
        List<Order> orders = orderRepository.findAllByHospitalIdInAndApprove(hospitalId, false);
        List<ApprovalsResponse> approvalsResponses = new ArrayList<>();
        for (Order o : orders) {
            ApprovalsResponse approvalsResponse = new ApprovalsResponse();

            HospitalPostResponse hospitalPostResponse = new HospitalPostResponse();
            BeanUtils.copyProperties(o.getHospital(), hospitalPostResponse);

            ServicesPostResponse servicesPostResponse = new ServicesPostResponse();
            BeanUtils.copyProperties(o.getServices(), servicesPostResponse);

            approvalsResponse.setHospital(hospitalPostResponse);
            approvalsResponse.setServices(servicesPostResponse);

            BeanUtils.copyProperties(o, approvalsResponse);

            approvalsResponses.add(approvalsResponse);
        }
        return approvalsResponses;
    }
}
