package com.gprindevelopment.cec.core.politician;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class News {

    private String title;

    private String link;

    private String description;

    private Timestamp publishedDate;

    private String sourceName;

    private String imageLink;

}
