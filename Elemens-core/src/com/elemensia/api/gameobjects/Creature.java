package com.elemensia.api.gameobjects;

import com.elemensia.api.Animation;
import com.elemensia.api.State;
import com.elemensia.game.World;

public abstract class Creature extends LivingThing{

	private Thread alive;

	public Creature(int x, int y, int width, int height, int health, Animation animations) {
		super(x, y, width, height, health, animations);
		this.alive = new Thread(new Runnable() {

			@Override
			public void run() {
				
				float delta = 1 / 60.0f;
				int delay = (int) (delta * 1000);
				System.out.println(delta + " " + delay);
				do {
					Creature.this.update(-9.8f, delta);
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}while(Creature.this.isAlive());

			}

		});
	}
	
	public void live(){
		this.alive.start();
	}

	public void eat(){
		this.animations.setAnimation(State.EATING, false);
	}

	@Override
	public void update(float gravity, float delta) {
		super.update(gravity, delta);
		World.updateColliding(this);
		this.takeDecision(delta);
	}
	public abstract void takeDecision(float delta);

}
