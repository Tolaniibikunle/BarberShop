package com.ardmore.quarters.gentlemens.config.schedule;

import com.ardmore.quarters.gentlemens.dao.IVerificationTokenDAO;
import com.ardmore.quarters.gentlemens.service.AccountDeletionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private static final long DAILY = 24 * 60 * 60 * 1000L;

    @Autowired
    IVerificationTokenDAO verificationTokenDAO;

    @Autowired
    AccountDeletionService accountDeletionService;

    @Scheduled(initialDelay = 0, fixedRate = DAILY)
    public void dailyVerificationTokenClean() {
        LOGGER.info("Cleaning expired verification tokens");
        verificationTokenDAO.deleteAllExpiredSince(new Date());
        LOGGER.info("Cleaning verification tokens complete");
    }

    @Scheduled(initialDelay = 0, fixedRate = DAILY)
    public void dailyAccountDeletion() {
        LOGGER.info("Deleting accounts for scheduled deletion");
        accountDeletionService.deleteAccounts();
        LOGGER.info("Account deletion complete");
    }

}
