package noticias;

import noticias.Noticia;

import java.util.List;

public class Noticiario {

    private final RepositorioNoticias repositorioNoticias;

    public Noticiario(RepositorioNoticias repositorioNoticias) {
        this.repositorioNoticias = repositorioNoticias;
    }

    public List<Noticia> procurarUltimasNoticias(long idDeputado, int contadorUltimasNoticias) {
        return repositorioNoticias.procurarUltimasNoticias(idDeputado, contadorUltimasNoticias);
    }
}
