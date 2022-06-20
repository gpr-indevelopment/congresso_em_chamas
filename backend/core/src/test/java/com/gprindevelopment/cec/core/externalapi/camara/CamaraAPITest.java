package com.gprindevelopment.cec.core.externalapi.camara;

import com.gprindevelopment.cec.core.externalapi.camara.functions.GetCurrentLegislature;
import com.gprindevelopment.cec.core.restclient.InvalidRequestException;
import com.gprindevelopment.cec.core.restclient.RequestsSender;
import com.gprindevelopment.cec.core.util.TestUtils;
import org.apache.http.client.ResponseHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CamaraAPITest {

    @InjectMocks
    private CamaraAPI camaraAPI;

    @Mock
    private RequestsSender requestsSender;

    @Captor
    private ArgumentCaptor<URI> uriCaptor;

    @Test
    public void requestProfilesByNameAndLegislatureId_validProfileNameAndLegislature_builtWithAllParameters() throws Exception{
        // given
        String name = "SampleName";
        Long legislatureId = 2L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestProfilesByNameAndLegislatureId(name, legislatureId);
        assertThat(uriCaptor.getValue().toString()).endsWith("?nome=SampleName&idLegislatura=2");
    }

    @Test
    public void requestProfilesByNameAndLegislatureId_emptyNameAndLegislature_builtWithEmptyNameAndCurrentLegislature() throws Exception{
        // given
        String name = "";
        Legislature currentLegislature = new Legislature();
        currentLegislature.setId(9L);
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        when(requestsSender.sendRequest(any(URI.class), any(GetCurrentLegislature.class))).thenReturn(currentLegislature);
        // then
        camaraAPI.requestProfilesByNameAndLegislatureId(name, null);
        assertThat(uriCaptor.getValue().toString()).endsWith("?nome=&idLegislatura=9");
    }

    @Test
    public void requestProfilesByNameAndLegislatureId_nullNameAndLegislature_builtWithNoNameAndCurrentLegislature() throws Exception{
        // given
        Legislature currentLegislature = new Legislature();
        currentLegislature.setId(9L);
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        when(requestsSender.sendRequest(any(URI.class), any(GetCurrentLegislature.class))).thenReturn(currentLegislature);
        // then
        camaraAPI.requestProfilesByNameAndLegislatureId(null, null);
        assertThat(uriCaptor.getValue().toString()).endsWith("?nome&idLegislatura=9");
    }

    @Test
    public void requestPoliticianById_validId_requestBuildWithId() throws Exception {
        // given
        Long politicianId = 5L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(TestUtils.generateRandomPolitician());
        // then
        camaraAPI.requestPoliticianById(politicianId);
        assertThat(uriCaptor.getValue().toString()).endsWith("/deputados/5");
    }

    @Test
    public void requestPoliticianById_nullId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestPoliticianById(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }

    @Test
    public void requestCurrentLegislature_noParameter_returnsCurrentLegislature() throws Exception{
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new Legislature());
        // then
        camaraAPI.requestCurrentLegislature();
        assertThat(uriCaptor.getValue().toString()).endsWith("/legislaturas?ordem=DESC&ordenarPor=id");
    }

    @Test
    public void requestPropositionIdsByPoliticianId_validId_buildsRequest() throws Exception{
        // given
        Long politicianId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestPropositionIdsByPoliticianId(politicianId);
        assertThat(uriCaptor.getValue().toString()).endsWith("?idDeputadoAutor=9&ordem=DESC&ordenarPor=id");
    }

    @Test
    public void requestPropositionIdsByPoliticianId_nullPoliticianId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestPropositionIdsByPoliticianId(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }

    @Test
    public void requestPropositionById_validPropositionId_buildsSuccessfully() throws Exception{
        // given
        Long propId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(TestUtils.generateRandomProposition());
        // then
        camaraAPI.requestPropositionById(propId);
        assertThat(uriCaptor.getValue().toString()).endsWith("/proposicoes/9");
    }

    @Test
    public void requestPropositionById_nullPropositionId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestPropositionById(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }

    @Test
    public void requestAuthorsByPropositionId_validPropositionId_buildsSuccessfully() throws Exception{
        // given
        Long propId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAuthorsByPropositionId(propId);
        assertThat(uriCaptor.getValue().toString()).endsWith("/proposicoes/9/autores");
    }

    @Test
    public void requestAuthorsByPropositionId_nullPropositionId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestAuthorsByPropositionId(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }

    @Test
    public void requestProcessingHistoryByPropositionId_validPropositionId_buildsSuccessfully() throws Exception {
        // given
        Long propositionId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestProcessingHistoryByPropositionId(propositionId);
        assertThat(uriCaptor.getValue().toString()).endsWith("/proposicoes/9/tramitacoes");
    }

    @Test
    public void requestProcessingHistoryByPropositionId_nullPropositionId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestProcessingHistoryByPropositionId(null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }

    @Test
    public void requestAllExpensesByPoliticianId_politicianIdNullMonthAndYear_buildsRequestWithoutMonthAndYear() throws Exception {
        // given
        Long politicianId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, null);
        assertThat(uriCaptor.getValue().toString()).endsWith("/9/despesas?itens=100");
    }

    @Test
    public void requestAllExpensesByPoliticianId_politicianIdAndMonthNullYear_buildsRequestWithoutYear() throws Exception {
        // given
        Long politicianId = 9L;
        List<Integer> months = Arrays.asList(1, 2, 3);
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAllExpensesByPoliticianId(politicianId, months, null);
        assertThat(uriCaptor.getValue().toString()).endsWith("/9/despesas?itens=100&mes=1&mes=2&mes=3");
    }

    @Test
    public void requestAllExpensesByPoliticianId_politicianIdAndYearNullMonth_buildsRequestWithoutMonth() throws Exception {
        // given
        Long politicianId = 9L;
        List<Integer> years = Arrays.asList(1, 2, 3);
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, years);
        assertThat(uriCaptor.getValue().toString()).endsWith("/9/despesas?itens=100&ano=1&ano=2&ano=3");
    }

    @Test
    public void requestAllExpensesByPoliticianId_politicianIdAndYearAndMonth_buildsRequestWithoutAllParameters() throws Exception {
        // given
        Long politicianId = 9L;
        List<Integer> years = Arrays.asList(1, 2, 3);
        List<Integer> months = Arrays.asList(4, 5, 6);
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAllExpensesByPoliticianId(politicianId, months, years);
        assertThat(uriCaptor.getValue().toString()).endsWith("/9/despesas?itens=100&mes=4&mes=5&mes=6&ano=1&ano=2&ano=3");
    }

    @Test
    public void requestAllExpensesByPoliticianId_politicianIdAndEmptyYearAndMonth_buildsRequestWithoutYearAndMonth() throws Exception {
        // given
        Long politicianId = 9L;
        // when
        when(requestsSender.sendRequest(uriCaptor.capture(), any(ResponseHandler.class))).thenReturn(new ArrayList<>());
        // then
        camaraAPI.requestAllExpensesByPoliticianId(politicianId, new ArrayList<>(), new ArrayList<>());
        assertThat(uriCaptor.getValue().toString()).endsWith("/9/despesas?itens=100");
    }

    @Test
    public void requestAllExpensesByPoliticianId_nullPoliticianId_throwsInvalidRequestException() {
        // then
        try {
            camaraAPI.requestAllExpensesByPoliticianId(null, null, null);
            throw new AssertionError();
        } catch (Exception e) {
            assertThat(e).isInstanceOf(InvalidRequestException.class);
        }
    }
}
