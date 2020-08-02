package com.devindev.congressoemchamas.data.updater;

import com.devindev.congressoemchamas.TestUtils;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.politician.PoliticianDAO;
import com.devindev.congressoemchamas.data.profile.Profile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataUpdaterManagerTest {

    @InjectMocks
    private DataUpdaterManager dataUpdaterManager;

    @Mock
    private PoliticianDAO dao;

    @Mock
    private DataUpdaterConfig dataUpdaterConfig;

    @Mock
    private DataUpdaterAPI dataUpdaterAPI;

    @Mock
    private DataUpdater dataUpdater;

    @Test
    public void updatePolitician_validPoliticianAndLegislature_managesUpdateSuccessfully() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        Legislature leg = TestUtils.generateRandomLegislature();
        // when
        when(dataUpdaterAPI.requestPoliticianById(1L)).thenReturn(pol);
        doNothing().when(dataUpdater).updateExpenses(pol, leg);
        doNothing().when(dataUpdater).updatePropositions(pol);
        doNothing().when(dataUpdater).updateTwitterUsername(pol);
        when(dao.save(pol)).thenReturn(TestUtils.generateRandomPolitician());
        // then
        Politician returnedPol = dataUpdaterManager.updatePolitician(1L, leg);
        assertThat(returnedPol).isNotEqualTo(pol);
        verify(dataUpdater).updateExpenses(pol, leg);
        verify(dataUpdater).updatePropositions(pol);
        verify(dataUpdater).updateTwitterUsername(pol);
    }

    @Test
    public void updatePolitician_validPoliticianAndNullLegislature_managesUpdateSuccessfully() {
        // given
        Politician pol = TestUtils.generateRandomPolitician();
        Legislature leg = TestUtils.generateRandomLegislature();
        // when
        when(dataUpdaterAPI.requestCurrentLegislature()).thenReturn(leg);
        when(dataUpdaterAPI.requestPoliticianById(1L)).thenReturn(pol);
        doNothing().when(dataUpdater).updateExpenses(pol, leg);
        doNothing().when(dataUpdater).updatePropositions(pol);
        doNothing().when(dataUpdater).updateTwitterUsername(pol);
        when(dao.save(pol)).thenReturn(TestUtils.generateRandomPolitician());
        // then
        Politician returnedPol = dataUpdaterManager.updatePolitician(1L, null);
        assertThat(returnedPol).isNotEqualTo(pol);
        verify(dataUpdater).updateExpenses(pol, leg);
        verify(dataUpdater).updatePropositions(pol);
        verify(dataUpdater).updateTwitterUsername(pol);
    }

    @Test
    public void scheduledInit_noPoliticiansExistInTheDb_updatesSuccessfully() {
        // given
        Legislature leg = TestUtils.generateRandomLegislature();
        Profile prof1 = TestUtils.generateRandomProfile();
        Profile prof2 = TestUtils.generateRandomProfile();
        Politician pol1 = TestUtils.generateRandomPolitician();
        Politician pol2 = TestUtils.generateRandomPolitician();
        // when
        when(dataUpdaterAPI.requestCurrentLegislature()).thenReturn(leg);
        when(dataUpdaterAPI.requestProfilesByNameAndLegislatureId(null, leg.getId()))
                .thenReturn(Arrays.asList(prof1, prof2));
        when(dao.findById(prof1.getId())).thenReturn(Optional.empty());
        when(dao.findById(prof2.getId())).thenReturn(Optional.empty());
        when(dataUpdaterAPI.requestPoliticianById(prof1.getId())).thenReturn(pol1);
        when(dataUpdaterAPI.requestPoliticianById(prof2.getId())).thenReturn(pol2);
        doNothing().when(dataUpdater).updateExpenses(pol1, leg);
        doNothing().when(dataUpdater).updatePropositions(pol1);
        doNothing().when(dataUpdater).updateTwitterUsername(pol1);
        doNothing().when(dataUpdater).updateExpenses(pol2, leg);
        doNothing().when(dataUpdater).updatePropositions(pol2);
        doNothing().when(dataUpdater).updateTwitterUsername(pol2);
        when(dao.save(pol1)).thenReturn(TestUtils.generateRandomPolitician());
        when(dao.save(pol2)).thenReturn(TestUtils.generateRandomPolitician());
        // then
        dataUpdaterManager.scheduledInit();
        verify(dataUpdater, times(0)).eligibleForUpdate(any(Politician.class));
        verify(dataUpdater, times(2)).updateExpenses(any(Politician.class), any(Legislature.class));
        verify(dataUpdater, times(2)).updatePropositions(any(Politician.class));
        verify(dataUpdater, times(2)).updateTwitterUsername(any(Politician.class));
        verify(dao, times(2)).save(any(Politician.class));
    }

    @Test
    public void scheduledInit_allExistsInDbnoPoliticiansAreEligible_noUptades() {
        // given
        Legislature leg = TestUtils.generateRandomLegislature();
        Profile prof1 = TestUtils.generateRandomProfile();
        Profile prof2 = TestUtils.generateRandomProfile();
        Politician pol1 = TestUtils.generateRandomPolitician();
        Politician pol2 = TestUtils.generateRandomPolitician();
        // when
        when(dataUpdaterAPI.requestCurrentLegislature()).thenReturn(leg);
        when(dataUpdaterAPI.requestProfilesByNameAndLegislatureId(null, leg.getId()))
                .thenReturn(Arrays.asList(prof1, prof2));
        when(dao.findById(prof1.getId())).thenReturn(Optional.of(pol1));
        when(dao.findById(prof2.getId())).thenReturn(Optional.of(pol2));
        when(dataUpdater.eligibleForUpdate(pol1)).thenReturn(false);
        when(dataUpdater.eligibleForUpdate(pol2)).thenReturn(false);
        // then
        dataUpdaterManager.scheduledInit();
        verify(dataUpdater, times(0)).updateExpenses(any(Politician.class), any(Legislature.class));
        verify(dataUpdater, times(0)).updatePropositions(any(Politician.class));
        verify(dataUpdater, times(0)).updateTwitterUsername(any(Politician.class));
        verify(dao, times(0)).save(any(Politician.class));
        verify(dataUpdater, times(2)).eligibleForUpdate(any(Politician.class));
    }
}
