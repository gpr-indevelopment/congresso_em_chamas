package com.devindev.congressoemchamas.data.legislature;

import lombok.Getter;
import lombok.Setter;
import java.time.YearMonth;

@Getter @Setter
public class Legislature {

    private Long id;

    private YearMonth startDate;

    private YearMonth endDate;
}
