package proposicoes;

import org.junit.jupiter.api.Test;
import politica.Partido;
import politica.Politico;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProposicaoTest {

    @Test
    public void proposicoes_tem_titulo_autores_link() throws MalformedURLException {
        URL link = new URL("http://google.com");
        List<Politico> autores = List.of(new Politico("João Silva", new Partido("PSDB")));
        String titulo = "Algum titulo";

        Proposicao proposicao = new Proposicao(titulo, link, autores);
        assertEquals(proposicao.getAutores(), autores);
        assertEquals(proposicao.getTitulo(), titulo);
        assertEquals(proposicao.getLink(), link);
    }

    @Test
    public void nao_deve_ser_possivel_instanciar_proposicao_sem_titulo_autores_e_link() throws MalformedURLException {
        URL link = new URL("http://google.com");
        List<Politico> autores = List.of(new Politico("João Silva", new Partido("PSDB")));
        String titulo = "Algum titulo";

        assertThrows(IllegalArgumentException.class, () -> new Proposicao(null, link, autores));
        assertThrows(IllegalArgumentException.class, () -> new Proposicao(titulo, null, autores));
        assertThrows(IllegalArgumentException.class, () -> new Proposicao(titulo, link, null));
    }

    @Test
    public void proposicao_nao_deve_ter_titulo_vazio() throws MalformedURLException {
        URL link = new URL("http://google.com");
        List<Politico> autores = List.of(new Politico("João Silva", new Partido("PSDB")));
        assertThrows(IllegalArgumentException.class, () -> new Proposicao("", link, autores));
        assertThrows(IllegalArgumentException.class, () -> new Proposicao(" ", link, autores));
    }

    @Test
    public void proposicao_deve_ter_ao_menos_um_autor() throws MalformedURLException {
        URL link = new URL("http://google.com");
        String titulo = "Algum titulo";

        assertThrows(IllegalArgumentException.class, () -> new Proposicao(titulo, link, new ArrayList<>()));
    }
}
