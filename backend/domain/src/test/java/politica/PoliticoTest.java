package politica;

import org.junit.jupiter.api.Test;
import politica.Partido;
import politica.Politico;

import static org.junit.jupiter.api.Assertions.*;

public class PoliticoTest {

    @Test
    public void politico_deve_ter_nome_e_partido() {
        String nome = "Joao Silva";
        Partido partido = new Partido("PSDB");
        Politico politico = new Politico(nome, partido);
        assertEquals(politico.getNome(), nome);
        assertEquals(politico.getPartido(), partido);

        assertThrows(IllegalArgumentException.class, () -> new Politico(null, partido));
        assertThrows(IllegalArgumentException.class, () -> new Politico(nome, null));
    }

    @Test
    public void nome_do_politico_nao_pode_estar_em_branco() {
        Partido partido = new Partido("PSDB");
        assertThrows(IllegalArgumentException.class, () -> new Politico("", partido));
        assertThrows(IllegalArgumentException.class, () -> new Politico(" ", partido));
    }

    @Test
    public void politicos_sao_iguais_pelo_nome_partido() {
        String nome = "Joao Silva";
        Partido partido = new Partido("PSDB");

        assertEquals(new Politico(nome, partido), new Politico(nome, partido));
        assertNotEquals(new Politico(nome, partido), new Politico("Outro nome", partido));
    }
}
