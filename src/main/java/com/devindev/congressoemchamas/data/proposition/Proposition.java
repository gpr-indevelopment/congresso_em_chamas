package com.devindev.congressoemchamas.data.proposition;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;

@Getter
@Setter
public class Proposition {

    @Id
    private Long id;

    // TODO: 4/4/2020 Change this to an enum or database data
    private String typeDescription;

    private String title;

    private String link;

    @JsonBackReference
    private Politician politicians;
}
