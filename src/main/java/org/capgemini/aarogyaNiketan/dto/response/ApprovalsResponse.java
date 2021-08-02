package org.capgemini.aarogyaNiketan.dto.response;

import lombok.Data;

@Data
public class ApprovalsResponse {
    Long id;
    Long userId;
    HospitalPostResponse hospital;
    ServicesPostResponse services;
    Boolean approve;
    Integer noOfServices;
}
