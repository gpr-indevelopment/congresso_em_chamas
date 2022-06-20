package despesas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

public abstract class ExtratoMultiplo<T extends Extrato> {

    private final long politicoId;

    private final List<T> extratos;

    private final BigDecimal valorTotal;

    public ExtratoMultiplo(List<T> extratos, long politicoId) {
        this.politicoId = politicoId;
        this.extratos = Collections.unmodifiableList(extratos);
        this.valorTotal = extratos
                .stream()
                .reduce(BigDecimal.valueOf(0.0), (a, b) -> a.add(b.getValorTotal()),
                BigDecimal::add).setScale(2, RoundingMode.CEILING);
    }

    public List<T> getExtratos() {
        return extratos;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public long getPoliticoId() {
        return politicoId;
    }
}
