package com.elemensia.api;

import com.elemensia.api.gameobjects.Decision;
import com.elemensia.api.gameobjects.LivingThing;

public class StateMachine {

	public SubState getDirectionH(LivingThing livingThing, Status status){
		SubState next = status.getState("DIRECTIONH");
		// BLOC F
		switch(status.getState("DIRECTIONH")){
		case LEFT :
			if (!livingThing.getDecisionValue(Decision.RIGHT) && !livingThing.getDecisionValue(Decision.LEFT)){
				next = SubState.NONE;
			}else if (livingThing.getDecisionValue(Decision.RIGHT)){
				next = SubState.RIGHT;
			}

			break;

		case RIGHT :
			if (!livingThing.getDecisionValue(Decision.RIGHT) && !livingThing.getDecisionValue(Decision.LEFT)){
				next = SubState.NONE;
			}else if (livingThing.getDecisionValue(Decision.LEFT)){
				next = SubState.LEFT;
			}
			break;

		case NONE :
			if (livingThing.getDecisionValue(Decision.RIGHT)){
				next = SubState.RIGHT;
			}else if (livingThing.getDecisionValue(Decision.LEFT)){
				next = SubState.LEFT;
			}
			break;
		}


		return next;
	}

	public SubState getDirectionV(LivingThing livingThing, Status status){
		SubState next = status.getState("DIRECTIONV");
		// BLOC F
		switch(status.getState("DIRECTIONV")){
		case NONE :
			if (livingThing.getDecisionValue(Decision.TOP) && status.getState("ACTION") == SubState.CLIMB){
				next = SubState.TOP;
			}
			if (livingThing.getDecisionValue(Decision.TOP) && status.getState("ACTION") == SubState.CLIMB){
				next = SubState.DOWN;
			}
			break;

		case TOP :
			if (!livingThing.getDecisionValue(Decision.TOP)){
				next = SubState.NONE;
			}
			break;

		case DOWN :
			if (!livingThing.getDecisionValue(Decision.TOP)){
				next = SubState.NONE;
			}
			break;
		}

		return next;
	}

	public SubState getAction(LivingThing livingThing, Status status){
		SubState next = status.getState("ACTION");
		// BLOC F
		switch(status.getState("ACTION")){
		case NONE :
			if (livingThing.getDecisionValue(Decision.ATTACK) /*TODO : isAvailble()*/){
				next = SubState.ATTACK;
			}
			else if  (livingThing.getDecisionValue(Decision.EAT)){
				next = SubState.EAT;
			}
			else if  (livingThing.getDecisionValue(Decision.SLEEP) && status.getState("DIRECTION") == SubState.NONE){
				next = SubState.SLEEP;
			}
			else if  (livingThing.getDecisionValue(Decision.CLIMB) /*TODO : isEchelle()*/){
				next = SubState.CLIMB;
			}
			else if  (!livingThing.isAlive()){
				next = SubState.DEAD;
			}
			break;

		case ATTACK :
			if (true/*TODO : isAvailble()*/){
				next = SubState.NONE;
			}else if  (!livingThing.isAlive()){
				next = SubState.DEAD;
			}
			break;

		case EAT :
			if (true/*TODO : isAvailble()*/){
				next = SubState.NONE;
			}else if  (!livingThing.isAlive()){
				next = SubState.DEAD;
			}
			break;

		case SLEEP :
			if (livingThing.getDecisionValue(Decision.SLEEP)){
				next = SubState.NONE;
			}else if  (!livingThing.isAlive()){
				next = SubState.DEAD;
			}
			break;

		case CLIMB :
			if (livingThing.getDecisionValue(Decision.JUMP) || livingThing.getDecisionValue(Decision.CLIMB) /*TODO  || contactGround() */){
				next = SubState.NONE;
			}else if  (!livingThing.isAlive()){
				next = SubState.DEAD;
			}
			break;
		case DEAD :
			if (livingThing.getDecisionValue(Decision.TOP)){
				next = SubState.DEAD;
			}
			break;
		}

		return next;
	}

	public SubState getMovement(LivingThing livingThing, Status status){
		SubState newState = status.getState("MOVEMENT");
		// BLOC F
		switch(status.getState("MOVEMENT")){
		case NONE :
			if (livingThing.getDecisionValue(Decision.JUMP)){
				newState = SubState.JUMP;
			}
			else if  (status.getState("ENVIRONMENT") == SubState.AIR){
				newState = SubState.FALL;
			}
			break;
		case FALL :
			if (status.getState("ENVIRONMENT") == SubState.GROUND){
				newState = SubState.NONE;
			}
			else if  (status.getState("ENVIRONMENT")==SubState.WATER){
				newState = SubState.NONE;
			}
			else if  (livingThing.getDecisionValue(Decision.JUMP)/*TODO: && jumpAvailable()*/){
				newState = SubState.JUMP;
			}
			break;
		case JUMP :
			if (!livingThing.isMovingUp()){
				newState = SubState.FALL;
			}
			else if  (status.getState("ENVIRONMENT")==SubState.WATER){
				newState = SubState.NONE;
			}
			else if  (livingThing.getDecisionValue(Decision.JUMP)/*TODO: && jumpAvailable()*/){
				newState = SubState.JUMP;
			}
			break;

		}

		return newState;
	}

}