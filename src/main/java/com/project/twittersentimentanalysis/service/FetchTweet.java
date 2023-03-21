package com.project.twittersentimentanalysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.twittersentimentanalysis.entities.Tweet;

@Service
public interface FetchTweet {
	public List<Tweet> fetchTweets(String query);
}
