package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.entities.Tweet;

public interface FetchTweet {
	public List<Tweet> fetchTweets(String query);
}
