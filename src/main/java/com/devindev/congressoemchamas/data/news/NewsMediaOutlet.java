package com.devindev.congressoemchamas.data.news;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NewsMediaOutlet {
    YAHOO ("br.noticias.yahoo.com") {
        @Override
        @JsonValue
        public String toString() {
            return "Yahoo";
        }
    },
    ESTADAO ("politica.estadao.com.br") {
        @Override
        @JsonValue
        public String toString() {
            return "Estadão";
        }
    },
    FOLHA ("folha.uol.com.br") {
        @Override
        @JsonValue
        public String toString() {
            return "Folha de S. Paulo";
        }
    },
    G1 ("g1.globo.com") {
        @Override
        @JsonValue
        public String toString() {
            return "G1";
        }

    };

    NewsMediaOutlet(String baseLink) {
        this.baseLink = baseLink;
    }

    private String baseLink;

    public static NewsMediaOutlet fromUrl(String url){
        for (NewsMediaOutlet mediaOutlet : NewsMediaOutlet.values()) {
            if(url.contains(mediaOutlet.baseLink)){
                return mediaOutlet;
            }
        }
        return null;
    }
}
