package com.devindev.congressoemchamas.data.news;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum NewsMediaOutlet {
    YAHOO ("br.noticias.yahoo.com", "#324fe1") {
        @Override
        public String toString() {
            return "Yahoo";
        }
    },
    ESTADAO ("politica.estadao.com.br", "#a6a6a6") {
        @Override
        public String toString() {
            return "Estadão";
        }
    },
    FOLHA ("folha.uol.com.br", "#000000") {
        @Override
        public String toString() {
            return "Folha de S. Paulo";
        }
    },
    G1 ("g1.globo.com", "#C4170C") {
        @Override
        public String toString() {
            return "G1";
        }

    };

    NewsMediaOutlet(String baseLink, String themeColor) {
        this.themeColor = themeColor;
        this.baseLink = baseLink;
    }

    private String baseLink;

    private String themeColor;

    private String name;

    public String getBaseLink() {
        return baseLink;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public String getName() {
        return this.toString();
    }

    public static NewsMediaOutlet fromUrl(String url){
        for (NewsMediaOutlet mediaOutlet : NewsMediaOutlet.values()) {
            if(url.contains(mediaOutlet.baseLink)){
                return mediaOutlet;
            }
        }
        return null;
    }
}
