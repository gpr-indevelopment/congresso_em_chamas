package despesas;

import org.junit.jupiter.api.Test;
import politica.Legislatura;
import politica.LegislaturaDeputadoFederal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BancoDespesasDeputadosFederaisTest {

    private final BancoDespesasDeputadosFederais bancoDespesas;

    private final RepositorioDespesasDeputadosFederais repositorio;

    public BancoDespesasDeputadosFederaisTest() {
        repositorio = mock(RepositorioDespesasDeputadosFederais.class);
        bancoDespesas = new BancoDespesasDeputadosFederais(repositorio);
    }

    @Test
    public void extrato_mensal_despesas_retorna_despesas_de_mes_ano() {
        long idPolitico = 1L;
        int mes = 1;
        int ano = 2022;
        Despesa despesa1 = new Despesa(15.2, LocalDateTime.now());
        Despesa despesa2 = new Despesa(1.5, LocalDateTime.now());
        List<Despesa> despesas = List.of(despesa1, despesa2);

        when(repositorio.procurarPorMesAno(idPolitico, mes, ano)).thenReturn(despesas);

        ExtratoMensal extrato = bancoDespesas.gerarExtratoMensalDeputadoFederal(idPolitico, mes, ano);
        assertEquals(extrato.getDespesas().size(), 2);
        assertEquals(extrato.getMes(), mes);
        assertEquals(extrato.getAno(), ano);
        assertTrue(extrato.getDespesas().contains(despesa1));
        assertTrue(extrato.getDespesas().contains(despesa2));
        assertEquals(extrato.getValorTotal(), BigDecimal.valueOf(16.7)
                .setScale(2, RoundingMode.CEILING));
    }

    @Test
    public void extrato_legislatura() {
        long idPolitico = 1L;
        Legislatura legislatura = new LegislaturaDeputadoFederal(Year.of(2023));
        Despesa despesa1 = new Despesa(15.2, LocalDateTime.now());
        Despesa despesa2 = new Despesa(1.5, LocalDateTime.now());
        List<Despesa> despesas = List.of(despesa1, despesa2);

        when(repositorio.procurarPorLegislatura(idPolitico, legislatura)).thenReturn(despesas);
        ExtratoLegislatura extrato = bancoDespesas.gerarExtratoLegislaturaDeputadoFederal(idPolitico, legislatura);
        assertEquals(extrato.getDespesas().size(), 2);
        assertTrue(extrato.getDespesas().contains(despesa1));
        assertTrue(extrato.getDespesas().contains(despesa2));
        assertEquals(extrato.getLegislatura(), legislatura);
        assertEquals(extrato.getValorTotal(), BigDecimal.valueOf(16.7)
                .setScale(2, RoundingMode.CEILING));
    }

    @Test
    public void extrato_ultimos_6_meses_retorna_mes_corrente_e_5_anteriores() {
        long idPolitico = 1L;
        int ultimosMeses = 6;
        Despesa despesa1 = new Despesa(15.2, LocalDateTime.now());
        Despesa despesa2 = new Despesa(1.5, LocalDateTime.now());
        Despesa despesa3 = new Despesa(9.1, LocalDateTime.now());
        List<Despesa> despesasEsseMes = List.of(despesa3);
        List<Despesa> despesasUltimoMes = List.of(despesa1);
        List<Despesa> despesasSeisMesesAtras = List.of(despesa2);
        YearMonth agora = YearMonth.now();
        YearMonth mesPassado = agora.minusMonths(1);
        YearMonth seisMesesAtras = agora.minusMonths(5);

        when(repositorio.procurarPorMesAno(idPolitico, agora.getMonthValue(), agora.getYear()))
                .thenReturn(despesasEsseMes);
        when(repositorio.procurarPorMesAno(idPolitico, mesPassado.getMonthValue(), mesPassado.getYear()))
                .thenReturn(despesasUltimoMes);
        when(repositorio.procurarPorMesAno(idPolitico, seisMesesAtras.getMonthValue(), seisMesesAtras.getYear()))
                .thenReturn(despesasSeisMesesAtras);

        ExtratoMultiploMensal extrato = bancoDespesas.gerarExtratoUltimosMesesDeputadoFederal(idPolitico, ultimosMeses);
        assertEquals(extrato.getValorTotal(), BigDecimal.valueOf(25.8)
                .setScale(2, RoundingMode.CEILING));
        assertEquals(extrato.getExtratos().size(), ultimosMeses);
        assertTrue(extrato.getExtrato(mesPassado.getMonthValue(), mesPassado.getYear())
                .orElseThrow(AssertionError::new)
                .getDespesas()
                .contains(despesa1));
    }
}
