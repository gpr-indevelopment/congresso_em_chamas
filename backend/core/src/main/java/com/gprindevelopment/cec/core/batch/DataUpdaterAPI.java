package com.gprindevelopment.cec.core.batch;

import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.politician.Legislature;
import com.gprindevelopment.cec.core.politician.Politician;
import com.gprindevelopment.cec.core.politician.Profile;
import com.gprindevelopment.cec.core.proposition.Processing;
import com.gprindevelopment.cec.core.proposition.Proposition;
import io.github.gprindevelopment.deputados.ConsultaDeputado;
import io.github.gprindevelopment.deputados.DeputadoClient;
import io.github.gprindevelopment.despesas.ConsultaDespesa;
import io.github.gprindevelopment.despesas.DespesaClient;
import io.github.gprindevelopment.dominio.Autor;
import io.github.gprindevelopment.dominio.Proposicao;
import io.github.gprindevelopment.legislaturas.LegislaturaClient;
import io.github.gprindevelopment.proposicoes.ConsultaProposicao;
import io.github.gprindevelopment.proposicoes.ConsultaTramitacao;
import io.github.gprindevelopment.proposicoes.ProposicaoClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DataUpdaterAPI {

    private final DespesaClient despesaClient;

    private final ProposicaoClient proposicaoClient;

    private final DeputadoClient deputadoClient;

    private final LegislaturaClient legislaturaClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpdaterAPI.class);

    private void doWait(Long delaySeconds) {
        try {
            delaySeconds = Objects.nonNull(delaySeconds) ? delaySeconds : 0;
            TimeUnit.SECONDS.sleep(delaySeconds);
        } catch (InterruptedException e) {
            LOGGER.error("An error was thrown when waiting in between API requests.");
        }
    }

    public List<Expense> requestAllExpensesByPoliticianId(Long id, List<Integer> months, List<Integer> years) {
        try {
            ConsultaDespesa.Builder builder = new ConsultaDespesa.Builder(id.intValue());
            if (months != null) {
                builder.meses(months);
            }

            if (years != null) {
                builder.anos(years);
            }
            return despesaClient.consultar(builder.build()).stream().map(Expense::new).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when updating expense of politician with id {}. Retrying...", id);
            doWait(5L);
            return requestAllExpensesByPoliticianId(id, months, years);
        }
    }

    public List<Long> requestPropositionIdsByPoliticianId(Long id) {
        try {
            ConsultaProposicao consulta = new ConsultaProposicao.Builder()
                    .idAutores(id.intValue())
                    .build();
            return proposicaoClient.consultar(consulta).stream().map(Proposicao::getId).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting proposition ids of politician with id {}. Retrying...", id);
            doWait(5L);
            return requestPropositionIdsByPoliticianId(id);
        }
    }

    public Proposition requestPropositionById(Long id) {
        try {
            return new Proposition(proposicaoClient.consultarDetalhes(id).get());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting propositions by its id: {}. Retrying...", id);
            doWait(5L);
            return requestPropositionById(id);
        }
    }

    public List<String> requestAuthorsByPropositionId(Long id) {
        try {
            return proposicaoClient.consultarAutores(id).stream().map(Autor::getNome).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting a list of authors by a proposition id: {}. Retrying...", id);
            doWait(5L);
            return requestAuthorsByPropositionId(id);
        }
    }

    public List<Processing> requestProcessingHistoryByPropositionId(Long id) {
        try {
            ConsultaTramitacao consulta = new ConsultaTramitacao.Builder(id)
                    .build();
            return proposicaoClient.consultarTramitacoes(consulta).stream().map(Processing::new).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting a list of processing history by a proposition id: {}. Retrying...", id);
            doWait(5L);
            return requestProcessingHistoryByPropositionId(id);
        }
    }

    public Politician requestPoliticianById(Long id) {
        try {
            return new Politician(deputadoClient.consultarDeputadoPorId(id.intValue()).get());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting politician with id {}. Retrying...", id);
            doWait(5L);
            return requestPoliticianById(id);
        }
    }

    public Legislature requestCurrentLegislature() {
        try {
            return new Legislature(legislaturaClient.consultarLegislaturaAtual());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting the current legislature. Retrying...");
            doWait(5L);
            return requestCurrentLegislature();
        }
    }

    public List<Profile> requestProfilesByNameAndLegislatureId(String name, Long legislatureId) {
        try {
            ConsultaDeputado consulta = new ConsultaDeputado.Builder()
                    .nome(name)
                    .itens(1000)
                    .legislaturas(legislatureId.intValue())
                    .build();
            return deputadoClient.consultar(consulta).stream().map(Profile::new).collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occurred when requesting profiles by name and legislature id. Retrying...");
            doWait(5L);
            return requestProfilesByNameAndLegislatureId(name, legislatureId);
        }
    }
}
