package com.ardmore.quarters.gentlemens.service.user;

import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.entity.VerificationToken;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;

public interface IUserService {

    User registerNewUserAccount(UserDTO userDTO, Boolean isAdmin) throws UserAlreadyExistsException;

    User getUser(String verificationToken);

    User getUserById(Integer id);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    String validateVerificationToken(String token);

    void deactivateAccount(Integer id);

}
