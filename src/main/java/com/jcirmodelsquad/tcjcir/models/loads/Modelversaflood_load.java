//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: versaflood_load
// Model Creator: 
// Created on: 19.07.2026 - 23:13:28
// Last changed on: 19.07.2026 - 23:13:28
package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class Modelversaflood_load extends ModelConverter //Same as Filename
{
	int textureX = 128;
	int textureY = 32;

	public Modelversaflood_load() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[1];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 1, 1, textureX, textureY); // Box 70 LOAD TILE

		bodyModel[0].addBox(0F, 0F, 0F, 40, 1, 18, 0F); // Box 70 LOAD TILE
		bodyModel[0].setRotationPoint(-20F, -15F, -9F);
	}
}