package com.elemensia.game;

import com.elemensia.api.Environment;
import com.elemensia.api.gameobjects.Solid;
import com.elemensia.api.gameobjects.WaterArea;

public class MainWorldMap extends Environment {

	public MainWorldMap(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void loadSolids() {
		this.addSolid(new Solid(0, 0, 1980, 280));
		this.addSolid(new Solid(2260, 0, 600, 280));

		this.addSolid(new Solid(0, 570, 880, 140));
		this.addSolid(new Solid(975, 570, 300, 140));

		this.addSolid(new Solid(1270, 683, 30, 30));
		this.addSolid(new Solid(1298, 677, 30, 30));
		this.addSolid(new Solid(1328, 671, 30, 30));
		this.addSolid(new Solid(1356, 665, 30, 30));
		this.addSolid(new Solid(1384, 660, 30, 30));
		this.addSolid(new Solid(1414, 658, 30, 30));
		this.addSolid(new Solid(1444, 656, 30, 30));

		this.addSolid(new Solid(1474, 656, 30, 30));
		this.addSolid(new Solid(1502, 658, 30, 30));
		this.addSolid(new Solid(1532, 660, 30, 30));
		this.addSolid(new Solid(1558, 664, 30, 30));
		this.addSolid(new Solid(1586, 669, 30, 30));
		this.addSolid(new Solid(1616, 675, 30, 30));
		this.addSolid(new Solid(1644, 682, 30, 30));
		this.addSolid(new Solid(1674, 685, 30, 30));

		this.addSolid(new Solid(1704, 570, 1130, 140));

	}

	@Override
	public void loadWater() {
		this.addWater(new WaterArea(1980, 0, 280, 260));
	}

	

}
