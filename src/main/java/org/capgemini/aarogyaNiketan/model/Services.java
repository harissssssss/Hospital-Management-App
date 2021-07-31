package org.capgemini.aarogyaNiketan.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer vacancy;
    private Integer price;
}
