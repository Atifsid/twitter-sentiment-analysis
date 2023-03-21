package com.project.twittersentimentanalysis.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.project.twittersentimentanalysis.entities.Tweet;
import com.project.twittersentimentanalysis.service.FetchTweet;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class FetchTweetImpl implements FetchTweet{

	@Override
	public List<Tweet> fetchTweets(String query) {
		List<Tweet> list = new ArrayList<>();
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		.setOAuthConsumerKey("")
		.setOAuthConsumerSecret("")
		.setOAuthAccessToken("")
		.setOAuthAccessTokenSecret("");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		
		try {
			Twitter twitter = tf.getSingleton();
			
			Query q = new Query(query + " +exclude:retweets");
			q.setResultType(q.RECENT);
			q.setCount(100);
			
			for (int i = 1; i <= 15; i++) {
                // Keep in mind that the search index has a 7-day limit. In other words, no tweets will be found for a date older than one week.
                // https://dev.twitter.com/rest/reference/get/search/tweets#until
                QueryResult result = twitter.search(q);
                for (Status tweet : result.getTweets()) {
                    System.out.println(tweet.getText() + "(@" + tweet.getUser().getScreenName() + ")" +  "[" + tweet.getCreatedAt() +  "]");
                    Tweet t = new Tweet();
                    t.setUserName(tweet.getUser().getScreenName());
                    t.setTweetText(tweet.getText());
                    t.setCreatedAt(tweet.getCreatedAt());
                    list.add(t);
                }
                // Check if there's a next page, else break the for loop
                if (!result.hasNext()){
                    break;
                }
                // Keep search only every three seconds because the Twitter'll be angry if you request in continuous
                Thread.sleep(3000);
                q = result.nextQuery();
            }
			
		} catch (TwitterException te) {
			// Check Twitter Status on : https://dev.twitter.com/overview/status
            te.printStackTrace();
            System.out.println("Failed to get the query: " + te.getMessage());
            System.exit(-1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return list;
	}

}
