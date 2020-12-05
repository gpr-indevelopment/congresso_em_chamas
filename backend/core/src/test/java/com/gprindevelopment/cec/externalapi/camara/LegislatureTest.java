package com.gprindevelopment.cec.externalapi.camara;

import org.junit.Test;

import java.time.YearMonth;

import static org.assertj.core.api.Assertions.assertThat;

public class LegislatureTest {

    @Test
    public void getValidityYears_validStartAndEndDate_returns4YearsOfValidity() {
        // given
        Legislature legislature = new Legislature();
        legislature.setStartDate(YearMonth.of(2019, 02));
        legislature.setEndDate(YearMonth.of(2023, 01));
        // then
        assertThat(legislature.getValidityYears()).containsExactlyInAnyOrder(2019, 2020, 2021, 2022);
    }
}
