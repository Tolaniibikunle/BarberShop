package com.ardmore.quarters.gentlemens.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "admin")
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;

}
