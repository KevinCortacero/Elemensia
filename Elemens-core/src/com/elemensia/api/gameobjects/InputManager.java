package com.elemensia.api.gameobjects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {

	private Map<String, Boolean> inputs;
	
	public InputManager() {
		this.inputs = new HashMap<>();
		this.inputs.put("RIGHT", false);
		this.inputs.put("LEFT", false);
		this.inputs.put("TOP", false);
		this.inputs.put("DOWN", false);
	}
	
	public void updateInputs(){
		this.setInputKeyValue("RIGHT", Input.Keys.D);
		this.setInputKeyValue("LEFT", Input.Keys.Q);
		this.setInputKeyValue("TOP", Input.Keys.Z);
		this.setInputKeyValue("DOWN", Input.Keys.S);
	}
	private void setInputKeyValue(String inputName, int keyPressed) {
		this.inputs.put(inputName, Gdx.input.isKeyPressed(keyPressed));
	}

	public boolean getInputValue(String inputName) {
		return this.inputs.get(inputName);
	}
}
