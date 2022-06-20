package despesas;

import politica.Legislatura;

import java.util.List;

public class ExtratoLegislatura extends Extrato {

    private final Legislatura legislatura;

    public ExtratoLegislatura(List<Despesa> despesas, long politicoId, Legislatura legislatura) {
        super(despesas, politicoId);
        this.legislatura = legislatura;
    }

    public Legislatura getLegislatura() {
        return legislatura;
    }
}
