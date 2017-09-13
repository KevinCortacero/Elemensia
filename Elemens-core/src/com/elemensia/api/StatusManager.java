package com.elemensia.api;

import com.elemensia.api.gameobjects.LivingThing;

public class StatusManager {

	private StateMachine stateMachine;
	//private static StateManager instance;
	private Status status;

	public StatusManager(){
		this.stateMachine = new StateMachine();
		this.status = new Status();
	}

	public State getState(String stateName){
		return this.status.getState(stateName);
	}
	
	@Override
	public String toString() {
		return this.status.toString();
	}
	
	public void updateStatus(LivingThing livingThing){
		State newState = this.stateMachine.getDirectionH(livingThing, this.status);
		this.status.setState("DIRECTIONH", newState);
		
		newState = this.stateMachine.getDirectionV(livingThing, this.status);
		this.status.setState("DIRECTIONV", newState);
		
		newState = this.stateMachine.getAction(livingThing, this.status);
		this.status.setState("ACTION", newState);
		
		newState = this.stateMachine.getMovement(livingThing, this.status);
		this.status.setState("MOVEMENT", newState);

		/*
		instance.currentState = instance.currentDirectionHorizontal + " " + instance.currentDirectionVertical + " " + instance.currentAction;

		System.out.println("STATE : " + instance.currentState);
		 */
		//TODO: set animation
	}

	public String getValue() {
		return this.getState("ENVIRONMENT").name() + " " + this.getState("MOVEMENT").name() + " " + this.getState("ACTION").name() + " " + this.getState("DIRECTIONH").name();
	}

	public void setState(String stateName, State state) {
		this.status.setState(stateName, state);
	}

}
