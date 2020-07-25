package com.devindev.congressoemchamas;

import com.devindev.congressoemchamas.data.expenses.MonthlyExpense;
import com.devindev.congressoemchamas.data.legislature.Legislature;
import com.devindev.congressoemchamas.data.news.News;
import com.devindev.congressoemchamas.data.politician.Politician;
import com.devindev.congressoemchamas.data.profile.Profile;
import com.devindev.congressoemchamas.data.proposition.Proposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


/**
 * API design best practices: https://stackoverflow.blog/2020/03/02/best-practices-for-rest-api-design/
 * Partial response for APIs: https://developers.google.com/drive/api/v3/performance
 */
@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}", method = RequestMethod.GET)
    public Politician getPolitician(@PathVariable Long politicianId) {
        return mainService.findById(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/propositions", method = RequestMethod.GET)
    public List<Proposition> getPropositionsByPoliticianId(@PathVariable Long politicianId) {
        return mainService.findPropositionsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/news", method = RequestMethod.GET)
    public List<News> getNews(@PathVariable Long politicianId) {
        return mainService.findNewsByPoliticianId(politicianId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses/{legislatureId}", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpensesByPoliticianId(@PathVariable Long politicianId, @PathVariable Long legislatureId) {
        return mainService.findByLegislature(politicianId, legislatureId);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/politicians/{politicianId}/monthlyexpenses", method = RequestMethod.GET)
    public List<MonthlyExpense> getMonthlyExpensesByPoliticianId(@PathVariable Long politicianId, @RequestParam(required = false) List<Integer> months, @RequestParam(required = false) List<Integer> years) {
        return mainService.findMonthlyExpensesByPoliticianId(politicianId, months, years);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/legislature", method = RequestMethod.GET)
    public Legislature getCurrentLegislature() {
        return mainService.getCurrentLegislature();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(path = "/profiles", method = RequestMethod.GET)
    public List<Profile> getProfilesByName(@RequestParam String name) {
        return mainService.findProfilesByName(name);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, path = "/document/upload")
    public Document upload(@RequestParam("file") MultipartFile file) {
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Document doc = new Document();
        Random random = new Random();
        doc.setNumber(Integer.valueOf(random.nextInt(100000)).longValue());
        doc.setPart("samplepart");
        doc.setType("sampletype");
        doc.setDesignOffice("WEG");
        doc.setVersion(01);
        return doc;
    }

    private class Document {
        private Long number;

        private String type;

        private String part;

        private Integer version;

        private String designOffice;

        public Long getNumber() {
            return number;
        }

        public void setNumber(Long number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public String getDesignOffice() {
            return designOffice;
        }

        public void setDesignOffice(String designOffice) {
            this.designOffice = designOffice;
        }
    }
}