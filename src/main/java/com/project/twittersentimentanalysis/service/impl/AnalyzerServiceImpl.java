package com.project.twittersentimentanalysis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.twittersentimentanalysis.dao.SentimentDao;
import com.project.twittersentimentanalysis.dao.TweetDao;
import com.project.twittersentimentanalysis.dto.ResponseDto;
import com.project.twittersentimentanalysis.entities.Sentiment;
import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.AnalyzerService;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

@Service
public class AnalyzerServiceImpl implements AnalyzerService {
	static StanfordCoreNLP pipeline;

	@Autowired
	private SentimentDao sentimentDao;
	@Autowired
	private TweetDao tweetDao;

	public static void init() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
	}

	public static String findSentiment(String tweet) {
		String sentimentType = "NULL";
		if (tweet != null && tweet.length() > 0) {
			Annotation annotation = pipeline.process(tweet);
			for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
				sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
			}
		}
		return sentimentType;
	}

	int num_neutral = 0;
	int num_negative = 0;
	int num_realnegative = 0;
	int num_positive = 0;
	int num_realpositive = 0;

	@Override
	public ResponseDto<List<Sentiment>> analyze() {
		ResponseDto<List<Sentiment>> response = new ResponseDto<>();
		try {
			init();
			List<Sentiment> list = new ArrayList<>();

			List<Tweet> tweets = tweetDao.findAll();

			for (int i = 0; i < tweets.size(); i++) {
				String tweetText = tweets.get(i).getTweetText();
				String sentiment = findSentiment(tweetText);

				if (sentiment.equalsIgnoreCase("Neutral")) {
					num_neutral++;
				} else if (sentiment.equalsIgnoreCase("Negative")) {
					num_negative++;
				} else if (sentiment.equalsIgnoreCase("Very Negative")) {
					num_realnegative++;
				} else if (sentiment.equalsIgnoreCase("Very Positive")) {
					num_realpositive++;
				} else {
					num_positive++;
				}
			}

			Sentiment s = new Sentiment();
			s.setId(1);
			s.setNeutral(num_neutral);
			s.setNegative(num_negative);
			s.setRealnegative(num_realnegative);
			s.setRealpositive(num_realpositive);
			s.setPositive(num_positive);
			sentimentDao.save(s);

			num_neutral = 0;
			num_negative = 0;
			num_realnegative = 0;
			num_positive = 0;
			num_realpositive = 0;
			list.add(s);
			
			response.setCode(HttpServletResponse.SC_OK);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Analyzed Successfully");
			response.setData(list);
			
		} catch (Exception e) {
			response.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

	@Override
	public ResponseDto<List<Sentiment>> sentiment() {
		ResponseDto<List<Sentiment>> response = new ResponseDto<List<Sentiment>>();
		try {
			List<Sentiment> list = new ArrayList<>();
			list.addAll(sentimentDao.findAll());
			
			response.setCode(HttpServletResponse.SC_OK);
			response.setStatus(HttpStatus.OK);
			response.setMessage("Sentiment Fetched Successfully");
			response.setData(list);
		} catch (Exception e) {
			response.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
		}
		return response;
	}

	@Override
	public ResponseDto<List<String>> getDataForWordCloud() {
		ResponseDto<List<String>> responseDto = new ResponseDto<>();
		
		
		return responseDto;
	}
}