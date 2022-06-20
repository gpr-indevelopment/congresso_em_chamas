package politica;

import java.util.Objects;

public class Politico {

    protected final String nome;

    protected final Partido partido;

    public Politico(String nome, Partido partido) {
        if (nome == null || partido == null) {
            throw new IllegalArgumentException("Políticos devem ter nome e partido válidos.");
        }

        if (nome.isBlank()) {
            throw new IllegalArgumentException("Um político não pode ter o nome em branco.");
        }
        this.nome = nome;
        this.partido = partido;
    }

    public String getNome() {
        return nome;
    }

    public Partido getPartido() {
        return partido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Politico politico = (Politico) o;
        return nome.equals(politico.nome) && partido.equals(politico.partido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, partido);
    }
}
