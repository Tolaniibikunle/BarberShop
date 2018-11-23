package com.ardmore.quarters.gentlemens.dao;

import com.ardmore.quarters.gentlemens.entity.AccountDeletetion;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface IAccountDeletionDAO extends CrudRepository<AccountDeletetion, Long> {

    void deleteByDeleteDateAfter(Date today);

}
