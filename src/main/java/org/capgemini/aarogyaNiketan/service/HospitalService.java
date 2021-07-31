package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;

import java.util.List;

public interface HospitalService {
    Hospital create(HospitalPostRequest hospital) throws Exception;
    Hospital get(Long id) throws Exception;
    Hospital update(Hospital hospital,Long id);
    List<Hospital> getAll(Long userId) throws Exception;
}
