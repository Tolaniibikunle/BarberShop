package com.ardmore.quarters.gentlemens.service.registration;

import com.ardmore.quarters.gentlemens.entity.User;
import com.ardmore.quarters.gentlemens.service.MailContentBuilder;
import com.ardmore.quarters.gentlemens.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    public static final Logger LOGGER = LoggerFactory.getLogger(RegistrationListener.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Value("${barbershop.email.address}")
    private String systemEmail;

    @Value("${barbershop.email.name}")
    private String emailName;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        try {
            this.confirmRegistration(event);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Error Creating Mail Message");
        }
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) throws UnsupportedEncodingException {
        final User user = event.getUser();
        final String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        final MimeMessagePreparator mimeMessagePreparator = constructEmailMessage(event, user, token);
        javaMailSender.send(mimeMessagePreparator);
    }

    private MimeMessagePreparator constructEmailMessage(OnRegistrationCompleteEvent event, User user, String token) {
        final String recipientAddress = user.getEmail();
        final String subject = "Registration Confirmation";
        final String confirmationUrl = event.getAppUrl() + "/auth/registrationConfirm?token=" + token;
        final String message =
                "You registered successfully. Please click the below link to activate your account";
        final String userName = user.getFirstName() + " " + user.getLastName();
        MimeMessagePreparator mimeMessage = getMimeMessage(subject, message, confirmationUrl, recipientAddress, userName);
        LOGGER.info("Sending Email to : {}", recipientAddress);
        return mimeMessage;
    }

    private MimeMessagePreparator getMimeMessage(String subject, String message, String confirmationUrl, String to, String name) {
        return mimeMessage -> {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            try {
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setFrom(new InternetAddress(systemEmail, emailName));
                mimeMessageHelper.setReplyTo(systemEmail);
                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setText(message, mailContentBuilder.build(message, confirmationUrl, name));
            } catch (Exception e) {
                throw new MailPreparationException("Unable To Create Mail Message");
            }
        };
    }


}
