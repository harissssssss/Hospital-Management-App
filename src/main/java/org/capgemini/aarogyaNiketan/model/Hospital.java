package org.capgemini.aarogyaNiketan.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "Hospitals")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private Long userId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="hospital_id")
    private List<Services> services;
}
