package despesas;

import despesas.Despesa;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DespesaTest {

    @Test
    public void despesas_devem_ter_valor_e_data_completa() {
        LocalDateTime data = LocalDateTime.of(2020, 3, 5, 1, 3);
        double valor = 2.3;
        Despesa despesa = new Despesa(valor, data);
        assertEquals(despesa.getValor(), valor);
        assertEquals(despesa.getData(), data);
    }

    @Test
    public void despesas_iguais_se_instancia_igual() {
        LocalDateTime agora = LocalDateTime.now();
        double valor = 2.5;
        Despesa despesa = new Despesa(valor, agora);
        assertEquals(despesa, despesa);
        assertNotEquals(despesa, new Despesa(valor, agora));
    }
}
