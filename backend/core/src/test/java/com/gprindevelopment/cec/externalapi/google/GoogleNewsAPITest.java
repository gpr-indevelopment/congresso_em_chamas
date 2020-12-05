package com.gprindevelopment.cec.externalapi.google;

import com.gprindevelopment.cec.politician.News;
import com.gprindevelopment.cec.restclient.RequestsSender;
import com.gprindevelopment.cec.util.TestUtils;
import com.gprindevelopment.cec.externalapi.google.functions.GetNewsByKeyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
    public void requestNews_validKeyword_buildsRequestSuccessfully() throws IOException {
        // given
        String keyword = "Eduardo Bolsonaro";
        News news1 = TestUtils.generateRandomNews();
        News news2 = TestUtils.generateRandomNews();
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(GetNewsByKeyword.class))).thenReturn(Arrays.asList(news1, news2));
        // then
        List<News> actualOutput = googleNewsAPI.requestNews(keyword);
        assertThat(actualOutput).containsExactly(news1, news2);
        assertThat(uriCaptor.getValue().toString()).endsWith("https:?apiKey&language=pt&q=Deputado+Eduardo+Bolsonaro&sortBy=publishedAt");
    }
}
