package org.capgemini.aarogyaNiketan.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long userId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hospital_id")
    Hospital hospital;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "service_id")
    Services services;

    Integer noOfServices;
}
