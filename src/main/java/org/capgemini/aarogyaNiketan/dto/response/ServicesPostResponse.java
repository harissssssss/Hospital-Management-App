package org.capgemini.aarogyaNiketan.dto.response;

import lombok.Data;

@Data
public class ServicesPostResponse {
    private Long id;
    private String name;
    private Integer vacancy;
    private Integer price;
}
