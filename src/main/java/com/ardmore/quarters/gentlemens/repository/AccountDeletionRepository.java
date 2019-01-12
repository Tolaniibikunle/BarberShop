package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.AccountDeletetion;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface AccountDeletionRepository extends CrudRepository<AccountDeletetion, Long> {

    void deleteByDeleteDateAfter(Date today);

}
