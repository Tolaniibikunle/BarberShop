package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.VerificationToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface VerificationTokenRepository extends CrudRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String token);

    @Modifying
    @Query(value = "delete from verification_token as t where t.expiration_date <= ?1", nativeQuery = true)
    @Transactional
    void deleteAllExpiredSince(Date now);

    @Modifying
    @Transactional
    void deleteByTokenEquals(String token);

}
