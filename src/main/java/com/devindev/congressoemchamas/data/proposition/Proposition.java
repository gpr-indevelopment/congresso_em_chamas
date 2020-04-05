package com.devindev.congressoemchamas.data.proposition;

import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.processing.Processing;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Proposition {

    @Id
    private Long id;

    // TODO: 4/4/2020 Change this to an enum or database data
    private String typeDescription;

    private String title;

    private String link;

    private Timestamp presentationTimestamp;

    private List<String> authors = new ArrayList<>();

    private List<Processing> processingHistory = new ArrayList<>();
}
