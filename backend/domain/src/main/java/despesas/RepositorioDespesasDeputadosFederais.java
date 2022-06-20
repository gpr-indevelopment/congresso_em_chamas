package despesas;

import despesas.Despesa;
import politica.Legislatura;

import java.util.List;

public interface RepositorioDespesasDeputadosFederais {

    List<Despesa> procurarPorMesAno(long politicoId, int mes, int ano);

    List<Despesa> procurarPorLegislatura(long politicoId, Legislatura legislatura);
}
