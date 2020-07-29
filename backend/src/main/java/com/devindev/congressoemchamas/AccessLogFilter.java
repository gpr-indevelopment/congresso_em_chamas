package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.accesslog.AccessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

@Component
public class AccessLogFilter implements Filter {

    @Autowired
    private MainRepository mainRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessLogFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        try {
            logAccess(servletRequest, stopWatch.getLastTaskTimeMillis());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error(e.getStackTrace()[0].toString());
            LOGGER.error("An error was found while trying to save an access log. Skipping...");
        }
    }

    private void logAccess(ServletRequest servletRequest, Long processingTimeMillis) {
        AccessLog accessLog = new AccessLog();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        accessLog.setMethod(HttpMethod.valueOf(req.getMethod()));
        accessLog.setTimestamp(new Timestamp(System.currentTimeMillis()));
        accessLog.setEndpoint(buildEndpointString(req));
        accessLog.setProcessingTimeMillis(processingTimeMillis);
        mainRepository.saveAccessLog(accessLog);
    }

    private String buildEndpointString(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        sb.append(req.getRequestURI());
        if (Objects.nonNull(req.getQueryString())) {
            sb.append("?").append(req.getQueryString());
        }
        return sb.toString();
    }
}
