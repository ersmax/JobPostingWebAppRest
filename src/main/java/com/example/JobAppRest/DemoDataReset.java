package com.example.JobAppRest;

import com.example.JobAppRest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DemoDataReset {

    @Autowired
    private JobService jobService;

    @EventListener(ApplicationReadyEvent.class) // when the app starts
    @Scheduled(cron = "0 0 * * * *")            // minute 0 of every hour
    public void reset() {
        jobService.resetDemoData();
    }

}
