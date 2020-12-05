package com.devindev.congressoemchamas.service;

import com.devindev.congressoemchamas.data.MainRepository;
import com.devindev.congressoemchamas.AccessLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccessLogServiceTest {

    @InjectMocks
    private AccessLogService accessLogService;

    @Mock
    private MainRepository mainRepository;

    @Captor
    private ArgumentCaptor<AccessLog> accessLogCaptor;

    @Test
    public void buildEndpointString_requestWithQueryString_buildsRightFormat() {
        // given
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/login");
        req.setQueryString("testParam=1");
        // then
        String actualOutput = accessLogService.buildEndpointString(req);
        assertThat(actualOutput).isEqualTo("/login?testParam=1");
    }

    @Test
    public void buildEndpointString_requestWithTwoQueryStrings_buildsRightFormat() {
        // given
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/login");
        req.setQueryString("testParam=1&testParam2=2");
        // then
        String actualOutput = accessLogService.buildEndpointString(req);
        assertThat(actualOutput).isEqualTo("/login?testParam=1&testParam2=2");
    }

    @Test
    public void buildEndpointString_noQueryString_buildsRightFormat() {
        // given
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/login");
        // then
        String actualOutput = accessLogService.buildEndpointString(req);
        assertThat(actualOutput).isEqualTo("/login");
    }

    @Test
    public void logAccess_validRequestAndProcessingTime_savesCompleteAccessLog() {
        // given
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setMethod("GET");
        req.setRequestURI("/login");
        Long processingTimeMillis = 5L;
        // when
        when(mainRepository.saveAccessLog(accessLogCaptor.capture())).thenReturn(new AccessLog());
        // then
        accessLogService.logAccess(req, processingTimeMillis);
        AccessLog actualOutput = accessLogCaptor.getValue();
        assertThat(actualOutput.getEndpoint()).isEqualTo("/login");
        assertThat(actualOutput.getMethod()).isEqualTo(HttpMethod.GET);
        assertThat(actualOutput.getTimestamp()).isNotNull();
        assertThat(actualOutput.getProcessingTimeMillis()).isEqualTo(processingTimeMillis);
    }
}
