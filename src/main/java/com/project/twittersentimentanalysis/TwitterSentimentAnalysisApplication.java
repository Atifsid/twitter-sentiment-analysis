package com.project.twittersentimentanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@SpringBootApplication
public class TwitterSentimentAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterSentimentAnalysisApplication.class, args);
	}

}
