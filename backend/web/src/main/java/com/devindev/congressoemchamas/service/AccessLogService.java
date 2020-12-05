package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.data.accesslog.AccessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Objects;

@Service
public class AccessLogService {

    @Autowired
    private MainRepository mainRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogService.class);

    public void logAccess(ServletRequest servletRequest, Long processingTimeMillis) {
        try {
            AccessLog accessLog = new AccessLog();
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            accessLog.setMethod(HttpMethod.valueOf(req.getMethod()));
            accessLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
            accessLog.setEndpoint(buildEndpointString(req));
            accessLog.setProcessingTimeMillis(processingTimeMillis);
            mainRepository.saveAccessLog(accessLog);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error was found while trying to save an access log. Skipping...");
        }
    }

    String buildEndpointString(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(req.getRequestURI());
        if (Objects.nonNull(req.getQueryString())) {
            sb.append("?").append(req.getQueryString());
        }
        return sb.toString();
    }
}
