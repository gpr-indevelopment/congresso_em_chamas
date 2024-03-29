package com.gprindevelopment.cec.api.politician;

import io.github.gprindevelopment.deputados.ConsultaDeputado;
import io.github.gprindevelopment.deputados.DeputadoClient;
import io.github.gprindevelopment.dominio.Estado;
import io.github.gprindevelopment.legislaturas.LegislaturaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final DeputadoClient deputadoClient;

    private final LegislaturaClient legislaturaClient;

    public List<Profile> findAllOnCurrentLegislatureByPoliticianName(String name, String stateInitials) {
        try {
            ConsultaDeputado.Builder consultaBuilder = new ConsultaDeputado.Builder()
                    .nome(name)
                    .itens(1000)
                    .legislaturas(legislaturaClient.consultarLegislaturaAtual().getId());
            if (stateInitials != null && !stateInitials.isEmpty()) {
                consultaBuilder.estados(Estado.valueOf(stateInitials));
            }
            return deputadoClient.consultar(consultaBuilder.build()).stream().map(Profile::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Excecao ainda nao tratada. Tratar no futuro.");
        }
    }

    public List<Profile> findAllByPoliticianName(String name, Long legislatureId) {
        try {
            ConsultaDeputado consulta = new ConsultaDeputado.Builder()
                    .nome(name)
                    .itens(1000)
                    .legislaturas(legislatureId.intValue())
                    .build();
            return deputadoClient.consultar(consulta).stream().map(Profile::new).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Excecao ainda nao tratada. Tratar no futuro.");
        }
    }
}
