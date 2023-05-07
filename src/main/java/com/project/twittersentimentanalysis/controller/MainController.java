package com.project.twittersentimentanalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.twittersentimentanalysis.dao.TweetDao;
import com.project.twittersentimentanalysis.dto.ResponseDto;
import com.project.twittersentimentanalysis.entities.Sentiment;
import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.AnalyzerService;
import com.project.twittersentimentanalysis.service.TweetService;

@RestController
public class MainController {

	@Autowired
	private TweetService tweetsService;
	@Autowired
	private AnalyzerService analyzerService;
	@Autowired
	private TweetDao tweetDao;

	@GetMapping("/tweets")
	public ResponseDto<List<Tweet>> getTweets(@RequestParam String query) {
		return this.tweetsService.getTweets(query);
	}
	@GetMapping("/fetchTweets")
	public List<Tweet> getTweets() {
		return this.tweetDao.findAll();
	}

	@GetMapping("/analyze")
	public ResponseDto<List<Sentiment>> getAnalysis() {
		return this.analyzerService.analyze();
	}
	@GetMapping("/sentiment")
	public ResponseDto<List<Sentiment>> getsentiment() {
		return this.analyzerService.sentiment();
	}
	@GetMapping("/wordcloud")
	public ResponseDto<List<String>> getDataForWordCloud(){
		return analyzerService.getDataForWordCloud();
	}
}
