//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 20.07.2026 - 08:30:56
// Last changed on: 20.07.2026 - 08:30:56
package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class Modelorejenny_load extends ModelConverter//Same as Filename
{
	int textureX = 64;
	int textureY = 32;

	public Modelorejenny_load() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[1];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, -19, 1, textureX, textureY); // Box 0 load

		bodyModel[0].addShapeBox(0F, 0F, 0F, 28, 0, 20, 0F,0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0F, 0F, 0.05F); // Box 0 load
		bodyModel[0].setRotationPoint(-14F, -8.75F, -10F);
	}
}