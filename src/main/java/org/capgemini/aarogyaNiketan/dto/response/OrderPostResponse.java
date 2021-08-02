package org.capgemini.aarogyaNiketan.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderPostResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hospital_id")
    HospitalPostResponse hospital;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id")
    ServicesPostResponse services;

    Integer noOfServices;
}
