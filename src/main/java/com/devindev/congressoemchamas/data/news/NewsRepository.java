package com.devindev.congressoemchamas.data.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepository {

    @Autowired
    private NewsDAO newsDAO;

    public void delete(Long newsId){
        newsDAO.deleteById(newsId);
    }
}
