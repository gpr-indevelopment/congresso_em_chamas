package politica;

import org.junit.jupiter.api.Test;
import politica.Partido;

import static org.junit.jupiter.api.Assertions.*;

public class PartidoTest {

    @Test
    public void partido_nao_pode_ter_nome_null_ou_em_branco() {
        assertThrows(IllegalArgumentException.class, () -> new Partido(null));
        assertThrows(IllegalArgumentException.class, () -> new Partido(""));
        assertThrows(IllegalArgumentException.class, () -> new Partido(" "));
    }

    @Test
    public void partido_deve_ser_igualado_pelo_nome() {
        assertEquals(new Partido("PSDB"), new Partido("PSDB"));
        assertNotEquals(new Partido("PSDB"), new Partido("PL"));
    }
}
