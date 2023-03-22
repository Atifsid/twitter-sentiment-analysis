package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.entities.Sentiment;

public interface AnalyzerService {
	public List<Sentiment> analyze();
}