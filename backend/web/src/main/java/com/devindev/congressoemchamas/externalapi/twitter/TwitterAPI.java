package com.devindev.congressoemchamas.externalapi.twitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.ResponseList;
import twitter4j.User;

@Component
public class TwitterAPI {

    @Autowired
    private TwitterConfig twitterConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterAPI.class);

    public String requestTwitterUsernameByName(String politicianName) {
        try {
            ResponseList<User> usersFound = twitterConfig.searchUsers(politicianName, 0);
            if(!usersFound.isEmpty()) {
                return usersFound.get(0).getScreenName();
            }
            return null;
        } catch (Exception exception) {
            LOGGER.error(String.format("An exception was thrown while searching %s on Twitter.", politicianName));
            LOGGER.error(exception.getMessage());
            return null;
        }
    }
}
