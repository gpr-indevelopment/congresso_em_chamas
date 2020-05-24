package com.devindev.congressoemchamas.data.processing;

import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Processing implements Comparable<Processing>{

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp timestamp;

    private Integer sequence;

    private String entityInitials;

    @Column(length = 10485760)
    private String title;

    @Column(length = 10485760)
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Proposition proposition;

    @Override
    public int compareTo(Processing o) {
        return -this.sequence.compareTo(o.getSequence());
    }
}
