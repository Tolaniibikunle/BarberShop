package com.ardmore.quarters.gentlemens.service.authentication;

import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;

public interface IAuthenticationIdentifierService {

    User createNewUser(UserDTO userDTO, Boolean isAdmin);

    User loginUser(String email, String password);

    void deleteUserByUserId(Long id);

}
