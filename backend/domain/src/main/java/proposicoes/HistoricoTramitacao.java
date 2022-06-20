package proposicoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoricoTramitacao {

    private final Proposicao proposicao;

    private final List<Tramitacao> tramitacoes;

    public HistoricoTramitacao(Proposicao proposicao) {
        if (proposicao == null) {
            throw new IllegalArgumentException("Histórico de tramitação deve ter uma proposição associada.");
        }
        this.proposicao = proposicao;
        this.tramitacoes = new ArrayList<>();
    }

    public HistoricoTramitacao(Proposicao proposicao, List<Tramitacao> tramitacoes) {
        this.proposicao = proposicao;
        this.tramitacoes = new ArrayList<>(tramitacoes);
        this.tramitacoes.sort(Comparator.naturalOrder());
    }

    public Proposicao getProposicao() {
        return proposicao;
    }

    public List<Tramitacao> getTramitacoes() {
        return Collections.unmodifiableList(tramitacoes);
    }

    public void addTramitacao(Tramitacao tramitacao) {
        tramitacoes.add(tramitacao);
        tramitacoes.sort(Comparator.naturalOrder());
    }
}
