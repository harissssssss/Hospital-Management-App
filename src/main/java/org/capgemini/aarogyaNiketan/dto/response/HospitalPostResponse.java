package org.capgemini.aarogyaNiketan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HospitalPostResponse {
    private Long id;
    private String name;
    private String location;
    private String type;
    private List<ServicesPostResponse> services;
}
