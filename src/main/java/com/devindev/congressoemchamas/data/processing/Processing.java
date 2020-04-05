package com.devindev.congressoemchamas.data.processing;

import com.devindev.congressoemchamas.data.politician.Politician;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Processing implements Comparable<Processing>{

    private Timestamp timestamp;

    private Integer sequence;

    private String entityInitials;

    private String title;

    private String description;

    @Override
    public int compareTo(Processing o) {
        return -this.sequence.compareTo(o.getSequence());
    }
}
