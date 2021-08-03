package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.dto.request.HospitalPatchRequest;
import org.capgemini.aarogyaNiketan.dto.request.HospitalPostRequest;
import org.capgemini.aarogyaNiketan.dto.response.ApprovalsResponse;
import org.capgemini.aarogyaNiketan.model.Hospital;

import java.util.List;

public interface HospitalService {
    Hospital create(HospitalPostRequest hospital) throws Exception;
    Hospital get(Long id) throws Exception;
    Hospital update(HospitalPatchRequest hospital) throws Exception;
    List<Hospital> getAll(Long userId) throws Exception;
    List<Hospital> getAllByLocation(String location) throws Exception;
    List<ApprovalsResponse> getAllApprovals() throws Exception;
}
