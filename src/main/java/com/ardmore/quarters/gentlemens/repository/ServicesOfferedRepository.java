package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.ServiceOffered;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServicesOfferedRepository extends CrudRepository<ServiceOffered, Long> {

    @Override
    List<ServiceOffered> findAll();

}
