package com.gprindevelopment.cec.core.externalapi.camara;

import com.gprindevelopment.cec.core.exception.CECException;
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
import io.github.gprindevelopment.dominio.Despesa;
import io.github.gprindevelopment.dominio.Proposicao;
import io.github.gprindevelopment.http.Pagina;
import io.github.gprindevelopment.legislaturas.LegislaturaClient;
import io.github.gprindevelopment.proposicoes.ConsultaProposicao;
import io.github.gprindevelopment.proposicoes.ConsultaTramitacao;
import io.github.gprindevelopment.proposicoes.ProposicaoClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CamaraClientFacade {

    private final DespesaClient despesaClient;

    private final ProposicaoClient proposicaoClient;

    private final DeputadoClient deputadoClient;

    private final LegislaturaClient legislaturaClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(CamaraClientFacade.class);

    public List<Expense> requestAllExpensesByPoliticianId(Long id, List<Integer> months, List<Integer> years) {
        try {
            int paginaAtual = 1;
            ConsultaDespesa.Builder builder = new ConsultaDespesa.Builder(id.intValue())
                    .pagina(paginaAtual)
                    .meses(months)
                    .anos(years);
            Pagina<Despesa> pagina = despesaClient.consultar(builder.build());
            List<Expense> result = pagina.stream().map(Expense::new).collect(Collectors.toList());
            while (pagina.temProxima()) {
                builder.pagina(++paginaAtual);
                pagina = despesaClient.consultar(builder.build());
                result.addAll(pagina.stream().map(Expense::new).collect(Collectors.toList()));
            }
            return result;
        } catch (Exception e) {
            String message = String.format("An error occurred when updating expense of politician with id %d.", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public List<Long> requestPropositionIdsByPoliticianId(Long id) {
        try {
            ConsultaProposicao consulta = new ConsultaProposicao.Builder()
                    .idAutores(id.intValue())
                    .build();
            return proposicaoClient.consultar(consulta).stream().map(Proposicao::getId).collect(Collectors.toList());
        } catch (Exception e) {
            String message = String.format("An error occurred when requesting proposition ids of politician with id %d", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public Proposition requestPropositionById(Long id) {
        try {
            return new Proposition(proposicaoClient.consultarDetalhes(id).get());
        } catch (Exception e) {
            String message = String.format("An error occurred when requesting propositions by its id: %d.", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public List<String> requestAuthorsByPropositionId(Long id) {
        try {
            return proposicaoClient.consultarAutores(id).stream().map(Autor::getNome).collect(Collectors.toList());
        } catch (Exception e) {
            String message = String.format("An error occurred when requesting a list of authors by a proposition id: %d", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public List<Processing> requestProcessingHistoryByPropositionId(Long id) {
        try {
            ConsultaTramitacao consulta = new ConsultaTramitacao.Builder(id)
                    .build();
            return proposicaoClient.consultarTramitacoes(consulta).stream().map(Processing::new).collect(Collectors.toList());
        } catch (Exception e) {
            String message = String.format("An error occurred when requesting a list of processing history by a proposition id: %d", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public Politician requestPoliticianById(Long id) {
        try {
            return new Politician(deputadoClient.consultarDeputadoPorId(id.intValue()).get());
        } catch (Exception e) {
            String message = String.format("An error occurred when requesting politician with id %d", id);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }

    public Legislature requestCurrentLegislature() {
        try {
            return new Legislature(legislaturaClient.consultarLegislaturaAtual());
        } catch (Exception e) {
            LOGGER.error("An error occurred when requesting the current legislature.");
            throw new CECException(e);
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
            String message = String.format("An error occurred when requesting profiles by name %s and legislature id %d.", name, legislatureId);
            LOGGER.error(message, e);
            throw new CECException(message, e);
        }
    }
}
