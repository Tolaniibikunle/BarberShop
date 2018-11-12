package com.ardmore.quarters.gentlemens.controller;

import com.ardmore.quarters.gentlemens.config.swagger.Swaggerize;
import com.ardmore.quarters.gentlemens.dto.LoginDTO;
import com.ardmore.quarters.gentlemens.dto.UserDTO;
import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.exception.EmailExistsException;
import com.ardmore.quarters.gentlemens.exception.InvalidTokenException;
import com.ardmore.quarters.gentlemens.exception.UserAlreadyExistsException;
import com.ardmore.quarters.gentlemens.service.IAuthenticationIdentifierService;
import com.ardmore.quarters.gentlemens.service.IUserService;
import com.ardmore.quarters.gentlemens.service.registration.OnRegistrationCompleteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController("/auth")
@Swaggerize
public class AuthenticationController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private IAuthenticationIdentifierService authenticationIdentifierService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws EmailExistsException {
        User user = userService.registerNewUserAccount(userDTO);
        if (user != null) {
            String appUrl = appUrl(request);
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), appUrl));
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<User> confirmRegistration(@RequestParam("token") String token) {
        final String result = userService.validateVerificationToken(token);
        if (result.equals("valid")) {
            final User user = userService.getUser(token);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            LOGGER.error("Token Invalid: Token={} | TokenStatus={}", token, result);
            throw new InvalidTokenException("Token Could Not Be Validated");
        }
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


    private String appUrl(HttpServletRequest request) {
        return "https://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

}
