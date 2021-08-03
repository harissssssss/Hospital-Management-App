package org.capgemini.aarogyaNiketan.service;

import org.capgemini.aarogyaNiketan.dto.request.ServicesPatchRequest;
import org.capgemini.aarogyaNiketan.model.Services;

public interface ServiceService {
    Services update(ServicesPatchRequest servicesPatchRequest) throws Exception;
}
