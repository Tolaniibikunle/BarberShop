package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.entity.ServiceOffered;
import com.ardmore.quarters.gentlemens.repository.ServicesOfferedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesOfferedService {

    private ServicesOfferedRepository repository;

    public ServicesOfferedService(ServicesOfferedRepository servicesOfferedRepository) {
        this.repository = servicesOfferedRepository;
    }

    public void createNewService(ServiceOffered serviceOffered) {
        repository.save(serviceOffered);
    }

    public List<ServiceOffered> getAllServices() {
        return repository.findAll();
    }

}
