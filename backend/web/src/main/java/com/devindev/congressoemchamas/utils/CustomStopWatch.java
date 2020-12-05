package com.devindev.congressoemchamas.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

public class CustomStopWatch extends StopWatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomStopWatch.class);

    @Override
    public void start(String taskName) throws IllegalStateException {
        LOGGER.info("Starting task: {}", taskName);
        super.start(taskName);
    }

    @Override
    public void stop() throws IllegalStateException {
        String currentTaskName = currentTaskName();
        super.stop();
        LOGGER.info("Stopping task: {}. Took {} seconds", currentTaskName, getLastTaskTimeMillis() * 1.0/1000);
    }
}
