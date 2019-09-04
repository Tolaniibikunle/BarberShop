package com.ardmore.quarters.gentlemens.repository;

import com.ardmore.quarters.gentlemens.entity.PasswordResetToken;
import com.ardmore.quarters.gentlemens.entity.User;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

  PasswordResetToken findByToken(String token);

  PasswordResetToken findByUser(User user);

  Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

  void deleteByExpiryDateLessThan(Date now);

  @Modifying
  @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
  void deleteAllExpiredSince(Date now);

}
