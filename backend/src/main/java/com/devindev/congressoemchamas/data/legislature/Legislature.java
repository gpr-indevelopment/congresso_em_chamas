package com.devindev.congressoemchamas.data.legislature;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.YearMonth;

@Entity
@Getter @Setter
public class Legislature {

    @Id
    private Long id;

    private YearMonth startDate;

    private YearMonth endDate;
}
