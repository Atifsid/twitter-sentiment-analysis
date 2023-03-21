package com.project.twittersentimentanalysis.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.twittersentimentanalysis.entities.Tweet;

public interface TweetDao extends JpaRepository<Tweet, Long>{

}
