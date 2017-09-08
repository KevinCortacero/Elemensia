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
		this.currentDirectionVertical = "NONE";
	}

	public static void updateState(LivingThing livingThing){
		if (instance == null)
			instance = new StateManager();
		instance.setDirectionHorizontal(livingThing);
		instance.setDirectionVertical(livingThing);
		
		instance.currentState = instance.currentDirectionHorizontal + " " + instance.currentDirectionVertical;
		System.out.println("STATE : " + instance.currentState);

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


	private void setAction(LivingThing livingThing){
		String newState = new String(this.currentAction);
		// BLOC F
		switch(this.currentAction){
		case "NONE" :
			if (livingThing.getInputValue("ATTACK") /*TODO : isAvailble()*/){
				newState = "FIGHT";
			}else if  (livingThing.getInputValue("EAT")){
				newState = "EAT";
			}else if  (livingThing.getInputValue("SLEEP") && this.currentMovement=="IDLE"){
				newState = "SLEEP";
			}else if  (livingThing.getInputValue("CLIMB") /*TODO : isEchelle()*/){
				newState = "CLIMB";
			}else if  (livingThing.isAlive()){
				newState = "DEAD";
			}
			break;

		case "FIGHT" :
			if (true/*TODO : isAvailble()*/){
				newState = "NONE";
			}else if  (livingThing.isAlive()){
				newState = "DEAD";
			}
			break;

		case "EAT" :
			if (true/*TODO : isAvailble()*/){
				newState = "NONE";
			}else if  (livingThing.isAlive()){
				newState = "DEAD";
			}
			break;

		case "SLEEP" :
			if (livingThing.getInputValue("SLEEP")){
				newState = "NONE";
			}else if  (livingThing.isAlive()){
				newState = "DEAD";
			}
			break;

		case "CLIMB" :
			if (livingThing.getInputValue("JUMP") || livingThing.getInputValue("CLIMB") /*TODO  || contactGround() */){
				newState = "NONE";
			}else if  (livingThing.isAlive()){
				newState = "DEAD";
			}
			break;
		case "DEAD" :
			if (livingThing.getInputValue("DOWN")){
				newState = "NONE";
			}
			break;
		}

		// BLOC M
		this.currentAction = newState;
	}
	

}
