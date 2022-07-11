package com.gprindevelopment.cec.api.util;

import com.gprindevelopment.cec.api.expense.Expense;
import com.gprindevelopment.cec.api.politician.Legislature;
import com.gprindevelopment.cec.api.politician.News;
import com.gprindevelopment.cec.api.politician.Politician;
import com.gprindevelopment.cec.api.politician.Profile;
import com.gprindevelopment.cec.api.proposition.Processing;
import com.gprindevelopment.cec.api.proposition.Proposition;
import net.bytebuddy.utility.RandomString;

import java.sql.Timestamp;
import java.time.YearMonth;
import java.util.Random;

public class TestUtils {

    public static Politician generateRandomPolitician() {
        Random rand = new Random();
        Politician politician = new Politician();
        politician.setId(rand.nextLong());
        politician.setName(RandomString.make());
        politician.setSchooling(RandomString.make());
        politician.setTwitterUsername(RandomString.make());
        politician.setParty(RandomString.make());
        politician.setPicture(RandomString.make());
        return politician;
    }

    public static Proposition generateRandomProposition() {
        Random rand = new Random();
        Proposition proposition = new Proposition();
        proposition.setId(rand.nextLong());
        proposition.setLink(RandomString.make());
        proposition.setPolitician(generateRandomPolitician());
        proposition.setPresentationTimestamp(new Timestamp(System.currentTimeMillis()));
        proposition.setTitle(RandomString.make());
        proposition.setTypeDescription(RandomString.make());
        return proposition;
    }

    public static Expense generateRandomExpense() {
        Random rand = new Random();
        Expense expense = new Expense();
        expense.setDocumentNumber(RandomString.make());
        expense.setDocumentUrl(RandomString.make());
        expense.setProvider(RandomString.make());
        expense.setType(RandomString.make());
        expense.setId(rand.nextLong());
        expense.setValue(rand.nextDouble());
        expense.setYearMonth(YearMonth.now());
        return expense;
    }

    public static News generateRandomNews() {
        News news = new News();
        news.setDescription(RandomString.make());
        news.setImageLink(RandomString.make());
        news.setLink(RandomString.make());
        news.setPublishedDate(new Timestamp(System.currentTimeMillis()));
        news.setSourceName(RandomString.make());
        news.setTitle(RandomString.make());
        return news;
    }

    public static Legislature generateRandomLegislature() {
        Random rand = new Random();
        Legislature legislature = new Legislature();
        legislature.setId(rand.nextLong());
        legislature.setEndDate(YearMonth.now());
        legislature.setStartDate(YearMonth.now().minusYears(1));
        return legislature;
    }

    public static Processing generateRandomProcessing() {
        Random rand = new Random();
        Processing processing = new Processing();
        processing.setId(rand.nextLong());
        processing.setDescription(RandomString.make());
        processing.setEntityInitials(RandomString.make());
        processing.setSequence(rand.nextInt());
        processing.setTitle(RandomString.make());
        processing.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return processing;
    }

    public static Profile generateRandomProfile() {
        Random rand = new Random();
        Profile profile = new Profile();
        profile.setName(RandomString.make());
        profile.setId(rand.nextLong());
        profile.setLegislatureId(rand.nextLong());
        profile.setParty(RandomString.make());
        profile.setPicture(RandomString.make());
        return profile;
    }
}
