package proposicoes;

import politica.Politico;

import java.net.URL;
import java.util.Collections;
import java.util.List;

public class Proposicao {

    private final String titulo;

    private final URL link;

    private final List<Politico> autores;

    public Proposicao(String titulo, URL link, List<Politico> autores) {
        if (titulo == null || link == null || autores == null) {
            throw new IllegalArgumentException("Uma proposição precisa de título, link e autores válidos.");
        }
        if (titulo.isBlank()) {
            throw new IllegalArgumentException("Proposições não podem ter um título em branco.");
        }
        if (autores.isEmpty()) {
            throw new IllegalArgumentException("Proposições devem ter pelo menos um autor.");
        }
        this.titulo = titulo;
        this.link = link;
        this.autores = Collections.unmodifiableList(autores);
    }

    public String getTitulo() {
        return titulo;
    }

    public URL getLink() {
        return link;
    }

    public List<Politico> getAutores() {
        return autores;
    }
}
