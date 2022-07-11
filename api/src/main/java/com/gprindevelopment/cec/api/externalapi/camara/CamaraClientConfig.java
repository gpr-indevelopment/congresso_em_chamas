package com.gprindevelopment.cec.api.externalapi.camara;

import io.github.gprindevelopment.deputados.DeputadoClient;
import io.github.gprindevelopment.despesas.DespesaClient;
import io.github.gprindevelopment.legislaturas.LegislaturaClient;
import io.github.gprindevelopment.proposicoes.ProposicaoClient;
import io.github.gprindevelopment.votacoes.VotacaoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamaraClientConfig {

    @Bean
    public DeputadoClient deputadoClient() {
        return new DeputadoClient();
    }

    @Bean
    public LegislaturaClient legislaturaClient() {
        return new LegislaturaClient();
    }

    @Bean
    public DespesaClient despesaClient() {
        return new DespesaClient();
    }

    @Bean
    public ProposicaoClient proposicaoClient() {
        return new ProposicaoClient();
    }

    @Bean
    public VotacaoClient votacaoClient() {
        return new VotacaoClient();
    }
}
