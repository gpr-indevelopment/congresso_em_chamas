package com.devindev.congressoemchamas.data.proposition;

import com.devindev.congressoemchamas.data.politician.Politician;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Proposition {

    @Id
    private Long id;

    // TODO: 4/4/2020 Change this to an enum or database data
    private String propositionType;

    private String title;

    @ManyToMany(mappedBy = "propositions")
    private List<Politician> politicians = new ArrayList<>();

    public Proposition() {
    }
}
