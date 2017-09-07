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

	public void changeAnimation(){

	}
}
