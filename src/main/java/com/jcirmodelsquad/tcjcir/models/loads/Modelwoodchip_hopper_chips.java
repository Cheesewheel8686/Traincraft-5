//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: woodchiphopper_chips
// Model Creator: 
// Created on: 19.07.2026 - 22:35:42
// Last changed on: 19.07.2026 - 22:35:42

package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class Modelwoodchip_hopper_chips extends ModelConverter //Same as Filename
{
	int textureX = 256;
	int textureY = 32;

	public Modelwoodchip_hopper_chips() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[13];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, -6, 1, textureX, textureY); // load 3
		bodyModel[1] = new ModelRendererTurbo(this, -6, 9, textureX, textureY); // load 2
		bodyModel[2] = new ModelRendererTurbo(this, -5, 17, textureX, textureY); // load 1
		bodyModel[3] = new ModelRendererTurbo(this, 145, 17, textureX, textureY); // load 4
		bodyModel[4] = new ModelRendererTurbo(this, 128, 17, textureX, textureY); // load 5
		bodyModel[5] = new ModelRendererTurbo(this, 144, 1, textureX, textureY); // Box 10
		bodyModel[6] = new ModelRendererTurbo(this, 127, 1, textureX, textureY); // Box 9
		bodyModel[7] = new ModelRendererTurbo(this, 144, 9, textureX, textureY); // Box 10
		bodyModel[8] = new ModelRendererTurbo(this, 127, 9, textureX, textureY); // Box 11
		bodyModel[9] = new ModelRendererTurbo(this, 127, 9, textureX, textureY); // Box 12
		bodyModel[10] = new ModelRendererTurbo(this, 144, 9, textureX, textureY); // Box 13
		bodyModel[11] = new ModelRendererTurbo(this, 144, 1, textureX, textureY); // Box 14
		bodyModel[12] = new ModelRendererTurbo(this, 127, 1, textureX, textureY); // Box 15

		bodyModel[0].addShapeBox(0F, 0F, 0F, 66, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[0].setRotationPoint(-33F, -19F, -10F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 66, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[1].setRotationPoint(-33F, -19F, 3F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 66, 0, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[2].setRotationPoint(-33F, -24F, -3F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 4
		bodyModel[3].setRotationPoint(-41F, -19F, -3F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[4].setRotationPoint(33F, -19F, -3F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 5F, -7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, -7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // Box 10
		bodyModel[5].setRotationPoint(33F, -19F, -10F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, -8F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -8F, -5F, 0F, 0F, -5F, 0F); // Box 9
		bodyModel[6].setRotationPoint(33F, -19F, -10F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, -7F); // Box 10
		bodyModel[7].setRotationPoint(33F, -19F, 3F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 5F, 0F, -8F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -8F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 11
		bodyModel[8].setRotationPoint(33F, -19F, 3F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, -7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, -7F, 0F, 0F, 0F); // Box 12
		bodyModel[9].setRotationPoint(-41F, -19F, 3F);

		bodyModel[10].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,-8F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -8F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 13
		bodyModel[10].setRotationPoint(-41F, -19F, 3F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, -8F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -8F, -5F, 0F); // Box 14
		bodyModel[11].setRotationPoint(-41F, -19F, -10F);

		bodyModel[12].addShapeBox(0F, 0F, 0F, 8, 0, 7, 0F,0F, 0F, 0F, 0F, 5F, -7F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, -7F, 0F, -5F, 0F, 0F, 0F, 0F); // Box 15
		bodyModel[12].setRotationPoint(-41F, -19F, -10F);
	}
}