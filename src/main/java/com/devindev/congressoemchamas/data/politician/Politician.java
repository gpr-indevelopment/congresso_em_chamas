package com.devindev.congressoemchamas.data.politician;

import com.devindev.congressoemchamas.data.news.News;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Politician implements Comparable<Politician> {

    @Id
    private Long id;

    private String name;

    private String picture;

    private String party;

    private Long legislatureId;

    private String twitterUsername;

    @OneToMany(mappedBy = "politician", cascade = CascadeType.ALL)
    @JsonManagedReference
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
