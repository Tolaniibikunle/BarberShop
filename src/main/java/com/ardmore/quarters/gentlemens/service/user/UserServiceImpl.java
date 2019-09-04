package com.ardmore.quarters.gentlemens.service.user;

import com.ardmore.quarters.gentlemens.repository.AccountDeletionRepository;
import com.ardmore.quarters.gentlemens.repository.UserRepository;
import com.ardmore.quarters.gentlemens.repository.VerificationTokenRepository;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.AccountDeletetion;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.entity.VerificationToken;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;
import com.ardmore.quarters.gentlemens.service.authentication.IAuthenticationIdentifierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userDAO;

    @Autowired
    private IAuthenticationIdentifierServiceImpl authenticationIdentifierService;

    @Autowired
    private VerificationTokenRepository verificationTokenDAO;

    @Autowired
    private AccountDeletionRepository accountDeletionDAO;

    @Override
    public User registerNewUserAccount(UserDTO userDTO, Boolean isAdmin) throws UserAlreadyExistsException {
        if (emailExists(userDTO.getEmail())) {
            throw new UserAlreadyExistsException(
                    "There is an account with that email address: " + userDTO.getEmail());
        }
        return authenticationIdentifierService.createNewUser(userDTO, isAdmin);
    }

    private boolean emailExists(String email) {
        return userDAO.findByEmail(email) != null;
    }

    @Override
    public User getUser(String verificationToken) {
        return userDAO.findUserByToken(verificationToken);
    }

    @Override
    public User getUserById(Integer id) {
        Optional<User> optionalUser = userDAO.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else return null;
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

    @Override
    public void deactivateAccount(Integer id) {
        Optional<User> user = userDAO.findById(id);
        if (user.isPresent()) {
            user.get().setEnabled(false);
            userDAO.save(user.get());
            accountDeletionDAO.save(new AccountDeletetion(user.get().getUserId(), user.get().getAuthId()));
        }
    }

}
