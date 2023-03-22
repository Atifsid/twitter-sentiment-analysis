package com.project.twittersentimentanalysis.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.twittersentimentanalysis.dao.TweetDao;
import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.FetchTweet;
import com.project.twittersentimentanalysis.service.TweetService;

@Service
public class TweetsServiceImpl implements TweetService {

	@Autowired
	private TweetDao tweetsDao;
	@Autowired
	private FetchTweet fetchTweet;

	public TweetsServiceImpl() {

	}

	@Override
	public List<Tweet> getTweets(String query) {
		List<Tweet> list = fetchTweet.fetchTweets(query);
		tweetsDao.saveAll(list);
		return tweetsDao.findAll();
	}

}
