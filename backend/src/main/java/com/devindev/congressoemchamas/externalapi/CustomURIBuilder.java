package com.devindev.congressoemchamas.externalapi;

import org.apache.http.client.utils.URIBuilder;

import java.util.List;
import java.util.Objects;

public class CustomURIBuilder extends URIBuilder {

    public void addListParameter(String key, List<? extends Object> values){
        if(Objects.nonNull(values) && !values.isEmpty()){
            for (Object value : values) {
                addParameter(key, value.toString());
            }
        }
    }
}
