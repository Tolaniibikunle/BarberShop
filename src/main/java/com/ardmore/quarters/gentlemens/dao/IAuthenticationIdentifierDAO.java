package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.AuthenticationIdentifier;
import org.springframework.data.repository.CrudRepository;

public interface IAuthenticationIdentifierDAO extends CrudRepository<AuthenticationIdentifier, Long> {

    AuthenticationIdentifier findAuthenticationIdentifierByEmailAddressEquals(String email);

}
