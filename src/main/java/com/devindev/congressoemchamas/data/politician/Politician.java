package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Politician extends Profile implements Comparable<Politician>  {

    private String twitterUsername;

    private String schooling;

    @Transient
    private List<Proposition> propositions = new ArrayList<>();

    @Transient
    private List<MonthlyExpense> monthlyExpens;

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
