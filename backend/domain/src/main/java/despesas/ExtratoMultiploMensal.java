package despesas;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExtratoMultiploMensal extends ExtratoMultiplo<ExtratoMensal> {

    private final Map<YearMonth, ExtratoMensal> mesAnoParaExtrato = new HashMap<>();

    public ExtratoMultiploMensal(List<ExtratoMensal> extratos, long politicoId) {
        super(extratos, politicoId);
        extratos.forEach(extratoMensal -> {
            int mes = extratoMensal.getMes();
            int ano = extratoMensal.getAno();
            mesAnoParaExtrato.put(YearMonth.of(ano, mes), extratoMensal);
        });
    }

    public Optional<ExtratoMensal> getExtrato(int mes, int ano) {
        return Optional.ofNullable(mesAnoParaExtrato.get(YearMonth.of(ano, mes)));
    }
}
