package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDAO extends CrudRepository<User, Integer> {

    User findDistinctByAuthId(Long authId);

    User findByEmail(String email);

}
