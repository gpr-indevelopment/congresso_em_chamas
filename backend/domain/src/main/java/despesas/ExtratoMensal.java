package despesas;

import java.util.List;

public class ExtratoMensal extends Extrato {

    private final int mes;

    private final int ano;

    public ExtratoMensal(List<Despesa> despesas, long politicoId, int mes, int ano) {
        super(despesas, politicoId);
        this.mes = mes;
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }
}
