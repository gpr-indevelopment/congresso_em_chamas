package com.devindev.congressoemchamas.data.news;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POLITICIAN_ID")
    @JsonBackReference
    private Politician politician;

    private String title;

    private String link;

    private String description;

    private String dateString;

    // TODO: 4/4/2020 persiste news media outlet with its toString(), and not ordinal.
    private NewsMediaOutlet mediaOutlet;

    // TODO: 01-Apr-20 Is this the right way of persisting a timestamp? Should I include timezone?
    private Timestamp timestamp;

    public News(String title, String link, String description, String dateString, NewsMediaOutlet mediaOutlet) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.dateString = dateString;
        this.mediaOutlet = mediaOutlet;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }

    public News() {
    }

    public void update(News news){
        this.title = news.title;
        this.link = news.link;
        this.description = news.description;
        this.dateString = news.dateString;
        this.mediaOutlet = news.mediaOutlet;
        this.timestamp = Timestamp.valueOf(LocalDateTime.now());
    }
}
