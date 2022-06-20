package politica;

import java.util.Objects;

public class DeputadoFederal extends Politico {

    private final Legislatura legislatura;

    public DeputadoFederal(String nome, Partido partido, LegislaturaDeputadoFederal legislatura) {
        super(nome, partido);
        if (legislatura == null) {
            throw new IllegalArgumentException("Um deputado federal deve ter uma legislatura");
        }
        this.legislatura = legislatura;
    }

    public Legislatura getLegislatura() {
        return legislatura;
    }

    @Override
    public boolean equals(Object o) {
        boolean igualPolitico = super.equals(o);
        if (igualPolitico) {
            DeputadoFederal deputadoFederal = (DeputadoFederal) o;
            igualPolitico = this.legislatura.equals(deputadoFederal.legislatura);
        }
        return igualPolitico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, partido, legislatura);
    }
}
