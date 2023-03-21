package com.project.twittersentimentanalysis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.TweetService;

@RestController
public class MainController {

	@Autowired
	private TweetService tweetsService;

	@GetMapping("/tweets")
	public List<Tweet> getCourses(@RequestParam String query) {
		return this.tweetsService.getTweets(query);
	}
}
