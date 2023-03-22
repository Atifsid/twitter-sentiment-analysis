package com.project.twittersentimentanalysis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.twittersentimentanalysis.entities.Tweet;

@Repository
public interface TweetDao extends JpaRepository<Tweet, Long> {

}
