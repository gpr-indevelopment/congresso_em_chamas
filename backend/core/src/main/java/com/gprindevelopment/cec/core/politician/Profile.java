package com.gprindevelopment.cec.core.politician;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public class Profile {

    @Id
    private Long id;

    private String name;

    private String party;

    private String picture;

    private Long legislatureId;

    private String stateInitials;

}
