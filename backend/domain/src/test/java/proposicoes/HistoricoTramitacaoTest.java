package proposicoes;

import org.junit.jupiter.api.Test;
import politica.Partido;
import politica.Politico;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoricoTramitacaoTest {

    @Test
    public void historico_deve_conter_uma_sequencia_de_eventos_em_uma_proposicao() throws MalformedURLException {
        String tituloProposicao = "Algum titulo";
        URL linkProposicao = new URL("http://www.google.com");
        Politico politico = new Politico("João Silva", new Partido("PSDB"));
        Proposicao proposicao = new Proposicao(tituloProposicao, linkProposicao, List.of(politico));

        Tramitacao tramitacao1 = new Tramitacao(1, "Descrição 1", LocalDateTime.now());
        Tramitacao tramitacao2 = new Tramitacao(2, "Descrição 2", LocalDateTime.now());

        HistoricoTramitacao historico = new HistoricoTramitacao(proposicao, List.of(tramitacao1, tramitacao2));
        assertTrue(historico.getTramitacoes().contains(tramitacao1));
        assertTrue(historico.getTramitacoes().contains(tramitacao2));
        assertEquals(historico.getProposicao(), proposicao);
    }

    @Test
    public void deve_ser_possivel_instanciar_historico_sem_tramitacoes() throws MalformedURLException {
        String tituloProposicao = "Algum titulo";
        URL linkProposicao = new URL("http://www.google.com");
        Politico politico = new Politico("João Silva", new Partido("PSDB"));
        Proposicao proposicao = new Proposicao(tituloProposicao, linkProposicao, List.of(politico));

        Tramitacao tramitacao1 = new Tramitacao(1, "Descrição 1", LocalDateTime.now());
        Tramitacao tramitacao2 = new Tramitacao(2, "Descrição 2", LocalDateTime.now());

        HistoricoTramitacao historico = new HistoricoTramitacao(proposicao);
        historico.addTramitacao(tramitacao1);
        historico.addTramitacao(tramitacao2);
        assertTrue(historico.getTramitacoes().contains(tramitacao1));
        assertTrue(historico.getTramitacoes().contains(tramitacao2));
        assertEquals(historico.getProposicao(), proposicao);
    }

    @Test
    public void nao_deve_ser_possivel_instanciar_historico_sem_proposicao() {
        assertThrows(IllegalArgumentException.class, () -> new HistoricoTramitacao(null));
    }

    @Test
    public void tramitacoes_do_historico_devem_estar_na_ordem_da_sequencia() throws MalformedURLException {
        String tituloProposicao = "Algum titulo";
        URL linkProposicao = new URL("http://www.google.com");
        Politico politico = new Politico("João Silva", new Partido("PSDB"));
        Proposicao proposicao = new Proposicao(tituloProposicao, linkProposicao, List.of(politico));

        Tramitacao tramitacao1 = new Tramitacao(1, "Descrição 1", LocalDateTime.now());
        Tramitacao tramitacao2 = new Tramitacao(2, "Descrição 2", LocalDateTime.now());

        HistoricoTramitacao historico = new HistoricoTramitacao(proposicao);
        historico.addTramitacao(tramitacao2);
        historico.addTramitacao(tramitacao1);
        assertEquals(historico.getTramitacoes().get(0), tramitacao1);
        assertEquals(historico.getTramitacoes().get(1), tramitacao2);
    }

    @Test
    public void tramitacao_deve_ter_sequencia_e_descricao() {
        assertThrows(IllegalArgumentException.class, () -> new Tramitacao(1, null, LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Tramitacao(1, "", LocalDateTime.now()));
        assertThrows(IllegalArgumentException.class, () -> new Tramitacao(1, " ", LocalDateTime.now()));
    }
}
