package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.entities.Tweet;

public interface TweetService {
	public List<Tweet> getTweets(String query);
}
