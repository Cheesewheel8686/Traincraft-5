//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 11.07.2026 - 23:04:24
// Last changed on: 11.07.2026 - 23:04:24

package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class Modelfmcwoodchip_load2 extends ModelConverter //Same as Filename
{
	int textureX = 256;
	int textureY = 64;

	public Modelfmcwoodchip_load2() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[10];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 2, 18, textureX, textureY); // Box 154 Cull
		bodyModel[1] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 3
		bodyModel[2] = new ModelRendererTurbo(this, 0, 2, textureX, textureY); // load 2
		bodyModel[3] = new ModelRendererTurbo(this, 1, 11, textureX, textureY); // load 1
		bodyModel[4] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[5] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[6] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9
		bodyModel[7] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[8] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[9] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9

		bodyModel[0].addBox(0F, 0F, 0F, 82, 1, 19, 0F); // Box 154 Cull
		bodyModel[0].setRotationPoint(-41F, -18F, -9.5F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 66, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[1].setRotationPoint(-33F, -18F, -9.5F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 66, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[2].setRotationPoint(-33F, -18F, 2.5F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 66, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[3].setRotationPoint(-33F, -23F, -2.5F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[4].setRotationPoint(-41F, -18F, -2.5F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[5].setRotationPoint(-41F, -23F, -9.5F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[6].setRotationPoint(-41F, -23F, 2.5F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[7].setRotationPoint(33F, -18F, -2.5F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[8].setRotationPoint(33F, -23F, 2.5F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[9].setRotationPoint(33F, -23F, -9.5F);
	}
}