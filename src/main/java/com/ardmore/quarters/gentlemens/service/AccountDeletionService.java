package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.repository.AccountDeletionRepository;
import com.ardmore.quarters.gentlemens.repository.AuthenticationIdentifierRepository;
import com.ardmore.quarters.gentlemens.repository.UserRepository;
import com.ardmore.quarters.gentlemens.entity.AccountDeletetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDeletionService {

    @Autowired
    AccountDeletionRepository accountDeletionDAO;

    @Autowired
    AuthenticationIdentifierRepository authenticationIdentifierDAO;

    @Autowired
    UserRepository userDAO;

    public void deleteAccounts() {
        List<AccountDeletetion> accountDeletions = (List<AccountDeletetion>) accountDeletionDAO.findAll();
        for (AccountDeletetion accountDeletetion : accountDeletions) {
            authenticationIdentifierDAO.deleteByIdEquals(accountDeletetion.getId());
            userDAO.deleteById(accountDeletetion.getUserId());
            accountDeletionDAO.deleteById(accountDeletetion.getId());
        }
    }


}
