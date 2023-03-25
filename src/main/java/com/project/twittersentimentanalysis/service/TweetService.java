package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.dto.ResponseDto;
import com.project.twittersentimentanalysis.entities.Tweet;

public interface TweetService {
	public ResponseDto<List<Tweet>> getTweets(String query);
}
