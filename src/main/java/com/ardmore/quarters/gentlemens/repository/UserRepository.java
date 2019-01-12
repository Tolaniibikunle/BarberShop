package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findDistinctByAuthId(Long authId);

    User findByEmail(String email);

}
