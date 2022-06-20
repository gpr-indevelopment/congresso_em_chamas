package politica;

import org.junit.jupiter.api.Test;
import politica.Legislatura;
import politica.LegislaturaDeputadoFederal;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class LegislaturaTest {

    @Test
    public void legislatura_de_deputado_federal_deve_durar_4_anos () {
        Year inicio = Year.of(2020);
        Legislatura legislatura = new LegislaturaDeputadoFederal(inicio);
        Year fim = legislatura.getFim();
        assertEquals(fim.minusYears(4), legislatura.getInicio());
    }

    @Test
    public void nao_e_possivel_uma_legislatura_deputado_federal_com_inicio_null() {
        assertThrows(IllegalArgumentException.class, () -> new LegislaturaDeputadoFederal(null));
    }

    @Test
    public void legislaturas_devem_ser_igualadas_pelo_tipo_e_ano() {
        assertEquals(new LegislaturaDeputadoFederal(Year.of(2022)),
                new LegislaturaDeputadoFederal(Year.of(2022)));

        assertNotEquals(new LegislaturaDeputadoFederal(Year.of(2020)),
                new LegislaturaDeputadoFederal(Year.of(2022)));
    }
}
