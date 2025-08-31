package com.munaf.A31_TASK_SCHEDULING.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class TaskSchedulingConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5); // Adjust the pool size as needed
        scheduler.setThreadNamePrefix("CustomTaskScheduler-"); // Optional: Set a custom thread name prefix
        scheduler.initialize(); // Initialize the scheduler
        return scheduler;
    }

}
