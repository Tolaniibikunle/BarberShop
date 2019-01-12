package com.ardmore.quarters.gentlemens.service.authentication;

import com.ardmore.quarters.gentlemens.config.system.SystemConsts;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.AuthenticationIdentifier;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.repository.AuthenticationIdentifierRepository;
import com.ardmore.quarters.gentlemens.repository.RoleRepository;
import com.ardmore.quarters.gentlemens.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAuthenticationIdentifierServiceImpl implements IAuthenticationIdentifierService {

    @Autowired
    private AuthenticationIdentifierRepository authenticationIdentifierRepository;

    @Autowired
    private AuthenticationFactory authenticationFactory;

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createNewUser(UserDTO userDTO, Boolean isAdmin) {
        Long authId = saveUserAuthDetails(userDTO);
        if (authId != null) {
            return saveUserDetails(userDTO, authId, isAdmin);
        }
        return null;
    }

    private User saveUserDetails(UserDTO userDTO, Long authId, Boolean isAdmin) {
        User user = userDTO.convertToUser();
        user.setAuthId(authId);
        if (isAdmin) {
            user.setRole(roleRepository.findByName(SystemConsts.BARBER_ROLE).getName());
        } else {
            user.setRole(roleRepository.findByName(SystemConsts.CUSTOMER_ROLE).getName());
        }
        User savedUser = userDAO.save(user);
        if (savedUser.equals(user)) {
            return savedUser;
        }
        return null;
    }

    private Long saveUserAuthDetails(UserDTO userDTO) {
        AuthenticationIdentifier authenticationIdentifier = authenticationFactory.createNewAuthenticationId(userDTO.getEmail(), userDTO.getPassword());
        AuthenticationIdentifier savedAuth = authenticationIdentifierRepository.save(authenticationIdentifier);
        if (savedAuth.equals(authenticationIdentifier)) {
            return savedAuth.getId();
        }
        return null;
    }


    @Override
    public User loginUser(String email, String password) {
        AuthenticationIdentifier authenticationIdentifier = authenticationIdentifierRepository.findAuthenticationIdentifierByEmailAddressEquals(email);
        String hashEnteredPassword = authenticationFactory.hashPassword(authenticationIdentifier.getGeneratedSalt(), password);
        if (hashEnteredPassword.equals(authenticationIdentifier.getHashedPassword())) {
            return userDAO.findDistinctByAuthId(authenticationIdentifier.getId());
        }
        return null;
    }

    @Override
    public void deleteUserByUserId(Long id) {
        authenticationIdentifierRepository.deleteByIdEquals(id);
    }


}
