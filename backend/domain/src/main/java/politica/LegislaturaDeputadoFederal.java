package politica;

import java.time.Year;

public class LegislaturaDeputadoFederal extends Legislatura {

    private static final int DURACAO_LEGISLATURA_ANOS = 4;

    public LegislaturaDeputadoFederal(Year inicio) {
        super(inicio);
    }

    @Override
    public Year getFim() {
        return inicio.plusYears(DURACAO_LEGISLATURA_ANOS);
    }
}
