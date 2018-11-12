package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IUserDAO;
import com.ardmore.quarters.gentlemens.dao.IVerificationTokenDAO;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.entity.VerificationToken;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IAuthenticationIdentifierServiceImpl authenticationIdentifierService;

    @Autowired
    private IVerificationTokenDAO verificationTokenDAO;

    @Override
    public User registerNewUserAccount(UserDTO userDTO) throws UserAlreadyExistsException {
        if (emailExists(userDTO.getEmail())) {
            throw new UserAlreadyExistsException(
                    "There is an account with that email address: " + userDTO.getEmail());
        }
        return authenticationIdentifierService.createNewUser(userDTO);
    }

    private boolean emailExists(String email) {
        return userDAO.findByEmail(email) != null;
    }

    @Override
    public User getUser(String verificationToken) {
        return null;
    }

    @Override
    public void saveRegisteredUser(User user) {

    }

    @Override
    public void createVerificationToken(User user, String token) {
        final VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenDAO.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return null;
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = verificationTokenDAO.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }

        final User user = verificationToken.getUser();
        final Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpirationDate().getTime() - calendar.getTime().getTime()) <= 0) {
            verificationTokenDAO.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        userDAO.save(user);
        return "valid";
    }

}
