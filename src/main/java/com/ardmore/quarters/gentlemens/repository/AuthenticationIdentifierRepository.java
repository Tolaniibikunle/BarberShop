package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.AuthenticationIdentifier;
import org.springframework.data.repository.CrudRepository;

public interface AuthenticationIdentifierRepository extends CrudRepository<AuthenticationIdentifier, Long> {

    AuthenticationIdentifier findAuthenticationIdentifierByEmailAddressEquals(String email);

    void deleteByIdEquals(Long id);

}
