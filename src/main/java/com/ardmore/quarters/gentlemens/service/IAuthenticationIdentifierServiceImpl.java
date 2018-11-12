package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IAuthenticationIdentifierDAO;
import com.ardmore.quarters.gentlemens.dao.IUserDAO;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.AuthenticationIdentifier;
import com.ardmore.quarters.gentlemens.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAuthenticationIdentifierServiceImpl implements IAuthenticationIdentifierService {

    @Autowired private IAuthenticationIdentifierDAO authenticationIdentifierDAO;

    @Autowired private AuthenticationFactory authenticationFactory;

    @Autowired private IUserDAO userDAO;

    @Override
    public User createNewUser(UserDTO userDTO) {
        Long authId = saveUserAuthDetails(userDTO);
        if (authId != null) {
            return saveUserDetails(userDTO, authId);
        }
        return null;
    }

    private User saveUserDetails(UserDTO userDTO, Long authId) {
        User user = userDTO.convertToUser();
        user.setAuthId(authId);
        User savedUser = userDAO.save(user);
        if (savedUser.equals(user)) {
            return savedUser;
        }
        return null;
    }

    private Long saveUserAuthDetails(UserDTO userDTO) {
        AuthenticationIdentifier authenticationIdentifier = authenticationFactory.createNewAuthenticationId(userDTO.getEmail(), userDTO.getPassword());
        AuthenticationIdentifier savedAuth = authenticationIdentifierDAO.save(authenticationIdentifier);
        if (savedAuth.equals(authenticationIdentifier)) {
            return savedAuth.getId();
        }
        return null;
    }



    @Override
    public User loginUser(String email, String password) {
        AuthenticationIdentifier authenticationIdentifier = authenticationIdentifierDAO.findAuthenticationIdentifierByEmailAddressEquals(email);
        String hashEnteredPassword = authenticationFactory.hashPassword(authenticationIdentifier.getGeneratedSalt(), password);
        if (hashEnteredPassword.equals(authenticationIdentifier.getHashedPassword())) {
            return userDAO.findDistinctByAuthId(authenticationIdentifier.getId());
        }
        return null;
    }

}
