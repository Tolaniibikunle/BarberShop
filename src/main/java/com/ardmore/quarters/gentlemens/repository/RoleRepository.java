package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

  Role findByName(String name);

}
