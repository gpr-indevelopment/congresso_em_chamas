package com.devindev.congressoemchamas.data.politician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticianRepository {

    @Autowired
    private PoliticianDAO dao;

    public Politician findById(Long id){
        return dao.findById(id).orElse(null);
    }

    public Politician save(Politician politician){
        return dao.save(politician);
    }
}
