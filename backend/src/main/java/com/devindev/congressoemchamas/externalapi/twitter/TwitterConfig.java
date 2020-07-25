package com.devindev.congressoemchamas.externalapi.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@Configuration
@PropertySource("classpath:twitter.properties")
public class TwitterConfig {

    @Value("${consumerKey}")
    private String consumerKey;

    @Value("${consumerSecret}")
    private String consumerSecret;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${accessTokenSecret}")
    private String accessTokenSecret;

    private TwitterFactory twitterFactory;

    @PostConstruct
    private void configure(){
        ConfigurationBuilder cb = new ConfigurationBuilder()
                .setOAuthConsumerKey(consumerKey)
                .setOAuthConsumerSecret(consumerSecret)
                .setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        this.twitterFactory = new TwitterFactory(cb.build());
    }

    public Twitter getTwitter(){
        return twitterFactory.getInstance();
    }
}
