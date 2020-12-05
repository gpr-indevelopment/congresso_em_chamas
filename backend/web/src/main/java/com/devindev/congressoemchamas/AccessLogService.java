package com.devindev.congressoemchamas;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccessLogService {

    private final AccessLogRepository accessLogRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogService.class);

    public void logAccess(ServletRequest servletRequest, Long processingTimeMillis) {
        try {
            AccessLog accessLog = new AccessLog();
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            accessLog.setMethod(HttpMethod.valueOf(req.getMethod()));
            accessLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
            accessLog.setEndpoint(buildEndpointString(req));
            accessLog.setProcessingTimeMillis(processingTimeMillis);
            accessLogRepository.save(accessLog);
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
