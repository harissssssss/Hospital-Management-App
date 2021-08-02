package org.capgemini.aarogyaNiketan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServicesPostResponse {
    private Long id;
    private String name;
    private Integer vacancy;
    private Integer price;
}
