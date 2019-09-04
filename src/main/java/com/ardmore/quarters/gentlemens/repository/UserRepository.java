package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findDistinctByAuthId(Long authId);

    User findByEmail(String email);

    @Query(value = "SELECT * FROM USERS WHERE ID IN\n" +
            "    (SELECT USER_ID FROM VERIFICATION_TOKEN WHERE TOKEN = :#{#token})", nativeQuery = true)
    User findUserByToken(@Param("token") String token);

}
