package com.devindev.congressoemchamas.data.profile;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter @Setter
public class Profile {

    @Id
    private Long id;

    private String name;

    private String party;

    private String picture;

    private Long legislatureId;
    
}
