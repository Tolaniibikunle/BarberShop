package com.ardmore.quarters.gentlemens.config.schedule;

import com.ardmore.quarters.gentlemens.dao.IVerificationTokenDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    IVerificationTokenDAO verificationTokenDAO;

    @Scheduled(initialDelay = 0, fixedRate = 24 * 60 * 60 * 1000)
    public void dailyVerificationTokenClean() {
        LOGGER.info("Cleaning expired verification tokens");
        verificationTokenDAO.deleteAllExpiredSince(new Date());
        LOGGER.info("Cleaning verification tokens complete");
    }

}
