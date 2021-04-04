package com.gprindevelopment.cec.core.externalapi.camara.functions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gprindevelopment.cec.core.expense.Expense;
import com.gprindevelopment.cec.core.restclient.CongressoResponseHandler;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GetAllExpensesByPoliticianId extends CongressoResponseHandler<List<Expense>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllExpensesByPoliticianId.class);

    @Override
    protected List<Expense> handleResponse(JsonObject jsonObject) {
        List<Expense> expenses = new ArrayList<>();
        requestPageRecursive(jsonObject, expenses);
        return expenses;
    }

    private void requestPageRecursive(JsonObject jsonObject, List<Expense> expenses){
        handlePageResponse(jsonObject, expenses);
        for (JsonElement link : jsonObject.get("links").getAsJsonArray()) {
            if(link.getAsJsonObject().get("rel").getAsString().equals("next")){
                expenses.addAll(requestPage(link.getAsJsonObject().get("href").getAsString()));
                break;
            }
        }
    }

    private void handlePageResponse(JsonObject jsonObject, List<Expense> expenses){
        for (JsonElement data : jsonObject.get("dados").getAsJsonArray()) {
            JsonObject dataObject = data.getAsJsonObject();
            Expense expense = new Expense();
            expense.setYearMonth(YearMonth.of(dataObject.get("ano").getAsInt(), dataObject.get("mes").getAsInt()));
            expense.setDocumentCode(dataObject.get("codDocumento").getAsLong());
            expense.setDocumentNumber(nullCheckRetrievedStringValue(dataObject.get("numDocumento")));
            expense.setDocumentUrl(nullCheckRetrievedStringValue(dataObject.get("urlDocumento")));
            expense.setProvider(nullCheckRetrievedStringValue(dataObject.get("nomeFornecedor")).toUpperCase());
            expense.setType(nullCheckRetrievedStringValue(dataObject.get("tipoDespesa")));
            expense.setValue(dataObject.get("valorDocumento").getAsDouble());
            expenses.add(expense);
        }
    }

    private List<Expense> requestPage(String url){
        try {
            GetAllExpensesByPoliticianId apiFunctionHandler = new GetAllExpensesByPoliticianId();
            return Request.Get(url).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the processing list of a proposition from CamaraAPI.");
            LOGGER.error("Returning an empty processing list.");
            return new ArrayList<>();
        }
    }
}
