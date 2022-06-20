package politica;

import java.time.Year;
import java.util.Objects;

public abstract class Legislatura {

    protected final Year inicio;

    public Legislatura(Year inicio) {
        if (inicio == null) {
            throw new IllegalArgumentException("A legislatura deve ter um ano de início válido.");
        }
        this.inicio = inicio;
    }

    public Year getInicio() {
        return inicio;
    }

    public abstract Year getFim();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Legislatura that = (Legislatura) o;
        return inicio.equals(that.inicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio);
    }
}
