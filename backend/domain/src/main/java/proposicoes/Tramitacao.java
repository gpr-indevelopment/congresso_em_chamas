package proposicoes;

import java.time.LocalDateTime;

public class Tramitacao implements Comparable<Tramitacao> {

    private final int sequencia;

    private final String descricao;

    private final LocalDateTime dataHora;

    public Tramitacao(int sequencia, String descricao, LocalDateTime dataHora) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Tramitações devem ter uma descrição válida.");
        }
        this.sequencia = sequencia;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public int getSequencia() {
        return sequencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    @Override
    public int compareTo(Tramitacao o) {
        return Integer.compare(this.sequencia, o.sequencia);
    }
}
