package com.gprindevelopment.cec.api.proposition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.gprindevelopment.dominio.TramitacaoProposicao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter
public class Processing implements Comparable<Processing>{

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp timestamp;

    private Integer sequence;

    private String entityInitials;

    @Column(length = 10485760)
    private String title;

    @Column(length = 10485760)
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Proposition proposition;

    public Processing() {
    }

    public Processing(TramitacaoProposicao tramitacao) {
        this.description = tramitacao.getDespacho();
        this.sequence = tramitacao.getSequencia();
        this.timestamp = Timestamp.valueOf(tramitacao.getDataHora());
        this.title = tramitacao.getDescricaoTramitacao();
        this.entityInitials = tramitacao.getSiglaOrgao();
    }

    @Override
    public int compareTo(Processing o) {
        return -this.sequence.compareTo(o.getSequence());
    }
}
