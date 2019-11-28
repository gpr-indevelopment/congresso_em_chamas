package com.devindev.congressoemchamas.politician;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Politician {

    @Id
    private Long id;

    private String name;

    private String picture;

    private String party;

    private String twitterUsername;
}
