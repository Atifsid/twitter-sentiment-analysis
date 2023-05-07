package com.project.twittersentimentanalysis.service;

import java.util.List;

import com.project.twittersentimentanalysis.dto.ResponseDto;
import com.project.twittersentimentanalysis.entities.Sentiment;

public interface AnalyzerService {
	public ResponseDto<List<Sentiment>> analyze();

	public ResponseDto<List<Sentiment>> sentiment();

	public ResponseDto<List<String>> getDataForWordCloud();
	
}