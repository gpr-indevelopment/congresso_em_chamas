package com.gprindevelopment.cec.core.proposition;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gprindevelopment.cec.core.politician.Politician;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
public class Proposition {

    @Id
    @GeneratedValue
    private Long id;

    private String typeDescription;

    @Column(length = 10485760)
    private String title;

    private String link;

    private Timestamp presentationTimestamp;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Politician politician;

    @ElementCollection
    @CollectionTable(name="authors", joinColumns=@JoinColumn(name="id"))
    @Column(name="author")
    @JsonManagedReference
    private Set<String> authors = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "proposition", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Processing> processingHistory = new ArrayList<>();
}
