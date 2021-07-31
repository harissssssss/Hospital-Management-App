package org.capgemini.aarogyaNiketan.service.imple;

import org.capgemini.aarogyaNiketan.Repository.HospitalRepository;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.dto.request.ServicesPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;
import org.capgemini.aarogyaNiketan.model.Services;
import org.capgemini.aarogyaNiketan.service.HospitalService;
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

    @Override
    public Hospital create(HospitalPostRequest hospitalPostRequest) {
        Hospital hospital = new Hospital();

        BeanUtils.copyProperties(hospitalPostRequest, hospital);
        List<Services> services = new ArrayList<>();
        for (ServicesPostRequest servicesPostRequest : hospitalPostRequest.getServices()) {
            Services.
            BeanUtils.copyProperties(servicesPostRequest, );
        }
        BeanUtils.copyProperties(hospitalPostRequest.getServices(), services);
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
}
