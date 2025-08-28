package com.learning.SpringBootSelf.scheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/mail")
public class EmailScheduleController {

    @Autowired
    Scheduler scheduler;

    @GetMapping
    public void mailSchedule() throws SchedulerException {

        JobDetail jobDetail= buildJobDetail();
        Trigger trigger = buildTrigger(jobDetail,
                ZonedDateTime.of(2025,8,29,4,54,9,0, ZoneId.of("Asia/Kolkata")));

        scheduler.scheduleJob(jobDetail, trigger);
    }


    private JobDetail buildJobDetail(){
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", "kc.nitish2@gmail.com");
        jobDataMap.put("subject", "Testing; this is scheduler");
        jobDataMap.put("body", "I am mailing you beacause its your time in my schedule");

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString())
                .withDescription("JOB: Send email")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildTrigger(JobDetail jobDetail, ZonedDateTime zonedDateTime){
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-trigger")
                .startAt(Date.from(zonedDateTime.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

}
