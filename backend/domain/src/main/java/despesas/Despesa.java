package despesas;

import java.time.LocalDateTime;

public class Despesa {

    private final double valor;

    private final LocalDateTime data;

    public Despesa(double valor, LocalDateTime data) {
        this.valor = valor;
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
