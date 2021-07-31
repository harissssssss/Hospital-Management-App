package org.capgemini.aarogyaNiketan.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class HospitalPostResponse {
    private Long id;
    private String name;
    private String location;
    private List<ServicesPostResponse> services;
}
