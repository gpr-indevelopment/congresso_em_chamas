package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Politician extends Profile implements Comparable<Politician>  {

    private String twitterUsername;

    private String schooling;

    @OneToMany(mappedBy = "politician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<News> news = new ArrayList<>();

    @Transient
    @JsonManagedReference
    private List<Proposition> propositions = new ArrayList<>();

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
