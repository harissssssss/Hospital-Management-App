package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class HospitalPostRequest {
    private String name;
    private String location;
    private String type;
    private List<ServicesPostRequest> services;
}
