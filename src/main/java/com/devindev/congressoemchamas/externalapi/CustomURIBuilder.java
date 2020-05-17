package com.devindev.congressoemchamas.externalapi;

import org.apache.http.client.utils.URIBuilder;

import java.util.Objects;

public class CustomURIBuilder extends URIBuilder {

    public void addArrayParameter(String key, Object[] values){
        if(Objects.nonNull(values)){
            for (Object value : values) {
                addParameter(key, value.toString());
            }
        }
    }
}
