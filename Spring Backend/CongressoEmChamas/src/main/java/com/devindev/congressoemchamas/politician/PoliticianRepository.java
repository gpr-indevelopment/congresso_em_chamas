package com.devindev.congressoemchamas.politician;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PoliticianRepository {

    @Autowired
    private PoliticianDAO dao;
}
