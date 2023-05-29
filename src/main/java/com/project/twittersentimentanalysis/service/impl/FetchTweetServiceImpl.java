package com.project.twittersentimentanalysis.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.FetchTweetService;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class FetchTweetServiceImpl implements FetchTweetService {

	@Override
	public List<Tweet> fetchTweets(String query) {
		List<Tweet> list = new ArrayList<>();

		ConfigurationBuilder cb = new ConfigurationBuilder();
		// add creds
		cb.



		TwitterFactory tf = new TwitterFactory(cb.build());

		try {
			Twitter twitter = tf.getInstance();

			Query q = new Query(query + " +exclude:retweets");
			q.setResultType(q.RECENT);
			q.setCount(100);
			q.setLang("en");

//			for (int i = 1; i <= 1; i++) {
			int i = 0;
				QueryResult result = twitter.search(q);
				for (Status tweet : result.getTweets()) {
					i++;
					Tweet t = new Tweet();
					t.setUserName(tweet.getUser().getScreenName());
					t.setTweetText(tweet.getText());
					t.setCreatedAt(tweet.getCreatedAt());
					list.add(t);
					if(i == 100) break;
				}
//				if (!result.hasNext()) {
//					break;
//				}
//				Thread.sleep(3000);
//				q = result.nextQuery();
//			}

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get the query: " + te.getMessage());
			System.exit(-1);
		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		return list;
	}

}