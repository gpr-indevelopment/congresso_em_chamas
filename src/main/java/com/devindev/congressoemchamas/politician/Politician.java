package com.devindev.congressoemchamas.politician;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

// TODO: 18-Mar-20 Consider removing lombok
@Entity
@Getter @Setter
public class Politician implements Comparable<Politician> {

    @Id
    private Long id;

    private String name;

    private String picture;

    private String party;

    private String twitterUsername;

    @Transient
    private List<News> news = new ArrayList<>();

    @Override
    public int compareTo(Politician o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Politician){
            return this.name.equals(((Politician) obj).getName());
        }
        else return super.equals(obj);
    }
}
