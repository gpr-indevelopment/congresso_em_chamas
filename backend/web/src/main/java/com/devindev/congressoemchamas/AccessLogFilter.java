package com.devindev.congressoemchamas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.*;
import java.io.IOException;

@Component
public class AccessLogFilter implements Filter {

    @Autowired
    private AccessLogService accessLogService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        filterChain.doFilter(servletRequest, servletResponse);
        stopWatch.stop();
        accessLogService.logAccess(servletRequest, stopWatch.getLastTaskTimeMillis());
    }
}
