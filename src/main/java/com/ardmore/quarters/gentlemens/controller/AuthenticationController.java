package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import com.ardmore.quarters.gentlemens.dto.LoginDTO;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;
import com.ardmore.quarters.gentlemens.repository.VerificationTokenRepository;
import com.ardmore.quarters.gentlemens.service.authentication.IAuthenticationIdentifierService;
import com.ardmore.quarters.gentlemens.service.registration.OnUserEvent;
import com.ardmore.quarters.gentlemens.service.user.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Swaggerize
public class AuthenticationController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private IAuthenticationIdentifierService authenticationIdentifierService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private VerificationTokenRepository verificationTokenDAO;

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO, @RequestParam("isAdmin") Boolean isAdmin, HttpServletRequest request) {
        User user = userService.registerNewUserAccount(userDTO, isAdmin);
        if (user != null) {
            String appUrl = appUrl(request);
            applicationEventPublisher.publishEvent(new OnUserEvent(user, request.getLocale(), appUrl));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<?> confirmRegistration(@RequestParam("token") String token) {
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
            verificationTokenDAO.deleteByTokenEquals(token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            LOGGER.error("Token Invalid: Token={} | TokenStatus={}", token, result);
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/resendRegistrationToken")
    public ResponseEntity<?> resendRegistrationToken(@RequestParam("token") String token, HttpServletRequest request) {
        User user = userService.getUser(token);
        if (user != null) {
            String appUrl = appUrl(request);
            applicationEventPublisher.publishEvent(new OnUserEvent(user, request.getLocale(), appUrl));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>("not-exist",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody LoginDTO loginDTO) {
        User user = authenticationIdentifierService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deactivateAccount")
    public ResponseEntity<User> deactivateUser(@RequestParam("id") Integer id) {
        userService.deactivateAccount(id);
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    private String appUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
