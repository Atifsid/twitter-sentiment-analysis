package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.entities.Tweet;

public interface FetchTweetService {
	public List<Tweet> fetchTweets(String query);
}
