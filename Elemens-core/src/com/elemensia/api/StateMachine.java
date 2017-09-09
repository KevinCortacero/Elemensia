package com.elemensia.api;

import com.elemensia.api.gameobjects.LivingThing;

public class StateMachine {
	
	public State getDirectionH(LivingThing livingThing, Status status){
		State next = status.getState("DIRECTIONH");
		// BLOC F
		switch(status.getState("DIRECTIONH")){
		case LEFT :
			if (livingThing.getDecisionValue("RIGHT")){
				next = State.RIGHT;
			}
			break;

		case RIGHT :
			if (livingThing.getDecisionValue("LEFT")){
				next = State.LEFT;
			}
			break;
		}

		return next;
	}
	
	public State getDirectionV(LivingThing livingThing, Status status){
		State next = status.getState("DIRECTIONV");
		// BLOC F
		switch(status.getState("DIRECTIONV")){
		case NONE :
			if (livingThing.getDecisionValue("TOP") && status.getState("ACTION") == State.CLIMB){
				next = State.TOP;
			}
			if (livingThing.getDecisionValue("DOWN") && status.getState("ACTION") == State.CLIMB){
				next = State.DOWN;
			}
			break;

		case TOP :
			if (!livingThing.getDecisionValue("TOP")){
				next = State.NONE;
			}
			break;

		case DOWN :
			if (!livingThing.getDecisionValue("DOWN")){
				next = State.NONE;
			}
			break;
		}

		return next;
	}
	
	public State getAction(LivingThing livingThing, Status status){
		State next = status.getState("ACTION");
		// BLOC F
		switch(status.getState("ACTION")){
		case NONE :
			if (livingThing.getDecisionValue("ATTACK") /*TODO : isAvailble()*/){
				next = State.ATTACK;
			}
			else if  (livingThing.getDecisionValue("EAT")){
				next = State.EAT;
			}
			else if  (livingThing.getDecisionValue("SLEEP") && status.getState("MOVEMENT") == State.IDLE){
				next = State.SLEEP;
			}
			else if  (livingThing.getDecisionValue("CLIMB") /*TODO : isEchelle()*/){
				next = State.CLIMB;
			}
			else if  (!livingThing.isAlive()){
				next = State.DEAD;
			}
			break;

		case ATTACK :
			if (true/*TODO : isAvailble()*/){
				next = State.NONE;
			}else if  (!livingThing.isAlive()){
				next = State.DEAD;
			}
			break;

		case EAT :
			if (true/*TODO : isAvailble()*/){
				next = State.NONE;
			}else if  (!livingThing.isAlive()){
				next = State.DEAD;
			}
			break;

		case SLEEP :
			if (livingThing.getDecisionValue("SLEEP")){
				next = State.NONE;
			}else if  (!livingThing.isAlive()){
				next = State.DEAD;
			}
			break;

		case CLIMB :
			if (livingThing.getDecisionValue("JUMP") || livingThing.getDecisionValue("CLIMB") /*TODO  || contactGround() */){
				next = State.NONE;
			}else if  (!livingThing.isAlive()){
				next = State.DEAD;
			}
			break;
		case DEAD :
			if (livingThing.getDecisionValue("DOWN")){
				next = State.DEAD;
			}
			break;
		}

		return next;
	}

}
