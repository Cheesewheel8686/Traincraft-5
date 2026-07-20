//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: 
// Model Creator: 
// Created on: 11.07.2026 - 23:46:28
// Last changed on: 11.07.2026 - 23:46:28

package com.jcirmodelsquad.tcjcir.models.loads; //Path where the model is located

import tmt.ModelConverter;
import tmt.ModelRendererTurbo;

public class Modelfmcwoodchip_load3 extends ModelConverter //Same as Filename
{
	int textureX = 256;
	int textureY = 64;

	public Modelfmcwoodchip_load3() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[1];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, -1, 17, textureX, textureY); // Box 154

		bodyModel[0].addBox(0F, 0F, 0F, 85, 0, 22, 0F); // Box 154
		bodyModel[0].setRotationPoint(-42.5F, -19.02F, -11F);
	}
}