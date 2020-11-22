package com.gprindevelopment.cec.externalapi.camara;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Legislature implements Serializable {

    @Id
    private Long id;

    private YearMonth startDate;

    private YearMonth endDate;

    public List<Integer> getValidityYears() {
        List<Integer> years = new ArrayList<>();
        Integer currentYear = startDate.getYear();
        while(currentYear < endDate.getYear()){
            years.add(currentYear);
            currentYear++;
        }
        return years;
    }
}
