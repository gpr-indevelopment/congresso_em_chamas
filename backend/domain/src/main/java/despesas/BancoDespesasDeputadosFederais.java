package despesas;

import politica.Legislatura;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class BancoDespesasDeputadosFederais {

    private final RepositorioDespesasDeputadosFederais repositorioDespesasDeputadosFederais;

    public BancoDespesasDeputadosFederais(RepositorioDespesasDeputadosFederais repositorioDespesasDeputadosFederais) {
        this.repositorioDespesasDeputadosFederais = repositorioDespesasDeputadosFederais;
    }

    public ExtratoMensal gerarExtratoMensalDeputadoFederal(long idDeputado, int mes, int ano) {
        List<Despesa> despesas = repositorioDespesasDeputadosFederais.procurarPorMesAno(idDeputado, mes, ano);
        return new ExtratoMensal(despesas, idDeputado, mes, ano);
    }

    public ExtratoLegislatura gerarExtratoLegislaturaDeputadoFederal(long idDeputado, Legislatura legislatura) {
        List<Despesa> despesas = repositorioDespesasDeputadosFederais.procurarPorLegislatura(idDeputado, legislatura);
        return new ExtratoLegislatura(despesas, idDeputado, legislatura);
    }

    public ExtratoMultiploMensal gerarExtratoUltimosMesesDeputadoFederal(long idDeputado, int ultimosMeses) {
        List<ExtratoMensal> extratos = new ArrayList<>();
        YearMonth agora = YearMonth.now();
        for(int i = 0; i < ultimosMeses; i++) {
            YearMonth current = agora.minusMonths(i);
            extratos.add(gerarExtratoMensalDeputadoFederal(idDeputado, current.getMonthValue(), current.getYear()));
        }

        return new ExtratoMultiploMensal(extratos, idDeputado);
    }

}
