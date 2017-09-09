package com.elemensia.api;

import java.util.HashMap;
import java.util.Map;

public class Status {
	
	private Map<String, State> states;
	
	public Status() {
		this.states = new HashMap<>();
		this.states.put("DIRECTIONV", State.RIGHT);
		this.states.put("DIRECTIONH", State.NONE);
		this.states.put("ENVIRONMENT", State.GROUND);
		this.states.put("MOVEMENT", State.IDLE);
		this.states.put("ACTION", State.NONE);
	}

	public State getState(String stateName) {
		return this.states.get(stateName);
	}

	public void setState(String stateName, State state) {
		this.states.put(stateName, state);
	}
}
