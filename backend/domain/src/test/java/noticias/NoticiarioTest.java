package noticias;

import noticias.Noticia;
import noticias.Noticiario;
import noticias.RepositorioNoticias;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NoticiarioTest {

    private final Noticiario noticiario;

    private final RepositorioNoticias repositorioNoticias;

    public NoticiarioTest() {
        repositorioNoticias = mock(RepositorioNoticias.class);
        noticiario = new Noticiario(repositorioNoticias);
    }

    @Test
    public void buscar_ultimas_10_noticias_deputado_federal_retorna_lista_noticias() throws MalformedURLException {
        long idDeputado = 1L;
        int contadorUltimasNoticias = 10;
        Noticia noticia = new Noticia("Alguma noticia", new URL("http://google.com.br"), LocalDateTime.now());
        List<Noticia> esperado = List.of(noticia);

        when(repositorioNoticias.procurarUltimasNoticias(idDeputado, contadorUltimasNoticias)).thenReturn(esperado);

        List<Noticia> noticias = noticiario.procurarUltimasNoticias(idDeputado, contadorUltimasNoticias);
        assertEquals(noticias.size(), 1);
        assertTrue(noticias.contains(noticia));
    }

    @Test
    public void se_nenhuma_noticia_for_encontrada_nas_ultimas_retornar_vazio() {
        long idDeputado = 1L;
        int contadorUltimasNoticias = 10;

        when(repositorioNoticias.procurarUltimasNoticias(idDeputado, contadorUltimasNoticias)).thenReturn(new ArrayList<>());

        List<Noticia> noticias = noticiario.procurarUltimasNoticias(idDeputado, contadorUltimasNoticias);
        assertTrue(noticias.isEmpty());
    }
}
