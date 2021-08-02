package org.capgemini.aarogyaNiketan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPostResponse {
    Long id;
    Long userId;
    HospitalPostResponse hospital;
    ServicesPostResponse services;
    Boolean approve;
    Integer noOfServices;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
