package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.entity.VerificationToken;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;

public interface IUserService {

    User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    String validateVerificationToken(String token);

}
