package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.expenses.Expense;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GetAllExpensesByPoliticianId extends CongressoResponseHandler<List<Expense>> {

    private List<Expense> expenses;

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllExpensesByPoliticianId.class);

    public GetAllExpensesByPoliticianId(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    protected List<Expense> handleResponse(JsonObject jsonObject) {
        requestPageRecursive(jsonObject);
        return expenses;
    }

    private void requestPageRecursive(JsonObject jsonObject){
        handlePageResponse(jsonObject);
        String nextPageUrl = null;
        for (JsonElement link : jsonObject.get("links").getAsJsonArray()) {
            if(link.getAsJsonObject().get("rel").getAsString().equals("next")){
                nextPageUrl = link.getAsJsonObject().get("href").getAsString();
                break;
            }
        }
        if(Objects.nonNull(nextPageUrl)){
            requestPage(nextPageUrl);
        }
    }

    private void handlePageResponse(JsonObject jsonObject){
        for (JsonElement data : jsonObject.get("dados").getAsJsonArray()) {
            JsonObject dataObject = data.getAsJsonObject();
            Expense expense = new Expense();
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Timestamp timestamp = new Timestamp(dateFormat.parse(dataObject.get("dataDocumento").getAsString()).getTime());
                expense.setDate(timestamp);
            } catch (ParseException e) {
                LOGGER.error(e.getMessage());
                LOGGER.error("Unable to parse the date of an expense. Expense returning with a null date.");
            }
            expense.setDocumentNumber(nullCheckRetrievedStringValue(dataObject.get("numDocumento")));
            expense.setDocumentUrl(nullCheckRetrievedStringValue(dataObject.get("urlDocumento")));
            expense.setProvider(nullCheckRetrievedStringValue(dataObject.get("nomeFornecedor")));
            expense.setType(nullCheckRetrievedStringValue(dataObject.get("tipoDespesa")));
            expense.setValue(dataObject.get("valorDocumento").getAsDouble());
            expenses.add(expense);
        }
    }

    private List<Expense> requestPage(String url){
        try {
            GetAllExpensesByPoliticianId apiFunctionHandler = new GetAllExpensesByPoliticianId(expenses);
            return Request.Get(url).execute().handleResponse(apiFunctionHandler);
        } catch (IOException exception) {
            LOGGER.error(exception.getMessage());
            LOGGER.error("Unable to retrieve the processing list of a proposition from CamaraAPI.");
            LOGGER.error("Returning an empty processing list.");
            return new ArrayList<>();
        }
    }
}
