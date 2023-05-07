package com.project.twittersentimentanalysis.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.twittersentimentanalysis.dao.TweetDao;
import com.project.twittersentimentanalysis.dto.ResponseDto;
import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.FetchTweetService;
import com.project.twittersentimentanalysis.service.TweetService;

@Service
public class TweetsServiceImpl implements TweetService {

	@Autowired
	private TweetDao tweetsDao;
	@Autowired
	private FetchTweetService fetchTweet;

	public TweetsServiceImpl() {

	}

	@Override
	public ResponseDto<List<Tweet>> getTweets(String query) {
		ResponseDto<List<Tweet>> response = new ResponseDto<>();
		try {
			List<Tweet> list = fetchTweet.fetchTweets(query);
			tweetsDao.saveAll(list);
			response.setCode(HttpServletResponse.SC_OK);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Successfully fetched Tweets");
			response.setData(list);
		} catch (Exception e) {
			response.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
	
}
