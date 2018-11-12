package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;

public interface IAuthenticationIdentifierService {

    User createNewUser(UserDTO userDTO);

    User loginUser(String email, String password);



}