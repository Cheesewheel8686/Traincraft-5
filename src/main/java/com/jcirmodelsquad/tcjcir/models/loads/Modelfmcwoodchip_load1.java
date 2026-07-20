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

public class Modelfmcwoodchip_load1 extends ModelConverter //Same as Filename
{
	int textureX = 256;
	int textureY = 64;

	public Modelfmcwoodchip_load1() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[28];

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
		bodyModel[10] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[11] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[12] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9
		bodyModel[13] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[14] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[15] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9
		bodyModel[16] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[17] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[18] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9
		bodyModel[19] = new ModelRendererTurbo(this, 176, 8, textureX, textureY); // load 5
		bodyModel[20] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 6
		bodyModel[21] = new ModelRendererTurbo(this, 144, 4, textureX, textureY); // load 9
		bodyModel[22] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 3
		bodyModel[23] = new ModelRendererTurbo(this, 0, 2, textureX, textureY); // load 2
		bodyModel[24] = new ModelRendererTurbo(this, 1, 11, textureX, textureY); // load 1
		bodyModel[25] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 3
		bodyModel[26] = new ModelRendererTurbo(this, 0, 2, textureX, textureY); // load 2
		bodyModel[27] = new ModelRendererTurbo(this, 1, 11, textureX, textureY); // load 1

		bodyModel[0].addBox(0F, 0F, 0F, 82, 1, 19, 0F); // Box 154 Cull
		bodyModel[0].setRotationPoint(-41F, -18F, -9.5F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 11, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[1].setRotationPoint(-33F, -18F, -9.5F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 11, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[2].setRotationPoint(-33F, -18F, 2.5F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 11, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[3].setRotationPoint(-33F, -23F, -2.5F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[4].setRotationPoint(-14F, -18F, -2.5F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[5].setRotationPoint(-14F, -23F, -9.5F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[6].setRotationPoint(-14F, -23F, 2.5F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[7].setRotationPoint(-22F, -18F, -2.5F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[8].setRotationPoint(-22F, -23F, 2.5F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[9].setRotationPoint(-22F, -23F, -9.5F);

		bodyModel[10].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[10].setRotationPoint(6F, -18F, -2.5F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[11].setRotationPoint(6F, -23F, 2.5F);

		bodyModel[12].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[12].setRotationPoint(6F, -23F, -9.5F);

		bodyModel[13].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[13].setRotationPoint(-41F, -18F, -2.5F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[14].setRotationPoint(-41F, -23F, -9.5F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[15].setRotationPoint(-41F, -23F, 2.5F);

		bodyModel[16].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[16].setRotationPoint(14F, -18F, -2.5F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[17].setRotationPoint(14F, -23F, -9.5F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[18].setRotationPoint(14F, -23F, 2.5F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 8, 0, 5, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[19].setRotationPoint(33F, -18F, -2.5F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[20].setRotationPoint(33F, -23F, 2.5F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[21].setRotationPoint(33F, -23F, -9.5F);

		bodyModel[22].addShapeBox(0F, 0F, 0F, 11, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[22].setRotationPoint(22F, -18F, -9.5F);

		bodyModel[23].addShapeBox(0F, 0F, 0F, 11, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[23].setRotationPoint(22F, -18F, 2.5F);

		bodyModel[24].addShapeBox(0F, 0F, 0F, 11, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[24].setRotationPoint(22F, -23F, -2.5F);

		bodyModel[25].addShapeBox(0F, 0F, 0F, 12, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[25].setRotationPoint(-6F, -18F, -9.5F);

		bodyModel[26].addShapeBox(0F, 0F, 0F, 12, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[26].setRotationPoint(-6F, -18F, 2.5F);

		bodyModel[27].addShapeBox(0F, 0F, 0F, 12, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[27].setRotationPoint(-6F, -23F, -2.5F);
	}
}