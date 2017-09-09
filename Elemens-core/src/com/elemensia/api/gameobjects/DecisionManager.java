package com.elemensia.api.gameobjects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class DecisionManager {

	private Map<String, Boolean> decisions;
	
	public DecisionManager() {
		this.decisions = new HashMap<>();
	}
	
	public boolean getDecisionValue(String decisionName) {
		if (! this.decisions.containsKey(decisionName))
			return false;
		return this.decisions.get(decisionName);
	}

	public void setDecisionValue(String decisionName, boolean value) {
		this.decisions.put(decisionName, value);
	}
	
	public void setDecisionValue(String decisionName, int keyPressed) {
		this.setDecisionValue(decisionName, Gdx.input.isKeyPressed(keyPressed));
	}
}
