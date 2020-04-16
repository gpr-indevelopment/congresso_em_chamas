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
    private Timestamp requestTimestamp;
}
