package com.ardmore.quarters.gentlemens.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity(name = "service")
@Table(name = "service")
public class ServiceOffered {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "service_cost")
    private BigDecimal cost;

    @Column(name = "barber_id")
    private long barberId;

    @Column(name = "service_length")
    private String length;

    @Column(name = "service_name")
    private String name;

}
