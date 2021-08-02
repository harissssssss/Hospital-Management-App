package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;

@Data
public class OrderPostRequest {
    Long userId;
    Long serviceId;
    Long hospitalId;
    Integer noOfServices;
}
