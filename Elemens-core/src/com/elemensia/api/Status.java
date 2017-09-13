package com.elemensia.api;

import java.util.HashMap;
import java.util.Map;

public class Status {
	
	private Map<String, SubState> states;
	
	public Status() {
		this.states = new HashMap<>();
		this.states.put("DIRECTIONV", SubState.NONE);
		this.states.put("DIRECTIONH", SubState.RIGHT);
		this.states.put("ENVIRONMENT", SubState.GROUND);
		this.states.put("MOVEMENT", SubState.NONE);
		this.states.put("ACTION", SubState.NONE);
	}

	public SubState getState(String stateName) {
		return this.states.get(stateName);
	}

	public void setState(String stateName, SubState state) {
		this.states.put(stateName, state);
	}
	
	@Override
	public String toString() {
		String res = "";
		for (SubState s : this.states.values()){
			res += s.name() + " ";
		}
		return res;
	}
}
