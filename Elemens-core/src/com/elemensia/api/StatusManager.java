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

	public void updateStatus(LivingThing livingThing){
		State newState = this.stateMachine.getDirectionH(livingThing, this.status);
		this.status.setState("DIRECTIONH", newState);
		
		newState = this.stateMachine.getDirectionV(livingThing, this.status);
		this.status.setState("DIRECTIONV", newState);
		
		newState = this.stateMachine.getAction(livingThing, this.status);
		this.status.setState("ACTION", newState);

		/*
		instance.currentState = instance.currentDirectionHorizontal + " " + instance.currentDirectionVertical + " " + instance.currentAction;

		System.out.println("STATE : " + instance.currentState);
		 */
		//TODO: set animation
	}

	/*private void setEnvironment(LivingThing livingThing){
		String newState = new String(this.currentEnvironment);
		// BLOC F
		switch(this.currentEnvironment){
		case "GROUND" :
			if (livingThing.getEnvironmentType()=="WATER"){
				newState = "WATER";
			}
			if (livingThing.getEnvironmentType()=="AIR"){
				newState = "AIR";
			}
			break;

		case "WATER" :
			if (livingThing.getEnvironmentType()=="GROUND"){
				newState = "GROUND";
			}
			if (livingThing.getEnvironmentType()=="AIR"){
				newState = "AIR";
			}
			break;

		case "AIR" :
			if (livingThing.getEnvironmentType()=="WATER"){
				newState = "WATER";
			}
			if (livingThing.getEnvironmentType()=="GROUND"){
				newState = "GROUND";
			}
			break;

			// BLOC M
			this.currentEnvironment = newState;
		}

		public void changeAnimation(){

		}*/
}
