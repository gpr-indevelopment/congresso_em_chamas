package com.gprindevelopment.cec.core.externalapi.google;

import com.gprindevelopment.cec.core.politician.News;
import com.gprindevelopment.cec.core.restclient.RequestsSender;
import com.gprindevelopment.cec.core.util.TestUtils;
import com.gprindevelopment.cec.core.externalapi.google.functions.GetNewsByKeyword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GoogleNewsAPITest {

    @InjectMocks
    private GoogleNewsAPI googleNewsAPI;

    @Mock
    private GoogleNewsConfig googleNewsConfig;

    @Mock
    private RequestsSender requestsSender;

    @Captor
    private ArgumentCaptor<URI> uriCaptor;

    @Test
    public void requestNews_validKeywordAndApiEnabled_buildsRequestSuccessfully() throws IOException {
        // given
        String keyword = "Eduardo Bolsonaro";
        News news1 = TestUtils.generateRandomNews();
        News news2 = TestUtils.generateRandomNews();
        // when
        when(googleNewsConfig.isGoogleNewsEnabled()).thenReturn(true);
        when(requestsSender.sendRequest(uriCaptor.capture(), any(GetNewsByKeyword.class))).thenReturn(Arrays.asList(news1, news2));
        // then
        List<News> actualOutput = googleNewsAPI.requestNews(keyword);
        assertThat(actualOutput).containsExactly(news1, news2);
        assertThat(uriCaptor.getValue().toString()).endsWith("https:?apiKey&language=pt&q=Deputado+Eduardo+Bolsonaro&sortBy=publishedAt");
        verify(googleNewsConfig).isGoogleNewsEnabled();
        verify(requestsSender).sendRequest(uriCaptor.capture(), any(GetNewsByKeyword.class));
    }

    @Test
    public void requestNews_validKeywordAndApiDisabled_ReturnsEmptyList() throws IOException {
        // given
        String keyword = "Eduardo Bolsonaro";
        // when
        when(googleNewsConfig.isGoogleNewsEnabled()).thenReturn(false);
        // then
        List<News> actualOutput = googleNewsAPI.requestNews(keyword);
        assertThat(actualOutput).isEmpty();
        verify(googleNewsConfig).isGoogleNewsEnabled();
        verify(requestsSender, never()).sendRequest(uriCaptor.capture(), any(GetNewsByKeyword.class));
    }
}
