package noticias;

import noticias.Noticia;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NoticiaTest {

    @Test
    public void noticias_devem_ter_titulo_url_e_data() throws MalformedURLException {
        URL url = new URL("http://ww.google.com");
        String titulo = "Uma notícia";
        LocalDateTime dataPublicacao = LocalDateTime.of(2020, 1, 2, 20, 5);
        Noticia noticia = new Noticia(titulo, url, dataPublicacao);
        assertEquals(noticia.getTitulo(), titulo);
        assertEquals(noticia.getDataPublicacao(), dataPublicacao);
        assertEquals(noticia.getUrl(), url);

        assertThrows(IllegalArgumentException.class, () -> new Noticia(null, url, dataPublicacao));
        assertThrows(IllegalArgumentException.class, () -> new Noticia(titulo, null, dataPublicacao));
        assertThrows(IllegalArgumentException.class, () -> new Noticia(titulo, url, null));
    }
}
