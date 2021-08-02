package org.capgemini.aarogyaNiketan.dto.request;

import lombok.Data;

@Data
public class HospitalPatchRequest {
    private Long id;
    private String name;
    private String location;
}
