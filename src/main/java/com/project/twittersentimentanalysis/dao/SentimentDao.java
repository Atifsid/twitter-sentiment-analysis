package com.project.twittersentimentanalysis.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.twittersentimentanalysis.entities.Sentiment;

@Repository
public interface SentimentDao extends JpaRepository<Sentiment, Long> {

}