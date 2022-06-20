package noticias;

import java.net.URL;
import java.time.LocalDateTime;

public class Noticia {

    private final String titulo;

    private final URL url;

    private final LocalDateTime dataPublicacao;

    public Noticia(String titulo, URL url, LocalDateTime dataPublicacao) {
        if (titulo == null || url == null || dataPublicacao == null) {
            throw new IllegalArgumentException("Noticias devem ter titulo, URL e data de publicação.");
        }
        this.titulo = titulo;
        this.url = url;
        this.dataPublicacao = dataPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public URL getUrl() {
        return url;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }
}
