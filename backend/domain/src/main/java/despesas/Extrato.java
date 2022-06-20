package despesas;

import despesas.Despesa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public abstract class Extrato {

    private final long politicoId;

    private final List<Despesa> despesas;

    private final BigDecimal valorTotal;

    private final LocalDateTime dataRequisicao;

    public Extrato(List<Despesa> despesas, long politicoId) {
        this.politicoId = politicoId;
        this.despesas = Collections.unmodifiableList(despesas);
        this.valorTotal = despesas
                .stream()
                .reduce(BigDecimal.valueOf(0.0), (a, b) -> a.add(BigDecimal.valueOf(b.getValor())),
                BigDecimal::add).setScale(2, RoundingMode.CEILING);
        this.dataRequisicao = LocalDateTime.now();
    }

    public List<Despesa> getDespesas() {
        return despesas;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public long getPoliticoId() {
        return politicoId;
    }

    public LocalDateTime getDataRequisicao() {
        return dataRequisicao;
    }
}
