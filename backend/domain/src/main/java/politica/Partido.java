package politica;

import java.util.Objects;

public class Partido {

    private final String nome;

    public Partido(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("Um partido deve ter um nome.");
        }

        if (nome.isBlank()) {
            throw new IllegalArgumentException("O nome de um partido não pode estar em branco.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Partido partido = (Partido) o;
        return nome.equals(partido.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
