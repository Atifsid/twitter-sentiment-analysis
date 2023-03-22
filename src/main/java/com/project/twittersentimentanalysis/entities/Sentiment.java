package com.project.twittersentimentanalysis.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Sentiment {

	@Id
	long id;
	int neutral;
	int negative;
	int realnegative;
	int positive;
	int realpositive;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNeutral() {
		return neutral;
	}

	public void setNeutral(int neutral) {
		this.neutral = neutral;
	}

	public int getNegative() {
		return negative;
	}

	public void setNegative(int negative) {
		this.negative = negative;
	}

	public int getRealnegative() {
		return realnegative;
	}

	public void setRealnegative(int realnegative) {
		this.realnegative = realnegative;
	}

	public int getPositive() {
		return positive;
	}

	public void setPositive(int positive) {
		this.positive = positive;
	}

	public int getRealpositive() {
		return realpositive;
	}

	public void setRealpositive(int realpositive) {
		this.realpositive = realpositive;
	}
}