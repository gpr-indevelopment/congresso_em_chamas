package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Politician extends Profile implements Comparable<Politician>  {

    private String twitterUsername;

    private String schooling;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "politician", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Proposition> propositions = new ArrayList<>();

    @OneToMany(mappedBy = "politician", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MonthlyExpense> monthlyExpenses = new ArrayList<>();

    @Transient
    private Set<News> news;

    @Override
    public int compareTo(Politician o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Politician){
            return this.getName().equals(((Politician) obj).getName());
        }
        else return super.equals(obj);
    }
}
