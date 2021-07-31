package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.model.Hospital;

public interface HospitalService {
    Hospital create(HospitalPostRequest hospital);
    Hospital get(Long id) throws Exception;
    Hospital update(Hospital hospital,Long id);
}
