package com.devindev.congressoemchamas.externalapi.camara;

import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CamaraAPI.class, CamaraConfig.class, RequestsSender.class})
public class CamaraAPIIntegrationTest {

    @Autowired
    private CamaraAPI camaraAPI;

    @Test
    public void requestProfilesByNameAndLegislatureId_eduardoBolsonaroAnd56Legislature_returnsProfile() {
        // given
        Long legislatureId = 56L;
        String politicianName = "Eduardo Bolsonaro";
        // then
        List<Profile> outputs = camaraAPI.requestProfilesByNameAndLegislatureId(politicianName, legislatureId);
        assertThat(outputs.size()).isEqualTo(1);
        assertThat(outputs.get(0).getName()).isEqualTo("Eduardo Bolsonaro");
        assertThat(outputs.get(0).getParty()).isEqualTo("PSL");
    }

    @Test
    public void requestProfilesByNameAndLegislatureId_emptyNameAndNullLegislature_returnsAllFromCurrentLegislature() {
        // given
        String politicianName = "";
        // then
        List<Profile> outputs = camaraAPI.requestProfilesByNameAndLegislatureId(politicianName, null);
        assertThat(outputs.isEmpty()).isFalse();
    }

    @Test
    public void requestPoliticianById_92346Id_returnsEduardoBolsonaro() {
        // given
        Long politicianId = 92346L;
        // then
        Politician politician = camaraAPI.requestPoliticianById(politicianId);
        assertThat(politician.getName()).isEqualTo("Eduardo Bolsonaro");
        assertThat(politician.getParty()).isEqualTo("PSL");
        assertThat(politician.getId()).isEqualTo(politicianId);
    }

    @Test
    public void requestCurrentLegislature_noParameter_returnsLegislature() {
        // then
        Legislature legislature = camaraAPI.requestCurrentLegislature();
        assertThat(legislature.getId()).isNotNull();
        assertThat(legislature.getStartDate()).isNotNull();
        assertThat(legislature.getEndDate()).isNotNull();
    }

    @Test
    public void requestPropositionIdsByPoliticianId_92346Id_returnsPropositions() {
        // given
        Long politicianId = 92346L;
        // then
        List<Long> propositionIds = camaraAPI.requestPropositionIdsByPoliticianId(politicianId);
        assertThat(propositionIds.isEmpty()).isFalse();
    }

    @Test
    public void requestPropositionById_2258599Id_returnsProposition() {
        // given
        Long propositionId = 2258599L;
        // then
        Proposition proposition = camaraAPI.requestPropositionById(propositionId);
        assertThat(proposition).isNotNull();
        assertThat(proposition.getId()).isEqualTo(propositionId);
    }

    @Test
    public void requestAuthorsByPropositionId_2258599Id_returnsAuthors() {
        // given
        Long propositionId = 2258599L;
        // then
        List<String> authors = camaraAPI.requestAuthorsByPropositionId(propositionId);
        assertThat(authors.isEmpty()).isFalse();
        assertThat(authors).contains("Eduardo Bolsonaro");
    }

    @Test
    public void requestProcessingHistoryByPropositionId_2258599Id_returnsProcessingHistory() {
        // given
        Long propositionId = 2258599L;
        // then
        List<Processing> processings = camaraAPI.requestProcessingHistoryByPropositionId(propositionId);
        assertThat(processings.isEmpty()).isFalse();
    }

    @Test
    public void requestAllExpensesByPoliticianId_92346IdNullMonthsAndYears_returnsExpenses() {
        // given
        Long politicianId = 92346L;
        // then
        List<Expense> expenses = camaraAPI.requestAllExpensesByPoliticianId(politicianId, null, null);
        assertThat(expenses.isEmpty()).isFalse();
    }

}
