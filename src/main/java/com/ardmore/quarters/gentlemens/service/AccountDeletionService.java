package com.ardmore.quarters.gentlemens.service;

import com.ardmore.quarters.gentlemens.dao.IAccountDeletionDAO;
import com.ardmore.quarters.gentlemens.dao.IAuthenticationIdentifierDAO;
import com.ardmore.quarters.gentlemens.dao.IUserDAO;
import com.ardmore.quarters.gentlemens.entity.AccountDeletetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountDeletionService {

    @Autowired
    IAccountDeletionDAO accountDeletionDAO;

    @Autowired
    IAuthenticationIdentifierDAO authenticationIdentifierDAO;

    @Autowired
    IUserDAO userDAO;

    public void deleteAccounts() {
        List<AccountDeletetion> accountDeletions = (List<AccountDeletetion>) accountDeletionDAO.findAll();
        for (AccountDeletetion accountDeletetion : accountDeletions) {
            authenticationIdentifierDAO.deleteByIdEquals(accountDeletetion.getId());
            userDAO.deleteById(accountDeletetion.getUserId());
            accountDeletionDAO.deleteById(accountDeletetion.getId());
        }
    }


}
