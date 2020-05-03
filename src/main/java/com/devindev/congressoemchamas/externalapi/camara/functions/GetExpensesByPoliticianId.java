package com.devindev.congressoemchamas.externalapi.camara.functions;

import com.devindev.congressoemchamas.data.expense.Expense;
import com.devindev.congressoemchamas.externalapi.CongressoResponseHandler;
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
        jsonObject.get("dados").getAsJsonArray().forEach(data -> {
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
            expense.setDocumentNumber(dataObject.get("numDocumento").getAsString());
            expense.setDocumentUrl(dataObject.get("urlDocumento").getAsString());
            expense.setProvider(dataObject.get("nomeFornecedor").getAsString());
            expense.setType(dataObject.get("tipoDespesa").getAsString());
            expense.setValue(dataObject.get("valorDocumento").getAsDouble());
            expenses.add(expense);
        });
        return expenses;
    }
}
