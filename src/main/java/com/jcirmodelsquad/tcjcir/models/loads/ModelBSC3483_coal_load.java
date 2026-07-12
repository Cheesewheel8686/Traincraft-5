//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 02.05.2026 - 23:33:23
// Last changed on: 02.05.2026 - 23:33:23

package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class ModelBSC3483_coal_load extends ModelConverter //Same as Filename
{
	int textureX = 64;
	int textureY = 64;

	public ModelBSC3483_coal_load() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[27];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);


		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 3
		bodyModel[1] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 2
		bodyModel[2] = new ModelRendererTurbo(this, 3, 11, textureX, textureY); // load 1
		bodyModel[3] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[4] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[5] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9
		bodyModel[6] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[7] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[8] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9
		bodyModel[9] = new ModelRendererTurbo(this, 1, 19, textureX, textureY); // load 3
		bodyModel[10] = new ModelRendererTurbo(this, 1, 19, textureX, textureY); // load 2
		bodyModel[11] = new ModelRendererTurbo(this, 34, 29, textureX, textureY); // load 1
		bodyModel[12] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[13] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[14] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9
		bodyModel[15] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[16] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[17] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9
		bodyModel[18] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[19] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[20] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9
		bodyModel[21] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 3
		bodyModel[22] = new ModelRendererTurbo(this, 2, 2, textureX, textureY); // load 2
		bodyModel[23] = new ModelRendererTurbo(this, 3, 11, textureX, textureY); // load 1
		bodyModel[24] = new ModelRendererTurbo(this, 35, 19, textureX, textureY); // load 5
		bodyModel[25] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 6
		bodyModel[26] = new ModelRendererTurbo(this, 31, 3, textureX, textureY); // load 9

		bodyModel[0].addShapeBox(0F, 0F, 0F, 7, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[0].setRotationPoint(-27.5F, -10F, -10F);

		bodyModel[1].addShapeBox(0F, 0F, 0F, 7, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[1].setRotationPoint(-27.5F, -10F, 3F);

		bodyModel[2].addShapeBox(0F, 0F, 0F, 7, 0, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[2].setRotationPoint(-27.5F, -15F, -3F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[3].setRotationPoint(-12.5F, -10F, -3F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[4].setRotationPoint(-12.5F, -15F, -10F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[5].setRotationPoint(-12.5F, -15F, 3F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[6].setRotationPoint(-20.5F, -10F, -3F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[7].setRotationPoint(-20.5F, -15F, 3F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[8].setRotationPoint(-20.5F, -15F, -10F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 9, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[9].setRotationPoint(-4.5F, -10F, -10F);

		bodyModel[10].addShapeBox(0F, 0F, 0F, 9, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[10].setRotationPoint(-4.5F, -10F, 3F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 9, 0, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[11].setRotationPoint(-4.5F, -15F, -3F);

		bodyModel[12].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[12].setRotationPoint(4.5F, -10F, -3F);

		bodyModel[13].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[13].setRotationPoint(4.5F, -15F, 3F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[14].setRotationPoint(4.5F, -15F, -10F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[15].setRotationPoint(-35.5F, -10F, -3F);

		bodyModel[16].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[16].setRotationPoint(-35.5F, -15F, -10F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[17].setRotationPoint(-35.5F, -15F, 3F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 5
		bodyModel[18].setRotationPoint(12.5F, -10F, -3F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[19].setRotationPoint(12.5F, -15F, -10F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,-8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, -8F, 0F, -7F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 9
		bodyModel[20].setRotationPoint(12.5F, -15F, 3F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 7, 0, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F); // load 3
		bodyModel[21].setRotationPoint(20.5F, -10F, -10F);

		bodyModel[22].addShapeBox(0F, 0F, 0F, 7, 0, 7, 0F,0F, 5F, 0F, 0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 2
		bodyModel[22].setRotationPoint(20.5F, -10F, 3F);

		bodyModel[23].addShapeBox(0F, 0F, 0F, 7, 0, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 1
		bodyModel[23].setRotationPoint(20.5F, -15F, -3F);

		bodyModel[24].addShapeBox(0F, 0F, 0F, 8, 0, 6, 0F,0F, 5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 5F, 0F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 5
		bodyModel[24].setRotationPoint(27.5F, -10F, -3F);

		bodyModel[25].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, 0F, -8F, 0F, 0F, -8F, 0F, -7F, 0F, 0F, -7F, 0F, -5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // load 6
		bodyModel[25].setRotationPoint(27.5F, -15F, 3F);

		bodyModel[26].addShapeBox(0F, 0F, 0F, 8, 5, 7, 0F,0F, 0F, -7F, -8F, 0F, -7F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F); // load 9
		bodyModel[26].setRotationPoint(27.5F, -15F, -10F);
	}
}