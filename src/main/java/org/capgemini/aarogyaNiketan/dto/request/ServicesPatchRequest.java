package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;

@Data
public class ServicesPatchRequest {
    private Long id;
    private String name;
    private Integer vacancy;
    private Integer price;
}
