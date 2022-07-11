package com.gprindevelopment.cec.api.politician;

import io.github.gprindevelopment.dominio.Deputado;
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

    public Profile() {
    }

    public Profile(Deputado deputado) {
        this.id = (long) deputado.getId();
        this.name = deputado.getNome();
        this.party = deputado.getSiglaPartido();
        this.picture = deputado.getUrlFoto().toString();
        this.legislatureId = (long) deputado.getIdLegislatura();
        this.stateInitials = deputado.getSiglaUf().name();
    }
}
