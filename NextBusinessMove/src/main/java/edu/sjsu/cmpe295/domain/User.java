package edu.sjsu.cmpe295.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	private List<String> recommendations = new ArrayList<String>();
	
	public List<String> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<String> recommendations) {
		this.recommendations = recommendations;
	}
	
}
