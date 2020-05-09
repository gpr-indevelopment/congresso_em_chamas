package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.expense.Expense;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GetExpensesByPoliticianId extends CongressoResponseHandler<List<Expense>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetExpensesByPoliticianId.class);

    @Override
    protected List<Expense> handleResponse(JsonObject jsonObject) {
        List<Expense> expenses = new ArrayList<>();
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
        return expenses;
    }
}
