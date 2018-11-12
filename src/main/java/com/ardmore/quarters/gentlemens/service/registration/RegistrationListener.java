package com.ardmore.quarters.gentlemens.service.registration;

import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);

    @Autowired private UserServiceImpl userService;

    @Autowired private JavaMailSender javaMailSender;

    @Value("${}")
    private String systemEmail;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        final SimpleMailMessage simpleMailMessage = constructEmailMessage(event, user, token);
        javaMailSender.send(simpleMailMessage);
    }

    private SimpleMailMessage constructEmailMessage(OnRegistrationCompleteEvent event, User user, String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/auth/registrationConfirm?token=" + token;
        final String message =
                "You registered successfully. We will send you a confirmation message to your email account.";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + confirmationUrl);
        email.setFrom(systemEmail);
        LOGGER.info("Sending Email to : {}", recipientAddress);
        return email;
    }


}
