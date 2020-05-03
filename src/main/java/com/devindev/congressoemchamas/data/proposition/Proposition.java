package com.devindev.congressoemchamas.data.proposition;

import com.devindev.congressoemchamas.data.processing.Processing;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Proposition {

    private Long id;

    // TODO: 4/4/2020 Change this to an enum or database data
    private String typeDescription;

    private String title;

    private String link;

    private Timestamp presentationTimestamp;

    private Set<String> authors = new HashSet<>();

    private List<Processing> processingHistory = new ArrayList<>();
}
