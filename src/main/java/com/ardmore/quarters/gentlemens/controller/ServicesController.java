package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import com.ardmore.quarters.gentlemens.entity.ServiceOffered;
import com.ardmore.quarters.gentlemens.service.ServicesOfferedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
@Swaggerize
public class ServicesController {

    private ServicesOfferedService offeredService;

    public ServicesController(ServicesOfferedService servicesOfferedService) {
        this.offeredService = servicesOfferedService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewService(@RequestBody ServiceOffered serviceOffered) {
        offeredService.createNewService(serviceOffered);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<?> getAllServices() {
        return new ResponseEntity<>(offeredService.getAllServices(), HttpStatus.OK);
    }

}
