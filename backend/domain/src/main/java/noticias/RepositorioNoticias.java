package noticias;

import noticias.Noticia;

import java.util.List;

public interface RepositorioNoticias {

    List<Noticia> procurarUltimasNoticias(long idDeputado, int contadorUltimasNoticias);
}
