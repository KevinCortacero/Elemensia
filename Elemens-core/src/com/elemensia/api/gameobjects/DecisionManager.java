package com.elemensia.api.gameobjects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;

public class DecisionManager {

	private Map<Decision, Boolean> decisions;
	
	public DecisionManager() {
		this.decisions = new HashMap<>();
	}
	
	public boolean getDecisionValue(Decision decisionName) {
		if (! this.decisions.containsKey(decisionName))
			return false;
		return this.decisions.get(decisionName);
	}

	public void setDecisionValue(Decision decisionName, boolean value) {
		this.decisions.put(decisionName, value);
	}
	
	public void setDecisionValue(Decision decisionName, int keyPressed) {
		this.setDecisionValue(decisionName, Gdx.input.isKeyPressed(keyPressed));
	}
}
