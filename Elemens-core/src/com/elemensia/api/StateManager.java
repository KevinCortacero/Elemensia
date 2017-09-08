package com.elemensia.api;

import com.elemensia.api.gameobjects.LivingThing;

public class StateManager {
	
	private static StateManager instance;
	private String currentState;
	private String currentDirectionHorizontal;
	private String currentDirectionVertical;
	private String currentEnvironment;
	private String currentAction;
	private String currentMovement;

	private StateManager(){
		this.currentDirectionHorizontal = "RIGHT";
	}
	
	public static void updateState(LivingThing livingThing){
		if (instance == null)
			instance = new StateManager();
		instance.setDirectionHorizontal(livingThing);
		

		//TODO: set animation
	}

	private void setDirectionHorizontal(LivingThing livingThing){
		String newState = new String(this.currentDirectionHorizontal);
		// BLOC F
		switch(this.currentDirectionHorizontal){
		case "LEFT" :
			if (livingThing.getInputValue("RIGHT")){
				newState = "RIGHT";
			}
			break;

		case "RIGHT" :
			if (livingThing.getInputValue("LEFT")){
				newState = "LEFT";
			}
			break;
		}

		// BLOC M
		this.currentDirectionHorizontal = newState;
		
		System.out.println("DIRECTION : " + this.currentDirectionHorizontal);

		/*
		// BLOC G
		switch(this.currentDirectionHorizontal){
		case "LEFT" :
			doSomething();
		case "RIGHT" :
			doSomething();
		 */
	}

	
	private void setDirectionVertical(LivingThing livingThing){
		String newState = new String(this.currentDirectionVertical);
		// BLOC F
		switch(this.currentDirectionVertical){
		case "NONE" :
			if (livingThing.getInputValue("TOP")){
				newState = "TOP";
			}
			if (livingThing.getInputValue("DOWN")){
				newState = "DOWN";
			}
			break;

		case "TOP" :
			if (!livingThing.getInputValue("TOP")){
				newState = "NONE";
			}
			break;
			
		case "DOWN" :
			if (!livingThing.getInputValue("DOWN")){
				newState = "NONE";
			}
			break;
		}

		// BLOC M
		this.currentDirectionVertical = newState;

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
