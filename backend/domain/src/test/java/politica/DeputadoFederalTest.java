package politica;

import org.junit.jupiter.api.Test;
import politica.DeputadoFederal;
import politica.LegislaturaDeputadoFederal;
import politica.Partido;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class DeputadoFederalTest {

    @Test
    public void deputado_federal_deve_ter_nome_partido_legislatura() {
        Partido partido = new Partido("PSDB");
        LegislaturaDeputadoFederal legislatura = new LegislaturaDeputadoFederal(Year.of(2022));
        String nome = "Joao Silva";
        DeputadoFederal deputado = new DeputadoFederal(nome, partido, legislatura);
        assertEquals(deputado.getNome(), nome);
        assertEquals(deputado.getPartido(), partido);
        assertEquals(deputado.getLegislatura(), legislatura);

        assertThrows(IllegalArgumentException.class, () -> new DeputadoFederal(nome, partido, null));
    }

    @Test
    public void deputados_sao_iguais_pelo_nome_partido_legislatura() {
        Partido partido = new Partido("PSDB");
        LegislaturaDeputadoFederal legislatura = new LegislaturaDeputadoFederal(Year.of(2022));
        String nome = "Joao Silva";
        assertEquals(new DeputadoFederal(nome, partido, legislatura),
                new DeputadoFederal(nome, partido, legislatura));

        assertNotEquals(new DeputadoFederal(nome, partido, legislatura),
                new DeputadoFederal(nome, partido, new LegislaturaDeputadoFederal(Year.of(2020))));
    }
}
