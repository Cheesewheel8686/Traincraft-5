//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2026 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: BSC 3483 test
// Model Creator: 
// Created on: 11.11.2023 - 19:57:49
// Last changed on: 11.11.2023 - 19:57:49

package com.jcirmodelsquad.tcjcir.models.trains; //Path where the model is located

import com.jcirmodelsquad.tcjcir.models.trucks.Model70TonTruck2;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tmt.ModelConverter;
import tmt.ModelRendererTurbo;
import tmt.Tessellator;
import train.client.renderhelper.ModelRenderHelper;
import train.common.api.AbstractTrains;
import train.common.api.Freight;
import train.common.library.Info;

public class ModelBSC3483 extends ModelConverter //Same as Filename
{
	int textureX = 512;
	int textureY = 256;

	public ModelBSC3483() //Same as Filename
	{
		bodyModel = new ModelRendererTurbo[260];

		initbodyModel_1();

		translateAll(0F, 0F, 0F);

		flipAll();
	}

	private void initbodyModel_1()
	{
		bodyModel[0] = new ModelRendererTurbo(this, 1, 92, textureX, textureY); // Box 0
		bodyModel[1] = new ModelRendererTurbo(this, 1, 92, textureX, textureY); // Box 0
		bodyModel[2] = new ModelRendererTurbo(this, 16, 4, textureX, textureY); // Box 130
		bodyModel[3] = new ModelRendererTurbo(this, 208, 150, textureX, textureY); // Box 0
		bodyModel[4] = new ModelRendererTurbo(this, 64, 170, textureX, textureY); // Box 256
		bodyModel[5] = new ModelRendererTurbo(this, 68, 126, textureX, textureY); // Box 74
		bodyModel[6] = new ModelRendererTurbo(this, 85, 126, textureX, textureY); // Box 74
		bodyModel[7] = new ModelRendererTurbo(this, 252, 155, textureX, textureY); // Box 0
		bodyModel[8] = new ModelRendererTurbo(this, 100, 126, textureX, textureY); // Box 74
		bodyModel[9] = new ModelRendererTurbo(this, 93, 126, textureX, textureY); // Box 74
		bodyModel[10] = new ModelRendererTurbo(this, 189, 165, textureX, textureY); // Box 74
		bodyModel[11] = new ModelRendererTurbo(this, 69, 148, textureX, textureY); // Box 179
		bodyModel[12] = new ModelRendererTurbo(this, 87, 148, textureX, textureY); // Box 181
		bodyModel[13] = new ModelRendererTurbo(this, 158, 158, textureX, textureY); // Box 183
		bodyModel[14] = new ModelRendererTurbo(this, 103, 148, textureX, textureY); // Box 186
		bodyModel[15] = new ModelRendererTurbo(this, 95, 148, textureX, textureY); // Box 188
		bodyModel[16] = new ModelRendererTurbo(this, 189, 158, textureX, textureY); // Box 190
		bodyModel[17] = new ModelRendererTurbo(this, 123, 126, textureX, textureY); // Box 0
		bodyModel[18] = new ModelRendererTurbo(this, 87, 170, textureX, textureY); // Box 0
		bodyModel[19] = new ModelRendererTurbo(this, 87, 170, textureX, textureY); // Box 256
		bodyModel[20] = new ModelRendererTurbo(this, 41, 170, textureX, textureY); // Box 0
		bodyModel[21] = new ModelRendererTurbo(this, 41, 170, textureX, textureY); // Box 256
		bodyModel[22] = new ModelRendererTurbo(this, 69, 133, textureX, textureY); // Box 74 middle door part
		bodyModel[23] = new ModelRendererTurbo(this, 89, 133, textureX, textureY); // Box 74 rear door part
		bodyModel[24] = new ModelRendererTurbo(this, 67, 156, textureX, textureY); // Box 74 middle door part
		bodyModel[25] = new ModelRendererTurbo(this, 88, 156, textureX, textureY); // Box 74 rear door part
		bodyModel[26] = new ModelRendererTurbo(this, 133, 172, textureX, textureY); // Box 74 middle door part
		bodyModel[27] = new ModelRendererTurbo(this, 16, 1, textureX, textureY); // Box 0
		bodyModel[28] = new ModelRendererTurbo(this, 16, 64, textureX, textureY); // Box 300
		bodyModel[29] = new ModelRendererTurbo(this, 203, 63, textureX, textureY); // Box 0
		bodyModel[30] = new ModelRendererTurbo(this, 177, 61, textureX, textureY); // Box 0
		bodyModel[31] = new ModelRendererTurbo(this, 116, 34, textureX, textureY); // Box 0
		bodyModel[32] = new ModelRendererTurbo(this, 161, 2, textureX, textureY); // Box 0
		bodyModel[33] = new ModelRendererTurbo(this, 175, 165, textureX, textureY); // Box 74
		bodyModel[34] = new ModelRendererTurbo(this, 182, 165, textureX, textureY); // Box 74
		bodyModel[35] = new ModelRendererTurbo(this, 175, 158, textureX, textureY); // Box 185
		bodyModel[36] = new ModelRendererTurbo(this, 182, 158, textureX, textureY); // Box 191
		bodyModel[37] = new ModelRendererTurbo(this, 158, 165, textureX, textureY); // Box 74
		bodyModel[38] = new ModelRendererTurbo(this, 219, 172, textureX, textureY); // Box 74  rear door part
		bodyModel[39] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[40] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[41] = new ModelRendererTurbo(this, 118, 7, textureX, textureY); // Box 0
		bodyModel[42] = new ModelRendererTurbo(this, 159, 39, textureX, textureY); // Box 0
		bodyModel[43] = new ModelRendererTurbo(this, 81, 26, textureX, textureY); // Box 130
		bodyModel[44] = new ModelRendererTurbo(this, 81, 53, textureX, textureY); // Box 130
		bodyModel[45] = new ModelRendererTurbo(this, 51, 148, textureX, textureY); // Box 74
		bodyModel[46] = new ModelRendererTurbo(this, 44, 148, textureX, textureY); // Box 74
		bodyModel[47] = new ModelRendererTurbo(this, 252, 134, textureX, textureY); // Box 0
		bodyModel[48] = new ModelRendererTurbo(this, 18, 148, textureX, textureY); // Box 74
		bodyModel[49] = new ModelRendererTurbo(this, 36, 148, textureX, textureY); // Box 74
		bodyModel[50] = new ModelRendererTurbo(this, 110, 165, textureX, textureY); // Box 74
		bodyModel[51] = new ModelRendererTurbo(this, 51, 126, textureX, textureY); // Box 179
		bodyModel[52] = new ModelRendererTurbo(this, 44, 126, textureX, textureY); // Box 181
		bodyModel[53] = new ModelRendererTurbo(this, 141, 165, textureX, textureY); // Box 183
		bodyModel[54] = new ModelRendererTurbo(this, 20, 126, textureX, textureY); // Box 186
		bodyModel[55] = new ModelRendererTurbo(this, 37, 126, textureX, textureY); // Box 188
		bodyModel[56] = new ModelRendererTurbo(this, 110, 158, textureX, textureY); // Box 190
		bodyModel[57] = new ModelRendererTurbo(this, 18, 170, textureX, textureY); // Box 0
		bodyModel[58] = new ModelRendererTurbo(this, 18, 170, textureX, textureY); // Box 256
		bodyModel[59] = new ModelRendererTurbo(this, 64, 170, textureX, textureY); // Box 0
		bodyModel[60] = new ModelRendererTurbo(this, 21, 133, textureX, textureY); // Box 74 middle door part
		bodyModel[61] = new ModelRendererTurbo(this, 44, 133, textureX, textureY); // Box 74 rear door part
		bodyModel[62] = new ModelRendererTurbo(this, 20, 156, textureX, textureY); // Box 74 middle door part
		bodyModel[63] = new ModelRendererTurbo(this, 45, 156, textureX, textureY); // Box 74 rear door part
		bodyModel[64] = new ModelRendererTurbo(this, 91, 172, textureX, textureY); // Box 74 middle door part
		bodyModel[65] = new ModelRendererTurbo(this, 127, 158, textureX, textureY); // Box 74
		bodyModel[66] = new ModelRendererTurbo(this, 134, 165, textureX, textureY); // Box 74
		bodyModel[67] = new ModelRendererTurbo(this, 127, 165, textureX, textureY); // Box 185
		bodyModel[68] = new ModelRendererTurbo(this, 134, 158, textureX, textureY); // Box 191
		bodyModel[69] = new ModelRendererTurbo(this, 141, 158, textureX, textureY); // Box 190
		bodyModel[70] = new ModelRendererTurbo(this, 176, 172, textureX, textureY); // Box 74  rear door part
		bodyModel[71] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[72] = new ModelRendererTurbo(this, 252, 113, textureX, textureY); // Box 0
		bodyModel[73] = new ModelRendererTurbo(this, 252, 92, textureX, textureY); // Box 0
		bodyModel[74] = new ModelRendererTurbo(this, 81, 45, textureX, textureY); // Box 130
		bodyModel[75] = new ModelRendererTurbo(this, 81, 34, textureX, textureY); // Box 130
		bodyModel[76] = new ModelRendererTurbo(this, 98, 26, textureX, textureY); // Box 130
		bodyModel[77] = new ModelRendererTurbo(this, 98, 53, textureX, textureY); // Box 130
		bodyModel[78] = new ModelRendererTurbo(this, 98, 45, textureX, textureY); // Box 130
		bodyModel[79] = new ModelRendererTurbo(this, 98, 34, textureX, textureY); // Box 130
		bodyModel[80] = new ModelRendererTurbo(this, 141, 99, textureX, textureY); // Box 0
		bodyModel[81] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[82] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[83] = new ModelRendererTurbo(this, 141, 99, textureX, textureY); // Box 0
		bodyModel[84] = new ModelRendererTurbo(this, 141, 99, textureX, textureY); // Box 0
		bodyModel[85] = new ModelRendererTurbo(this, 141, 99, textureX, textureY); // Box 0
		bodyModel[86] = new ModelRendererTurbo(this, 16, 26, textureX, textureY); // Box 133
		bodyModel[87] = new ModelRendererTurbo(this, 16, 91, textureX, textureY); // Box 0
		bodyModel[88] = new ModelRendererTurbo(this, 141, 81, textureX, textureY); // Box 0
		bodyModel[89] = new ModelRendererTurbo(this, 141, 78, textureX, textureY); // Box 214
		bodyModel[90] = new ModelRendererTurbo(this, 141, 85, textureX, textureY); // Box 0
		bodyModel[91] = new ModelRendererTurbo(this, 141, 85, textureX, textureY); // Box 158
		bodyModel[92] = new ModelRendererTurbo(this, 141, 85, textureX, textureY); // Box 0
		bodyModel[93] = new ModelRendererTurbo(this, 141, 85, textureX, textureY); // Box 158
		bodyModel[94] = new ModelRendererTurbo(this, 141, 75, textureX, textureY); // Box 0
		bodyModel[95] = new ModelRendererTurbo(this, 141, 72, textureX, textureY); // Box 214
		bodyModel[96] = new ModelRendererTurbo(this, 110, 98, textureX, textureY); // Box 0
		bodyModel[97] = new ModelRendererTurbo(this, 141, 107, textureX, textureY); // Box 0
		bodyModel[98] = new ModelRendererTurbo(this, 141, 107, textureX, textureY); // Box 0
		bodyModel[99] = new ModelRendererTurbo(this, 141, 107, textureX, textureY); // Box 0
		bodyModel[100] = new ModelRendererTurbo(this, 141, 107, textureX, textureY); // Box 0
		bodyModel[101] = new ModelRendererTurbo(this, 207, 95, textureX, textureY); // Box 0
		bodyModel[102] = new ModelRendererTurbo(this, 162, 95, textureX, textureY); // Box 0
		bodyModel[103] = new ModelRendererTurbo(this, 191, 150, textureX, textureY); // Box 0
		bodyModel[104] = new ModelRendererTurbo(this, 174, 150, textureX, textureY); // Box 0
		bodyModel[105] = new ModelRendererTurbo(this, 157, 150, textureX, textureY); // Box 0
		bodyModel[106] = new ModelRendererTurbo(this, 140, 150, textureX, textureY); // Box 0
		bodyModel[107] = new ModelRendererTurbo(this, 123, 150, textureX, textureY); // Box 0
		bodyModel[108] = new ModelRendererTurbo(this, 42, 100, textureX, textureY); // Box 172
		bodyModel[109] = new ModelRendererTurbo(this, 42, 100, textureX, textureY); // Box 172
		bodyModel[110] = new ModelRendererTurbo(this, 42, 100, textureX, textureY); // Box 172
		bodyModel[111] = new ModelRendererTurbo(this, 16, 23, textureX, textureY); // Box 0 cull
		bodyModel[112] = new ModelRendererTurbo(this, 216, 90, textureX, textureY, "cull"); // Box 144 shaker bracket cull
		bodyModel[113] = new ModelRendererTurbo(this, 269, 25, textureX, textureY, "cull"); // Interior Brackets cull
		bodyModel[114] = new ModelRendererTurbo(this, 269, 25, textureX, textureY, "cull"); // Interior Brackets cull
		bodyModel[115] = new ModelRendererTurbo(this, 131, 35, textureX, textureY); // Box 133
		bodyModel[116] = new ModelRendererTurbo(this, 116, 35, textureX, textureY); // Box 133
		bodyModel[117] = new ModelRendererTurbo(this, 142, 5, textureX, textureY); // Box 133
		bodyModel[118] = new ModelRendererTurbo(this, 177, 76, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[119] = new ModelRendererTurbo(this, 182, 48, textureX, textureY, "cull"); // grab iron cull
		bodyModel[120] = new ModelRendererTurbo(this, 147, 5, textureX, textureY); // Box 253
		bodyModel[121] = new ModelRendererTurbo(this, 239, 32, textureX, textureY, "cull"); // grab iron cull
		bodyModel[122] = new ModelRendererTurbo(this, 121, 35, textureX, textureY); // Box 253
		bodyModel[123] = new ModelRendererTurbo(this, 182, 45, textureX, textureY, "cull"); // Box 101 cull
		bodyModel[124] = new ModelRendererTurbo(this, 144, 42, textureX, textureY); // Box 0
		bodyModel[125] = new ModelRendererTurbo(this, 144, 48, textureX, textureY); // Box 242
		bodyModel[126] = new ModelRendererTurbo(this, 184, 36, textureX, textureY); // Box 216
		bodyModel[127] = new ModelRendererTurbo(this, 21, 26, textureX, textureY); // Box 133
		bodyModel[128] = new ModelRendererTurbo(this, 36, 26, textureX, textureY); // Box 133
		bodyModel[129] = new ModelRendererTurbo(this, 46, 26, textureX, textureY); // Box 133
		bodyModel[130] = new ModelRendererTurbo(this, 41, 26, textureX, textureY); // Box 133
		bodyModel[131] = new ModelRendererTurbo(this, 31, 26, textureX, textureY); // Box 133
		bodyModel[132] = new ModelRendererTurbo(this, 76, 26, textureX, textureY); // Box 133
		bodyModel[133] = new ModelRendererTurbo(this, 71, 26, textureX, textureY); // Box 133
		bodyModel[134] = new ModelRendererTurbo(this, 56, 26, textureX, textureY); // Box 133
		bodyModel[135] = new ModelRendererTurbo(this, 51, 26, textureX, textureY); // Box 133
		bodyModel[136] = new ModelRendererTurbo(this, 66, 26, textureX, textureY); // Box 133
		bodyModel[137] = new ModelRendererTurbo(this, 61, 26, textureX, textureY); // Box 133
		bodyModel[138] = new ModelRendererTurbo(this, 155, 35, textureX, textureY); // Box 0
		bodyModel[139] = new ModelRendererTurbo(this, 217, 50, textureX, textureY); // Box 263
		bodyModel[140] = new ModelRendererTurbo(this, 208, 49, textureX, textureY); // Box 264
		bodyModel[141] = new ModelRendererTurbo(this, 219, 50, textureX, textureY); // Box 265
		bodyModel[142] = new ModelRendererTurbo(this, 126, 35, textureX, textureY); // Box 253
		bodyModel[143] = new ModelRendererTurbo(this, 139, 38, textureX, textureY); // Box 253
		bodyModel[144] = new ModelRendererTurbo(this, 143, 30, textureX, textureY); // Box 43
		bodyModel[145] = new ModelRendererTurbo(this, 183, 39, textureX, textureY, "cull"); // Box 0 cull
		bodyModel[146] = new ModelRendererTurbo(this, 76, 45, textureX, textureY); // Box 133
		bodyModel[147] = new ModelRendererTurbo(this, 16, 86, textureX, textureY, "cull"); // sill flange cull
		bodyModel[148] = new ModelRendererTurbo(this, 71, 45, textureX, textureY); // Box 133
		bodyModel[149] = new ModelRendererTurbo(this, 56, 45, textureX, textureY); // Box 133
		bodyModel[150] = new ModelRendererTurbo(this, 46, 45, textureX, textureY); // Box 133
		bodyModel[151] = new ModelRendererTurbo(this, 51, 45, textureX, textureY); // Box 133
		bodyModel[152] = new ModelRendererTurbo(this, 66, 45, textureX, textureY); // Box 133
		bodyModel[153] = new ModelRendererTurbo(this, 61, 45, textureX, textureY); // Box 133
		bodyModel[154] = new ModelRendererTurbo(this, 16, 45, textureX, textureY); // Box 133
		bodyModel[155] = new ModelRendererTurbo(this, 21, 45, textureX, textureY); // Box 133
		bodyModel[156] = new ModelRendererTurbo(this, 36, 45, textureX, textureY); // Box 133
		bodyModel[157] = new ModelRendererTurbo(this, 41, 45, textureX, textureY); // Box 133
		bodyModel[158] = new ModelRendererTurbo(this, 26, 45, textureX, textureY); // Box 133
		bodyModel[159] = new ModelRendererTurbo(this, 31, 45, textureX, textureY); // Box 133
		bodyModel[160] = new ModelRendererTurbo(this, 159, 47, textureX, textureY, "cull"); // Box 263 cull
		bodyModel[161] = new ModelRendererTurbo(this, 16, 67, textureX, textureY); // Box 130
		bodyModel[162] = new ModelRendererTurbo(this, 201, 90, textureX, textureY, "cull"); // Box 144 shaker bracket cull
		bodyModel[163] = new ModelRendererTurbo(this, 159, 53, textureX, textureY); // Box 263
		bodyModel[164] = new ModelRendererTurbo(this, 159, 56, textureX, textureY, "cull"); // Box 263 cull
		bodyModel[165] = new ModelRendererTurbo(this, 171, 90, textureX, textureY, "cull"); // Box 144 shaker bracket cull
		bodyModel[166] = new ModelRendererTurbo(this, 159, 50, textureX, textureY); // Box 263
		bodyModel[167] = new ModelRendererTurbo(this, 186, 90, textureX, textureY, "cull"); // Box 144 shaker bracket cull
		bodyModel[168] = new ModelRendererTurbo(this, 159, 44, textureX, textureY); // Box 263
		bodyModel[169] = new ModelRendererTurbo(this, 159, 41, textureX, textureY, "cull"); // Box 263 cull
		bodyModel[170] = new ModelRendererTurbo(this, 182, 54, textureX, textureY, "cull"); // grab iron cull
		bodyModel[171] = new ModelRendererTurbo(this, 182, 51, textureX, textureY, "cull"); // Box 101 cull
		bodyModel[172] = new ModelRendererTurbo(this, 174, 29, textureX, textureY, "cull"); // grab iron cull
		bodyModel[173] = new ModelRendererTurbo(this, 152, 5, textureX, textureY); // Box 253
		bodyModel[174] = new ModelRendererTurbo(this, 218, 21, textureX, textureY, "cull"); // Box 0 cull
		bodyModel[175] = new ModelRendererTurbo(this, 209, 22, textureX, textureY, "cull"); // Box 252 cull
		bodyModel[176] = new ModelRendererTurbo(this, 199, 34, textureX, textureY); // Box 251
		bodyModel[177] = new ModelRendererTurbo(this, 236, 21, textureX, textureY, "cull"); // Box 0 cull
		bodyModel[178] = new ModelRendererTurbo(this, 227, 22, textureX, textureY, "cull"); // Box 252 cull
		bodyModel[179] = new ModelRendererTurbo(this, 199, 47, textureX, textureY); // Box 251
		bodyModel[180] = new ModelRendererTurbo(this, 162, 9, textureX, textureY); // Box 0
		bodyModel[181] = new ModelRendererTurbo(this, 162, 3, textureX, textureY); // Box 242
		bodyModel[182] = new ModelRendererTurbo(this, 202, 44, textureX, textureY); // Box 96
		bodyModel[183] = new ModelRendererTurbo(this, 217, 42, textureX, textureY); // Box 215
		bodyModel[184] = new ModelRendererTurbo(this, 208, 41, textureX, textureY); // Box 216
		bodyModel[185] = new ModelRendererTurbo(this, 219, 42, textureX, textureY); // Box 216
		bodyModel[186] = new ModelRendererTurbo(this, 202, 31, textureX, textureY); // Box 96
		bodyModel[187] = new ModelRendererTurbo(this, 251, 45, textureX, textureY, "cull"); // Brace Cull
		bodyModel[188] = new ModelRendererTurbo(this, 240, 23, textureX, textureY, "cull"); // Brace Cull
		bodyModel[189] = new ModelRendererTurbo(this, 254, 24, textureX, textureY, "cull"); // brace cull
		bodyModel[190] = new ModelRendererTurbo(this, 278, 21, textureX, textureY, "cull"); // brace cull
		bodyModel[191] = new ModelRendererTurbo(this, 264, 30, textureX, textureY, "cull"); // brace cull
		bodyModel[192] = new ModelRendererTurbo(this, 270, 22, textureX, textureY, "cull"); // brace cull
		bodyModel[193] = new ModelRendererTurbo(this, 195, 67, textureX, textureY); // thing (I seriously have no clue wtf this is)
		bodyModel[194] = new ModelRendererTurbo(this, 166, 62, textureX, textureY); // end brace1
		bodyModel[195] = new ModelRendererTurbo(this, 165, 65, textureX, textureY); // end brace 2
		bodyModel[196] = new ModelRendererTurbo(this, 169, 62, textureX, textureY); // end brace3
		bodyModel[197] = new ModelRendererTurbo(this, 171, 66, textureX, textureY); // thing (I seriously have no clue wtf this is)
		bodyModel[198] = new ModelRendererTurbo(this, 157, 5, textureX, textureY); // Box 133
		bodyModel[199] = new ModelRendererTurbo(this, 159, 35, textureX, textureY); // Box 263
		bodyModel[200] = new ModelRendererTurbo(this, 159, 38, textureX, textureY, "cull"); // Box 263 cull
		bodyModel[201] = new ModelRendererTurbo(this, 196, 71, textureX, textureY); // end brace1
		bodyModel[202] = new ModelRendererTurbo(this, 195, 61, textureX, textureY); // end brace 2
		bodyModel[203] = new ModelRendererTurbo(this, 178, 62, textureX, textureY); // end brace1
		bodyModel[204] = new ModelRendererTurbo(this, 177, 65, textureX, textureY); // end brace 2
		bodyModel[205] = new ModelRendererTurbo(this, 175, 71, textureX, textureY); // end brace3
		bodyModel[206] = new ModelRendererTurbo(this, 183, 66, textureX, textureY); // thing (I seriously have no clue wtf this is)
		bodyModel[207] = new ModelRendererTurbo(this, 182, 62, textureX, textureY); // end brace3
		bodyModel[208] = new ModelRendererTurbo(this, 190, 67, textureX, textureY); // thing (I seriously have no clue wtf this is)
		bodyModel[209] = new ModelRendererTurbo(this, 191, 71, textureX, textureY); // end brace1
		bodyModel[210] = new ModelRendererTurbo(this, 190, 61, textureX, textureY); // end brace 2
		bodyModel[211] = new ModelRendererTurbo(this, 182, 71, textureX, textureY); // end brace3
		bodyModel[212] = new ModelRendererTurbo(this, 139, 35, textureX, textureY); // Box 352
		bodyModel[213] = new ModelRendererTurbo(this, 78, 117, textureX, textureY); // Box 0
		bodyModel[214] = new ModelRendererTurbo(this, 67, 113, textureX, textureY); // Box 286
		bodyModel[215] = new ModelRendererTurbo(this, 67, 113, textureX, textureY); // Box 286
		bodyModel[216] = new ModelRendererTurbo(this, 71, 100, textureX, textureY); // Box 118
		bodyModel[217] = new ModelRendererTurbo(this, 66, 100, textureX, textureY); // Box 119
		bodyModel[218] = new ModelRendererTurbo(this, 69, 103, textureX, textureY); // Box 491
		bodyModel[219] = new ModelRendererTurbo(this, 68, 107, textureX, textureY); // Box 93
		bodyModel[220] = new ModelRendererTurbo(this, 69, 103, textureX, textureY); // Box 94
		bodyModel[221] = new ModelRendererTurbo(this, 82, 113, textureX, textureY); // Box 80
		bodyModel[222] = new ModelRendererTurbo(this, 78, 117, textureX, textureY); // Box 80
		bodyModel[223] = new ModelRendererTurbo(this, 94, 110, textureX, textureY); // brake lines
		bodyModel[224] = new ModelRendererTurbo(this, 185, 2, textureX, textureY, "cull"); // rando cull shid (don't color the ends)
		bodyModel[225] = new ModelRendererTurbo(this, 177, 80, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[226] = new ModelRendererTurbo(this, 1, 98, textureX, textureY); // Box 28
		bodyModel[227] = new ModelRendererTurbo(this, 1, 98, textureX, textureY); // Box 28
		bodyModel[228] = new ModelRendererTurbo(this, 65, 99, textureX, textureY); // Box 384
		bodyModel[229] = new ModelRendererTurbo(this, 12, 99, textureX, textureY); // Box 384
		bodyModel[230] = new ModelRendererTurbo(this, 15, 98, textureX, textureY); // Box 0
		bodyModel[231] = new ModelRendererTurbo(this, 15, 98, textureX, textureY); // Box 232
		bodyModel[232] = new ModelRendererTurbo(this, 15, 98, textureX, textureY); // Box 0
		bodyModel[233] = new ModelRendererTurbo(this, 15, 98, textureX, textureY); // Box 232
		bodyModel[234] = new ModelRendererTurbo(this, 250, 68, textureX, textureY); // Box 216
		bodyModel[235] = new ModelRendererTurbo(this, 170, 76, textureX, textureY); // Box 375
		bodyModel[236] = new ModelRendererTurbo(this, 141, 81, textureX, textureY); // Box 0
		bodyModel[237] = new ModelRendererTurbo(this, 141, 72, textureX, textureY); // Box 214
		bodyModel[238] = new ModelRendererTurbo(this, 141, 78, textureX, textureY); // Box 0
		bodyModel[239] = new ModelRendererTurbo(this, 141, 75, textureX, textureY); // Box 214
		bodyModel[240] = new ModelRendererTurbo(this, 230, 45, textureX, textureY, "cull"); // Box 259 cull
		bodyModel[241] = new ModelRendererTurbo(this, 230, 45, textureX, textureY, "cull"); // Box 259 cull
		bodyModel[242] = new ModelRendererTurbo(this, 186, 126, textureX, textureY); // Box 0
		bodyModel[243] = new ModelRendererTurbo(this, 111, 108, textureX, textureY, "cull"); // Box 255 cull
		bodyModel[244] = new ModelRendererTurbo(this, 144, 31, textureX, textureY); // Box 72
		bodyModel[245] = new ModelRendererTurbo(this, 151, 32, textureX, textureY); // Box 87
		bodyModel[246] = new ModelRendererTurbo(this, 184, 76, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[247] = new ModelRendererTurbo(this, 184, 80, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[248] = new ModelRendererTurbo(this, 192, 76, textureX, textureY); // Box 375
		bodyModel[249] = new ModelRendererTurbo(this, 211, 76, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[250] = new ModelRendererTurbo(this, 211, 80, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[251] = new ModelRendererTurbo(this, 204, 76, textureX, textureY); // Box 375
		bodyModel[252] = new ModelRendererTurbo(this, 204, 68, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[253] = new ModelRendererTurbo(this, 204, 72, textureX, textureY, "cull"); // Box 236 cull
		bodyModel[254] = new ModelRendererTurbo(this, 211, 68, textureX, textureY); // Box 375
		bodyModel[255] = new ModelRendererTurbo(this, 26, 26, textureX, textureY); // Box 133
		bodyModel[256] = new ModelRendererTurbo(this, 235, 70, textureX, textureY, "cull"); // Box 268 cull
		bodyModel[257] = new ModelRendererTurbo(this, 235, 67, textureX, textureY, "cull"); // Box 268 cull
		bodyModel[258] = new ModelRendererTurbo(this, 235, 64, textureX, textureY, "cull"); // Box 268 cull
		bodyModel[259] = new ModelRendererTurbo(this, 235, 60, textureX, textureY, "cull"); // Box 268 cull

		bodyModel[0].addBox(0F, 0F, 0F, 3, 2, 3, 0F); // Box 0
		bodyModel[0].setRotationPoint(-40.5F, 3F, -1.5F);

		bodyModel[1].addBox(0F, 0F, 0F, 3, 2, 3, 0F); // Box 0
		bodyModel[1].setRotationPoint(37.5F, 3F, -1.5F);

		bodyModel[2].addBox(0F, 0F, 0F, 59, 17, 1, 0F); // Box 130
		bodyModel[2].setRotationPoint(-29.5F, -14F, 10F);

		bodyModel[3].addShapeBox(0F, 0F, 0F, 4, 2, 4, 0F,0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[3].setRotationPoint(13.5F, 1F, -2F);

		bodyModel[4].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0.285F, -4.23F, -0.35F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0.285F, -4.23F, 0F, 0F, 4F, -0.5F, 0F, 0.05F, 0.3F, 0F, 0.05F, 0F, 0F, 4F, 0F); // Box 256
		bodyModel[4].setRotationPoint(-8.5F, 2F, -9F);

		bodyModel[5].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, -1F, -0.65F, 0F, 1F, 0F, -5F, 0F); // Box 74
		bodyModel[5].setRotationPoint(2.15F, 2F, -10.5F);

		bodyModel[6].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, -1F, -1.85F, 0F, -1F, -1.85F, 0F, 1F, -0.15F, 0F, 1F); // Box 74
		bodyModel[6].setRotationPoint(8.35F, 2F, -10.5F);

		bodyModel[7].addShapeBox(0F, 0F, 0F, 1, 1, 19, 0F,0.35F, -1F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0.35F, -1F, 0F, 0.35F, 1.05F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -0.2F); // Box 0
		bodyModel[7].setRotationPoint(-3.15F, 1F, -9.5F);

		bodyModel[8].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, -1F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 1F); // Box 74
		bodyModel[8].setRotationPoint(17.85F, 2F, -10.5F);

		bodyModel[9].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, 1F, -1.85F, 0F, 1F); // Box 74
		bodyModel[9].setRotationPoint(16.65F, 2F, -10.5F);

		bodyModel[10].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F); // Box 74
		bodyModel[10].setRotationPoint(17.85F, 2F, -3F);

		bodyModel[11].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 1F, -0.65F, 0F, -1F, 0F, -5F, 0F); // Box 179
		bodyModel[11].setRotationPoint(2.15F, 2F, 9.5F);

		bodyModel[12].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 1F, -1.85F, 0F, 1F, -1.85F, 0F, -1F, -0.15F, 0F, -1F); // Box 181
		bodyModel[12].setRotationPoint(8.35F, 2F, 9.5F);

		bodyModel[13].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F); // Box 183
		bodyModel[13].setRotationPoint(2.15F, 2F, 2F);

		bodyModel[14].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 1F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, -1F); // Box 186
		bodyModel[14].setRotationPoint(17.85F, 2F, 9.5F);

		bodyModel[15].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 1F, -0.15F, 0F, 1F, -0.15F, 0F, -1F, -1.85F, 0F, -1F); // Box 188
		bodyModel[15].setRotationPoint(16.65F, 2F, 9.5F);

		bodyModel[16].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F); // Box 190
		bodyModel[16].setRotationPoint(17.85F, 2F, 2F);

		bodyModel[17].addShapeBox(0F, 0F, 0F, 11, 1, 20, 0F,-0.35F, -9.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.35F, -9.15F, 0F, -0.35F, 9.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.35F, 9.15F, 0F); // Box 0
		bodyModel[17].setRotationPoint(24.5F, -8.15F, -10F);

		bodyModel[18].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0.285F, -4.23F, -0.35F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0.285F, -4.23F, 0F, 0F, 4F, -0.5F, 0F, 0.05F, 0.3F, 0F, 0.05F, 0F, 0F, 4F, 0F); // Box 0
		bodyModel[18].setRotationPoint(18.5F, 2F, -9F);

		bodyModel[19].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0.285F, -4.23F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.285F, -4.23F, -0.35F, 0F, 4F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0.3F, 0F, 4F, -0.5F); // Box 256
		bodyModel[19].setRotationPoint(18.5F, 2F, 3F);

		bodyModel[20].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, 0F, 0.5F, 0.285F, -4.23F, -0.35F, 0.285F, -4.23F, 0F, 0F, 0F, 0F, 0F, 0.05F, 0.3F, 0F, 4F, -0.5F, 0F, 4F, 0F, 0F, 0.05F, 0F); // Box 0
		bodyModel[20].setRotationPoint(3.5F, 2F, -9F);

		bodyModel[21].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, 0F, 0F, 0.285F, -4.23F, 0F, 0.285F, -4.23F, -0.35F, 0F, 0F, 0.5F, 0F, 0.05F, 0F, 0F, 4F, 0F, 0F, 4F, -0.5F, 0F, 0.05F, 0.3F); // Box 256
		bodyModel[21].setRotationPoint(3.5F, 2F, 3F);

		bodyModel[22].addShapeBox(0F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F); // Box 74 middle door part
		bodyModel[22].setRotationPoint(10.35F, 2F, -10F);
		bodyModel[22].rotateAngleZ = -0.35342917F;

		bodyModel[23].addShapeBox(-1F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F); // Box 74 rear door part
		bodyModel[23].setRotationPoint(16.65F, 2F, -10F);
		bodyModel[23].rotateAngleZ = 0.35342917F;

		bodyModel[24].addShapeBox(0F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F); // Box 74 middle door part
		bodyModel[24].setRotationPoint(10.35F, 2F, 2F);
		bodyModel[24].rotateAngleZ = -0.35342917F;

		bodyModel[25].addShapeBox(-1F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F); // Box 74 rear door part
		bodyModel[25].setRotationPoint(16.65F, 2F, 2F);
		bodyModel[25].rotateAngleZ = 0.35342917F;

		bodyModel[26].addShapeBox(0.25F, 3F, 0F, 1, 1, 20, 0F,-0.75F, -0.325F, 0F, 0F, -0.325F, 0F, 0F, -0.325F, 0F, -0.75F, -0.325F, 0F, -0.75F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, -0.75F, 0.325F, 0F); // Box 74 middle door part
		bodyModel[26].setRotationPoint(10.35F, 2F, -10F);
		bodyModel[26].rotateAngleZ = -0.35342917F;

		bodyModel[27].addShapeBox(0F, 0F, 0F, 73, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 0
		bodyModel[27].setRotationPoint(-36.5F, -14F, 11F);

		bodyModel[28].addShapeBox(0F, 0F, 0F, 73, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 300
		bodyModel[28].setRotationPoint(-36.5F, -14F, -12F);

		bodyModel[29].addShapeBox(0F, 0F, 0F, 1, 1, 24, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 0
		bodyModel[29].setRotationPoint(-37.5F, -14F, -12F);

		bodyModel[30].addShapeBox(0F, 0F, 0F, 1, 1, 24, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F, 0F, -0.75F, 0F); // Box 0
		bodyModel[30].setRotationPoint(36.5F, -14F, -12F);

		bodyModel[31].addShapeBox(0F, 0F, 0F, 1, 6, 20, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[31].setRotationPoint(-36.5F, -14F, -10F);

		bodyModel[32].addShapeBox(0F, 0F, 0F, 1, 6, 20, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[32].setRotationPoint(35.5F, -14F, -10F);

		bodyModel[33].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F); // Box 74
		bodyModel[33].setRotationPoint(8.35F, 2F, -3F);

		bodyModel[34].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F); // Box 74
		bodyModel[34].setRotationPoint(16.65F, 2F, -3F);

		bodyModel[35].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F); // Box 185
		bodyModel[35].setRotationPoint(8.35F, 2F, 2F);

		bodyModel[36].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F); // Box 191
		bodyModel[36].setRotationPoint(16.65F, 2F, 2F);

		bodyModel[37].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F); // Box 74
		bodyModel[37].setRotationPoint(2.15F, 2F, -3F);

		bodyModel[38].addShapeBox(-2F, 3F, 0F, 1, 1, 20, 0F,-0.75F, -0.325F, 0F, 0F, -0.325F, 0F, 0F, -0.325F, 0F, -0.75F, -0.325F, 0F, -0.75F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, -0.75F, 0.325F, 0F); // Box 74  rear door part
		bodyModel[38].setRotationPoint(16.65F, 2F, -10F);
		bodyModel[38].rotateAngleZ = 0.35342917F;

		bodyModel[39].addShapeBox(-4F, 0F, 0F, 4, 1, 21, 0F,-0.85F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.85F, -2F, 0F, -1.8F, 1.35F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.8F, 1.35F, 0F); // Box 0
		bodyModel[39].setRotationPoint(13.5F, 0F, -10.5F);

		bodyModel[40].addShapeBox(0F, 0F, 0F, 4, 1, 21, 0F,0F, 0F, 0F, -0.85F, -2F, 0F, -0.85F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.8F, 1.35F, 0F, -1.8F, 1.35F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[40].setRotationPoint(13.5F, 0F, -10.5F);

		bodyModel[41].addShapeBox(0F, 0F, 0F, 1, 1, 20, 0F,0F, 0F, 0.07F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.07F, 0F, -0.15F, 0.07F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -0.15F, 0.07F); // Box 0
		bodyModel[41].setRotationPoint(35.5F, -8F, -10F);

		bodyModel[42].addShapeBox(0F, 0F, 0F, 1, 1, 20, 0F,0F, 0F, 0F, 0F, 0F, 0.07F, 0F, 0F, 0.07F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -0.15F, 0.07F, 0F, -0.15F, 0.07F, 0F, -1F, 0F); // Box 0
		bodyModel[42].setRotationPoint(-36.5F, -8F, -10F);

		bodyModel[43].addBox(0F, 0F, 0F, 7, 6, 1, 0F); // Box 130
		bodyModel[43].setRotationPoint(-36.5F, -14F, -11F);

		bodyModel[44].addShapeBox(0F, 0F, 0F, 7, 6, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F); // Box 130
		bodyModel[44].setRotationPoint(-36.5F, -8F, -11F);

		bodyModel[45].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 1F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, -1F); // Box 74
		bodyModel[45].setRotationPoint(-9.15F, 2F, 9.5F);

		bodyModel[46].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 1F, -0.15F, 0F, 1F, -0.15F, 0F, -1F, -1.85F, 0F, -1F); // Box 74
		bodyModel[46].setRotationPoint(-10.35F, 2F, 9.5F);

		bodyModel[47].addShapeBox(0F, 0F, 0F, 1, 1, 19, 0F,0F, -1F, 0F, 0.35F, -1F, 0F, 0.35F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -0.2F, 0.35F, 1.05F, -0.2F, 0F, 0F, 0F); // Box 0
		bodyModel[47].setRotationPoint(2.15F, 1F, -9.5F);

		bodyModel[48].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 1F, -0.65F, 0F, -1F, 0F, -5F, 0F); // Box 74
		bodyModel[48].setRotationPoint(-24.85F, 2F, 9.5F);

		bodyModel[49].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 1F, -1.85F, 0F, 1F, -1.85F, 0F, -1F, -0.15F, 0F, -1F); // Box 74
		bodyModel[49].setRotationPoint(-18.65F, 2F, 9.5F);

		bodyModel[50].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F); // Box 74
		bodyModel[50].setRotationPoint(-24.85F, 2F, 2F);

		bodyModel[51].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, -1F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 1F); // Box 179
		bodyModel[51].setRotationPoint(-9.15F, 2F, -10.5F);

		bodyModel[52].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, -1F, -0.15F, 0F, -1F, -0.15F, 0F, 1F, -1.85F, 0F, 1F); // Box 181
		bodyModel[52].setRotationPoint(-10.35F, 2F, -10.5F);

		bodyModel[53].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F); // Box 183
		bodyModel[53].setRotationPoint(-9.15F, 2F, -3F);

		bodyModel[54].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, -1F, -0.65F, 0F, 1F, 0F, -5F, 0F); // Box 186
		bodyModel[54].setRotationPoint(-24.85F, 2F, -10.5F);

		bodyModel[55].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, -1F, -1.85F, 0F, -1F, -1.85F, 0F, 1F, -0.15F, 0F, 1F); // Box 188
		bodyModel[55].setRotationPoint(-18.65F, 2F, -10.5F);

		bodyModel[56].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, 0F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F); // Box 190
		bodyModel[56].setRotationPoint(-24.85F, 2F, -3F);

		bodyModel[57].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, 0F, 0F, 0.285F, -4.23F, 0F, 0.285F, -4.23F, -0.35F, 0F, 0F, 0.5F, 0F, 0.05F, 0F, 0F, 4F, 0F, 0F, 4F, -0.5F, 0F, 0.05F, 0.3F); // Box 0
		bodyModel[57].setRotationPoint(-23.5F, 2F, 3F);

		bodyModel[58].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0F, 0F, 0.5F, 0.285F, -4.23F, -0.35F, 0.285F, -4.23F, 0F, 0F, 0F, 0F, 0F, 0.05F, 0.3F, 0F, 4F, -0.5F, 0F, 4F, 0F, 0F, 0.05F, 0F); // Box 256
		bodyModel[58].setRotationPoint(-23.5F, 2F, -9F);

		bodyModel[59].addShapeBox(0F, 0F, 0F, 5, 1, 6, 0F,0.285F, -4.23F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0.285F, -4.23F, -0.35F, 0F, 4F, 0F, 0F, 0.05F, 0F, 0F, 0.05F, 0.3F, 0F, 4F, -0.5F); // Box 0
		bodyModel[59].setRotationPoint(-8.5F, 2F, 3F);

		bodyModel[60].addShapeBox(0F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F); // Box 74 middle door part
		bodyModel[60].setRotationPoint(-16.65F, 2F, -10F);
		bodyModel[60].rotateAngleZ = -0.35342917F;

		bodyModel[61].addShapeBox(-1F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F); // Box 74 rear door part
		bodyModel[61].setRotationPoint(-10.35F, 2F, -10F);
		bodyModel[61].rotateAngleZ = 0.35342917F;

		bodyModel[62].addShapeBox(0F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F); // Box 74 middle door part
		bodyModel[62].setRotationPoint(-16.65F, 2F, 2F);
		bodyModel[62].rotateAngleZ = -0.35342917F;

		bodyModel[63].addShapeBox(-1F, 0F, 0F, 1, 5, 8, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, -0.5F, 0F, 0.325F, -0.5F); // Box 74 rear door part
		bodyModel[63].setRotationPoint(-10.35F, 2F, 2F);
		bodyModel[63].rotateAngleZ = 0.35342917F;

		bodyModel[64].addShapeBox(0.25F, 3F, 0F, 1, 1, 20, 0F,-0.75F, -0.325F, 0F, 0F, -0.325F, 0F, 0F, -0.325F, 0F, -0.75F, -0.325F, 0F, -0.75F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, -0.75F, 0.325F, 0F); // Box 74 middle door part
		bodyModel[64].setRotationPoint(-16.65F, 2F, -10F);
		bodyModel[64].rotateAngleZ = -0.35342917F;

		bodyModel[65].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F); // Box 74
		bodyModel[65].setRotationPoint(-18.65F, 2F, -3F);

		bodyModel[66].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F); // Box 74
		bodyModel[66].setRotationPoint(-10.35F, 2F, -3F);

		bodyModel[67].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,-0.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F); // Box 185
		bodyModel[67].setRotationPoint(-18.65F, 2F, 2F);

		bodyModel[68].addShapeBox(0F, 0F, 0F, 2, 5, 1, 0F,0F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, 0F, 0F, 0F, -1.85F, 0F, 0F, -0.15F, 0F, 0F, -0.15F, 0F, 0F, -1.85F, 0F, 0F); // Box 191
		bodyModel[68].setRotationPoint(-10.35F, 2F, 2F);

		bodyModel[69].addShapeBox(0F, 0F, 0F, 7, 5, 1, 0F,-0.65F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.65F, 0F, 0F, -0.65F, 0F, 0F, 0F, -5F, 0F, 0F, -5F, 0F, -0.65F, 0F, 0F); // Box 190
		bodyModel[69].setRotationPoint(-9.15F, 2F, 2F);

		bodyModel[70].addShapeBox(-2F, 3F, 0F, 1, 1, 20, 0F,-0.75F, -0.325F, 0F, 0F, -0.325F, 0F, 0F, -0.325F, 0F, -0.75F, -0.325F, 0F, -0.75F, 0.325F, 0F, 0F, 0.325F, 0F, 0F, 0.325F, 0F, -0.75F, 0.325F, 0F); // Box 74  rear door part
		bodyModel[70].setRotationPoint(-10.35F, 2F, -10F);
		bodyModel[70].rotateAngleZ = 0.35342917F;

		bodyModel[71].addShapeBox(0F, 0F, 0F, 4, 1, 21, 0F,0F, 0F, 0F, -0.5F, -2F, 0F, -0.5F, -2F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F, -1.85F, 1F, 0F, -1.85F, 1F, 0F, 0F, -0.4F, 0F); // Box 0
		bodyModel[71].setRotationPoint(0F, 0F, -10.5F);

		bodyModel[72].addShapeBox(0F, 0F, 0F, 1, 1, 19, 0F,0F, 0F, 0F, 0.35F, -1F, 0F, 0.35F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -0.2F, 0.35F, 1.05F, -0.2F, 0F, 0F, 0F); // Box 0
		bodyModel[72].setRotationPoint(-24.85F, 1F, -9.5F);

		bodyModel[73].addShapeBox(0F, 0F, 0F, 1, 1, 19, 0F,0.35F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, -1F, 0F, 0.35F, 1.05F, -0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -0.2F); // Box 0
		bodyModel[73].setRotationPoint(23.85F, 1F, -9.5F);

		bodyModel[74].addBox(0F, 0F, 0F, 7, 6, 1, 0F); // Box 130
		bodyModel[74].setRotationPoint(-36.5F, -14F, 10F);

		bodyModel[75].addShapeBox(0F, 0F, 0F, 7, 6, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F); // Box 130
		bodyModel[75].setRotationPoint(-36.5F, -8F, 10F);

		bodyModel[76].addBox(0F, 0F, 0F, 7, 6, 1, 0F); // Box 130
		bodyModel[76].setRotationPoint(29.5F, -14F, -11F);

		bodyModel[77].addShapeBox(0F, 0F, 0F, 7, 6, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, 0F, 0F); // Box 130
		bodyModel[77].setRotationPoint(29.5F, -8F, -11F);

		bodyModel[78].addBox(0F, 0F, 0F, 7, 6, 1, 0F); // Box 130
		bodyModel[78].setRotationPoint(29.5F, -14F, 10F);

		bodyModel[79].addShapeBox(0F, 0F, 0F, 7, 6, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, 0F, 0F); // Box 130
		bodyModel[79].setRotationPoint(29.5F, -8F, 10F);

		bodyModel[80].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, -1.4F, -2F, 0F, -1.85F, -2F, 0F, -1.4F, -2F, 0F, -1.4F, -2F, 0F); // Box 0
		bodyModel[80].setRotationPoint(-24.85F, 1F, -3F);

		bodyModel[81].addShapeBox(0F, 0F, 0F, 4, 1, 21, 0F,-0.85F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.85F, -2F, 0F, -1.8F, 1.35F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.8F, 1.35F, 0F); // Box 0
		bodyModel[81].setRotationPoint(-17.5F, 0F, -10.5F);

		bodyModel[82].addShapeBox(0F, 0F, 0F, 4, 1, 21, 0F,0F, 0F, 0F, -0.85F, -2F, 0F, -0.85F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.8F, 1.35F, 0F, -1.8F, 1.35F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[82].setRotationPoint(-13.5F, 0F, -10.5F);

		bodyModel[83].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, -1.4F, 0F, 0F, -1.9F, 0F, 0F, -1.9F, -2F, 0F, -1.4F, -2F, 0F); // Box 0
		bodyModel[83].setRotationPoint(-24.85F, 1F, 0F);

		bodyModel[84].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -3F, 0F, 0F, -3F, -1.85F, 0F, 0F, -1.4F, 0F, 0F, -1.4F, -2F, 0F, -1.85F, -2F, 0F); // Box 0
		bodyModel[84].setRotationPoint(14.85F, 1F, 0F);

		bodyModel[85].addShapeBox(0F, 0F, 0F, 10, 3, 3, 0F,0F, 0F, -3F, 0F, 0F, -3F, 0F, 0F, 0F, 0F, 0F, 0F, -1.85F, -2F, 0F, -1.4F, -2F, 0F, -1.4F, 0F, 0F, -1.85F, 0F, 0F); // Box 0
		bodyModel[85].setRotationPoint(14.85F, 1F, -3F);

		bodyModel[86].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[86].setRotationPoint(-29F, -13.75F, -12F);

		bodyModel[87].addBox(0F, 0F, 0F, 75, 2, 4, 0F); // Box 0
		bodyModel[87].setRotationPoint(-37.5F, 3F, -2F);

		bodyModel[88].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 0
		bodyModel[88].setRotationPoint(-24.95F, 1F, -10.5F);

		bodyModel[89].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 214
		bodyModel[89].setRotationPoint(-24.95F, 1F, 9.5F);

		bodyModel[90].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0.35F, -1F, -1F, 0.35F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -1.2F, 0.35F, 1.05F, 0.2F, 0F, 0F, 0F); // Box 0
		bodyModel[90].setRotationPoint(-24.85F, 1F, -10.5F);

		bodyModel[91].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0F, 0F, 0F, 0.35F, -1F, 0F, 0.35F, -1F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, 0.2F, 0.35F, 1.05F, -1.2F, 0F, 0F, 0F); // Box 158
		bodyModel[91].setRotationPoint(-24.85F, 1F, 9.5F);

		bodyModel[92].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.35F, -1F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, -1F, 0F, 0.35F, 1.05F, -1.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, 0.2F); // Box 0
		bodyModel[92].setRotationPoint(23.85F, 1F, -10.5F);

		bodyModel[93].addShapeBox(0F, 0F, 0F, 1, 1, 1, 0F,0.35F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, -1F, -1F, 0.35F, 1.05F, 0.2F, 0F, 0F, 0F, 0F, 0F, 0F, 0.35F, 1.05F, -1.2F); // Box 158
		bodyModel[93].setRotationPoint(23.85F, 1F, 9.5F);

		bodyModel[94].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, -0.4F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, 0F); // Box 0
		bodyModel[94].setRotationPoint(15.05F, 1F, -10.5F);

		bodyModel[95].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, 0F); // Box 214
		bodyModel[95].setRotationPoint(15.05F, 1F, 9.5F);

		bodyModel[96].addShapeBox(-4F, 0F, 0F, 4, 1, 21, 0F,-0.5F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, -2F, 0F, -1.85F, 1F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, -1.85F, 1F, 0F); // Box 0
		bodyModel[96].setRotationPoint(0F, 0F, -10.5F);

		bodyModel[97].addShapeBox(0F, 0F, -3F, 10, 1, 3, 0F,-1.7F, -1F, 0F, -1.45F, -1F, 0F, 0.35F, 0F, 0F, -0.1F, 0F, 0F, -1.7F, 0F, 0F, -1.45F, 0F, 0F, -1.45F, 0F, 0F, -1.6F, 0F, 0F); // Box 0
		bodyModel[97].setRotationPoint(-12.05F, 1F, 0F);

		bodyModel[98].addShapeBox(0F, 0F, 0F, 10, 1, 3, 0F,-0.1F, 0F, 0F, 0.35F, 0F, 0F, -1.45F, -1F, 0F, -1.7F, -1F, 0F, -1.6F, 0F, 0F, -1.45F, 0F, 0F, -1.45F, 0F, 0F, -1.7F, 0F, 0F); // Box 0
		bodyModel[98].setRotationPoint(-12.05F, 1F, 0F);

		bodyModel[99].addShapeBox(0F, 0F, -3F, 10, 1, 3, 0F,-1.45F, -1F, 0F, -1.7F, -1F, 0F, -0.1F, 0F, 0F, 0.35F, 0F, 0F, -1.45F, 0F, 0F, -1.7F, 0F, 0F, -1.6F, 0F, 0F, -1.45F, 0F, 0F); // Box 0
		bodyModel[99].setRotationPoint(2.05F, 1F, 0F);

		bodyModel[100].addShapeBox(0F, 0F, 0F, 10, 1, 3, 0F,0.35F, 0F, 0F, -0.1F, 0F, 0F, -1.7F, -1F, 0F, -1.45F, -1F, 0F, -1.45F, 0F, 0F, -1.6F, 0F, 0F, -1.7F, 0F, 0F, -1.45F, 0F, 0F); // Box 0
		bodyModel[100].setRotationPoint(2.05F, 1F, 0F);

		bodyModel[101].addShapeBox(0F, 0F, 0F, 1, 5, 21, 0F,0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[101].setRotationPoint(-29.5F, -2F, -11F);

		bodyModel[102].addShapeBox(0F, 0F, 0F, 1, 5, 21, 0F,0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[102].setRotationPoint(28.5F, -2F, -10F);

		bodyModel[103].addShapeBox(-4F, 0F, 0F, 4, 2, 4, 0F,-0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F); // Box 0
		bodyModel[103].setRotationPoint(13.5F, 1F, -2F);

		bodyModel[104].addShapeBox(0F, 0F, 0F, 4, 2, 4, 0F,0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[104].setRotationPoint(0F, 1F, -2F);

		bodyModel[105].addShapeBox(-4F, 0F, 0F, 4, 2, 4, 0F,-0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F); // Box 0
		bodyModel[105].setRotationPoint(0F, 1F, -2F);

		bodyModel[106].addShapeBox(0F, 0F, 0F, 4, 2, 4, 0F,0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[106].setRotationPoint(-13.5F, 1F, -2F);

		bodyModel[107].addShapeBox(-4F, 0F, 0F, 4, 2, 4, 0F,-0.1F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, -2F, 0F, -0.1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.1F, 0F, 0F); // Box 0
		bodyModel[107].setRotationPoint(-13.5F, 1F, -2F);

		bodyModel[108].addBox(0F, 0F, 0F, 1, 1, 20, 0F); // Box 172
		bodyModel[108].setRotationPoint(13F, 2F, -10F);

		bodyModel[109].addBox(0F, 0F, 0F, 1, 1, 20, 0F); // Box 172
		bodyModel[109].setRotationPoint(-0.5F, 2F, -10F);

		bodyModel[110].addBox(0F, 0F, 0F, 1, 1, 20, 0F); // Box 172
		bodyModel[110].setRotationPoint(-14F, 2F, -10F);

		bodyModel[111].addBox(0F, 0F, 0F, 59, 0, 1, 0F); // Box 0 cull
		bodyModel[111].setRotationPoint(-29.5F, 3F, -12F);

		bodyModel[112].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F,-2F, -1F, -1.5F, 0F, -1F, -1.5F, 0F, 0F, -0.5F, -2F, 0F, -0.5F, -2F, 0.05F, -1.5F, 0F, 0.05F, -1.5F, 0F, 0.05F, -0.5F, -2F, 0.05F, -0.5F); // Box 144 shaker bracket cull
		bodyModel[112].setRotationPoint(-31.5F, 1.98F, -13.52F);

		bodyModel[113].addShapeBox(0F, 0F, 0F, 0, 10, 7, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -10F, 0F, 0F, -10F, 0F, 0F, -9F, 0F, 0F, -9F, 0F, 0F, 0F, -1F, 0F, 0F, -1F); // Interior Brackets cull
		bodyModel[113].setRotationPoint(13.5F, -10F, -10F);

		bodyModel[114].addShapeBox(0F, 0F, 0F, 0, 10, 7, 0F,0F, -10F, 0F, 0F, -10F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, -9F, 0F, 0F, -9F, 0F); // Interior Brackets cull
		bodyModel[114].setRotationPoint(13.5F, -10F, 3F);

		bodyModel[115].addBox(0F, 0F, 0F, 1, 17, 1, 0F); // Box 133
		bodyModel[115].setRotationPoint(-36.51F, -13.99F, 10.1F);

		bodyModel[116].addBox(0F, 0F, 0F, 1, 17, 1, 0F); // Box 133
		bodyModel[116].setRotationPoint(-36.51F, -13.99F, -11.01F);

		bodyModel[117].addBox(0F, 0F, 0F, 1, 17, 1, 0F); // Box 133
		bodyModel[117].setRotationPoint(35.51F, -13.99F, -11.01F);

		bodyModel[118].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F); // Box 236 cull
		bodyModel[118].setRotationPoint(-36.5F, 3F, 10F);

		bodyModel[119].addBox(0F, 0F, 0F, 7, 1, 1, 0F); // grab iron cull
		bodyModel[119].setRotationPoint(-36F, -1F, -12.01F);

		bodyModel[120].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 253
		bodyModel[120].setRotationPoint(36.5F, -13.75F, -3F);

		bodyModel[121].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // grab iron cull
		bodyModel[121].setRotationPoint(-37.52F, -1F, -10.99F);

		bodyModel[122].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,-0.25F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, -0.25F, -0.25F, 0F); // Box 253
		bodyModel[122].setRotationPoint(-37.5F, -13.75F, -3F);

		bodyModel[123].addBox(0F, 0F, 0F, 4, 1, 1, 0F); // Box 101 cull
		bodyModel[123].setRotationPoint(-36F, -5.5F, -12.01F);

		bodyModel[124].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[124].setRotationPoint(-37.5F, 3F, -9F);

		bodyModel[125].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 242
		bodyModel[125].setRotationPoint(-37.5F, 3F, 4F);

		bodyModel[126].addBox(0F, 0F, 0F, 1, 1, 21, 0F); // Box 216
		bodyModel[126].setRotationPoint(-36.5F, 2F, -10.5F);

		bodyModel[127].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[127].setRotationPoint(-24.5F, -13.75F, -12F);

		bodyModel[128].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[128].setRotationPoint(-9.5F, -13.75F, -12F);

		bodyModel[129].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[129].setRotationPoint(-0.5F, -13.75F, -12F);

		bodyModel[130].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[130].setRotationPoint(-5F, -13.75F, -12F);

		bodyModel[131].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[131].setRotationPoint(-14F, -13.75F, -12F);

		bodyModel[132].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[132].setRotationPoint(28F, -13.75F, -12F);

		bodyModel[133].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[133].setRotationPoint(23.5F, -13.75F, -12F);

		bodyModel[134].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[134].setRotationPoint(8.5F, -13.75F, -12F);

		bodyModel[135].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[135].setRotationPoint(4F, -13.75F, -12F);

		bodyModel[136].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[136].setRotationPoint(18.5F, -13.75F, -12F);

		bodyModel[137].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[137].setRotationPoint(13F, -13.75F, -12F);

		bodyModel[138].addShapeBox(0F, 0F, 0F, 1, 11, 0, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[138].setRotationPoint(-37.75F, -8F, 3.5F);

		bodyModel[139].addShapeBox(0F, 0F, 0F, 0, 1, 3, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 263
		bodyModel[139].setRotationPoint(-38.5F, 5F, -1F);

		bodyModel[140].addShapeBox(0F, -1F, 0F, 0, 3, 4, 0F,0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 264
		bodyModel[140].setRotationPoint(-36.53F, 5F, 6.5F);

		bodyModel[141].addShapeBox(0F, -1F, 0F, 0, 1, 5, 0F,0F, -1F, 0.38F, 0F, -1F, 0.38F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0.38F, 0F, 1F, 0.38F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 265
		bodyModel[141].setRotationPoint(-38.36F, 5F, 2.35F);
		bodyModel[141].rotateAngleY = -0.37873645F;

		bodyModel[142].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 253
		bodyModel[142].setRotationPoint(-37.25F, -13.75F, 2F);

		bodyModel[143].addShapeBox(0F, 0F, 0F, 1, 14, 1, 0F,0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 253
		bodyModel[143].setRotationPoint(-37.25F, -11F, 4F);

		bodyModel[144].addShapeBox(0F, 0F, 0F, 1, 3, 2, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F); // Box 43
		bodyModel[144].setRotationPoint(-37.75F, -11F, 2.5F);

		bodyModel[145].addShapeBox(0F, 0F, 0F, 2, 1, 3, 0F,0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -1F, 0F); // Box 0 cull
		bodyModel[145].setRotationPoint(-39F, -4.5F, 2F);

		bodyModel[146].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[146].setRotationPoint(-29F, -13.75F, 11F);

		bodyModel[147].addBox(0F, 0F, 0F, 59, 0, 1, 0F); // sill flange cull
		bodyModel[147].setRotationPoint(-29.5F, 3F, 11F);

		bodyModel[148].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[148].setRotationPoint(-24.5F, -13.75F, 11F);

		bodyModel[149].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[149].setRotationPoint(-9.5F, -13.75F, 11F);

		bodyModel[150].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[150].setRotationPoint(-0.5F, -13.75F, 11F);

		bodyModel[151].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[151].setRotationPoint(-5F, -13.75F, 11F);

		bodyModel[152].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[152].setRotationPoint(-19.5F, -13.75F, 11F);

		bodyModel[153].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[153].setRotationPoint(-14F, -13.75F, 11F);

		bodyModel[154].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[154].setRotationPoint(28F, -13.75F, 11F);

		bodyModel[155].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F, 0F, -0.24F, 0F); // Box 133
		bodyModel[155].setRotationPoint(23.5F, -13.75F, 11F);

		bodyModel[156].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[156].setRotationPoint(8.5F, -13.75F, 11F);

		bodyModel[157].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[157].setRotationPoint(4F, -13.75F, 11F);

		bodyModel[158].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[158].setRotationPoint(18.5F, -13.75F, 11F);

		bodyModel[159].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[159].setRotationPoint(13F, -13.75F, 11F);

		bodyModel[160].addShapeBox(0F, 0F, 0F, 6, 0, 1, 0F,0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F); // Box 263 cull
		bodyModel[160].setRotationPoint(-35.51F, 3F, -12F);

		bodyModel[161].addBox(0F, 0F, 0F, 59, 17, 1, 0F); // Box 130
		bodyModel[161].setRotationPoint(-29.5F, -14F, -11F);

		bodyModel[162].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F,-2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -1F, -1.5F, -2F, -1F, -1.5F, -2F, 0.05F, -0.5F, 0F, 0.05F, -0.5F, 0F, 0.05F, -1.5F, -2F, 0.05F, -1.5F); // Box 144 shaker bracket cull
		bodyModel[162].setRotationPoint(-31.5F, 1.98F, 10.48F);

		bodyModel[163].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, -0.75F, 0F, 0F, -1.75F, 0F, -0.25F, 1F, 0F, -0.25F, 0F, 0F, -0.25F, -0.75F, 0F, -0.25F, -1.75F); // Box 263
		bodyModel[163].setRotationPoint(-35.51F, 2.24F, 10.75F);

		bodyModel[164].addShapeBox(0F, 0F, 0F, 6, 0, 1, 0F,0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F); // Box 263 cull
		bodyModel[164].setRotationPoint(-35.51F, 3F, 11F);

		bodyModel[165].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F,0F, 0F, -0.5F, -2F, 0F, -0.5F, -2F, -1F, -1.5F, 0F, -1F, -1.5F, 0F, 0.05F, -0.5F, -2F, 0.05F, -0.5F, -2F, 0.05F, -1.5F, 0F, 0.05F, -1.5F); // Box 144 shaker bracket cull
		bodyModel[165].setRotationPoint(27.5F, 1.98F, 10.52F);

		bodyModel[166].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, -1.75F, 0F, 0F, -0.75F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, -0.25F, -1.75F, 0F, -0.25F, -0.75F, 0F, -0.25F, 0F, 0F, -0.25F, 1F); // Box 263
		bodyModel[166].setRotationPoint(-35.51F, 2.25F, -11.75F);

		bodyModel[167].addShapeBox(0F, 0F, 0F, 4, 1, 3, 0F,0F, -1F, -1.5F, -2F, -1F, -1.5F, -2F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0.05F, -1.5F, -2F, 0.05F, -1.5F, -2F, 0.05F, -0.5F, 0F, 0.05F, -0.5F); // Box 144 shaker bracket cull
		bodyModel[167].setRotationPoint(27.5F, 1.98F, -13.48F);

		bodyModel[168].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, -0.75F, 0F, 0F, -1.75F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, -0.25F, -0.75F, 0F, -0.25F, -1.75F, 0F, -0.25F, 1F, 0F, -0.25F, 0F); // Box 263
		bodyModel[168].setRotationPoint(29.51F, 2.25F, -12F);

		bodyModel[169].addShapeBox(0F, 0F, 0F, 6, 0, 1, 0F,0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, 1F, 0F, 0F, 0F); // Box 263 cull
		bodyModel[169].setRotationPoint(29.51F, 3F, -12F);

		bodyModel[170].addBox(0F, 0F, 0F, 7, 1, 1, 0F); // grab iron cull
		bodyModel[170].setRotationPoint(29F, -1F, 11.01F);

		bodyModel[171].addBox(0F, 0F, 0F, 4, 1, 1, 0F); // Box 101 cull
		bodyModel[171].setRotationPoint(32F, -5.5F, 11.01F);

		bodyModel[172].addShapeBox(0F, 0F, 0F, 1, 1, 8, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // grab iron cull
		bodyModel[172].setRotationPoint(36.52F, -1F, 2.99F);

		bodyModel[173].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, -0.25F, -0.25F, 0F, -0.25F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 253
		bodyModel[173].setRotationPoint(36.5F, -13.75F, 2F);

		bodyModel[174].addShapeBox(0F, 0F, 0F, 1, 16, 3, 0F,-0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, -0.5F, -0.25F, 0F); // Box 0 cull
		bodyModel[174].setRotationPoint(36.01F, -13.75F, -10.5F);

		bodyModel[175].addShapeBox(0F, 0F, 0F, 3, 17, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.7175F, 0F, 0F, 0.2175F, 0F, 0F, 0F, 0F, -0.25F, -0.5F, 0F, -0.25F, -0.7175F, 0F, -0.25F, 0.2175F, 0F, -0.25F, 0F); // Box 252 cull
		bodyModel[175].setRotationPoint(33F, -13.75F, -12.26F);

		bodyModel[176].addShapeBox(0F, 0F, 0F, 1, 9, 0, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 251
		bodyModel[176].setRotationPoint(32.5F, -6F, -11.02F);

		bodyModel[177].addShapeBox(0F, 0F, 0F, 1, 16, 3, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, -0.5F, -0.25F, 0F, -0.5F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 0 cull
		bodyModel[177].setRotationPoint(-37.01F, -13.75F, 7.5F);

		bodyModel[178].addShapeBox(0F, 0F, 0F, 3, 17, 1, 0F,0F, 0F, 0.2175F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.7175F, 0F, -0.25F, 0.2175F, 0F, -0.25F, 0F, 0F, -0.25F, -0.5F, 0F, -0.25F, -0.7175F); // Box 252 cull
		bodyModel[178].setRotationPoint(-36F, -13.75F, 11.26F);

		bodyModel[179].addShapeBox(0F, 0F, 0F, 1, 9, 0, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, -0.25F, 0F); // Box 251
		bodyModel[179].setRotationPoint(-33.5F, -6F, 11.02F);

		bodyModel[180].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[180].setRotationPoint(36.5F, 3F, 4F);

		bodyModel[181].addShapeBox(0F, 0F, 0F, 1, 0, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 242
		bodyModel[181].setRotationPoint(36.5F, 3F, -9F);

		bodyModel[182].addShapeBox(0F, 0F, 0F, 0, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 96
		bodyModel[182].setRotationPoint(-36.51F, -9F, 7.02F);

		bodyModel[183].addShapeBox(0F, 0F, 0F, 0, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 215
		bodyModel[183].setRotationPoint(38.5F, 5F, -2F);

		bodyModel[184].addShapeBox(0F, -1F, 0F, 0, 3, 4, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F); // Box 216
		bodyModel[184].setRotationPoint(36.53F, 5F, -10.5F);

		bodyModel[185].addShapeBox(0F, -1F, 0F, 0, 1, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0.38F, 0F, -1F, 0.38F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0.38F, 0F, 1F, 0.38F); // Box 216
		bodyModel[185].setRotationPoint(36.51F, 5F, -7F);
		bodyModel[185].rotateAngleY = -0.37873645F;

		bodyModel[186].addShapeBox(0F, 0F, 0F, 0, 11, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 96
		bodyModel[186].setRotationPoint(36.51F, -9F, -8.02F);

		bodyModel[187].addBox(0F, 0F, 0F, 1, 1, 20, 0F); // Brace Cull
		bodyModel[187].setRotationPoint(-32.95F, -3.6F, -10F);
		bodyModel[187].rotateAngleZ = 0.8621752F;

		bodyModel[188].addBox(0F, 0F, 0F, 1, 1, 20, 0F); // Brace Cull
		bodyModel[188].setRotationPoint(31.54F, -3.69F, -10F);
		bodyModel[188].rotateAngleZ = 0.70862113F;

		bodyModel[189].addShapeBox(0F, 0F, 0F, 1, 8, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F, 0F, -1.54F, 0F, 0F, -1.54F, 0F, 0F, -0.4F, 0F); // brace cull
		bodyModel[189].setRotationPoint(31.54F, -3.74F, -3F);
		bodyModel[189].rotateAngleZ = 0.70862113F;

		bodyModel[190].addShapeBox(0F, 0F, 0F, 1, 8, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.4F, 0F, 0F, -1.54F, 0F, 0F, -1.54F, 0F, 0F, -0.4F, 0F); // brace cull
		bodyModel[190].setRotationPoint(31.54F, -3.74F, 2F);
		bodyModel[190].rotateAngleZ = 0.70862113F;

		bodyModel[191].addShapeBox(0F, 0F, 0F, 1, 8, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.54F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1.54F, 0F); // brace cull
		bodyModel[191].setRotationPoint(-32.27F, -4.43F, -3F);
		bodyModel[191].rotateAngleZ = -0.70862113F;

		bodyModel[192].addShapeBox(0F, 0F, 0F, 1, 8, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1.54F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -1.54F, 0F); // brace cull
		bodyModel[192].setRotationPoint(-32.27F, -4.43F, 2F);
		bodyModel[192].rotateAngleZ = -0.70862113F;

		bodyModel[193].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.75F, -0.25F, 0F, -0.75F, -0.25F, 0F, -0.75F, 0F, -0.5F, -0.75F, 0F); // thing (I seriously have no clue wtf this is)
		bodyModel[193].setRotationPoint(-37.52F, 1.75F, 10F);

		bodyModel[194].addShapeBox(0F, 0F, 0F, 0, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace1
		bodyModel[194].setRotationPoint(-36.52F, 1F, -10.01F);

		bodyModel[195].addShapeBox(0F, 0F, 0F, 0, 3, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F); // end brace 2
		bodyModel[195].setRotationPoint(-36.52F, 2F, -11.01F);

		bodyModel[196].addShapeBox(-3F, 0F, 0F, 3, 2, 0, 0F,0F, 0F, 0F, 0F, -2F, 0.5F, 0F, -2F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F); // end brace3
		bodyModel[196].setRotationPoint(-32.52F, 1F, -11.01F);

		bodyModel[197].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,-0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, -0.75F, -0.25F, 0F, -0.75F, -0.25F, 0F, -0.75F, 0F, -0.5F, -0.75F, 0F); // thing (I seriously have no clue wtf this is)
		bodyModel[197].setRotationPoint(-37.52F, 1.75F, -11.26F);

		bodyModel[198].addBox(0F, 0F, 0F, 1, 17, 1, 0F); // Box 133
		bodyModel[198].setRotationPoint(35.51F, -13.99F, 10.01F);

		bodyModel[199].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, -1.75F, 0F, 0F, -0.75F, 0F, -0.25F, 0F, 0F, -0.25F, 1F, 0F, -0.25F, -1.75F, 0F, -0.25F, -0.75F); // Box 263
		bodyModel[199].setRotationPoint(29.5F, 2.25F, 10.75F);

		bodyModel[200].addShapeBox(0F, 0F, 0F, 6, 0, 1, 0F,0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 1F, 0F, 0F, -1F, 0F, 0F, 0F); // Box 263 cull
		bodyModel[200].setRotationPoint(29.5F, 3F, 11F);

		bodyModel[201].addShapeBox(0F, 0F, 0F, 0, 1, 1, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace1
		bodyModel[201].setRotationPoint(-36.51F, 1F, 9F);

		bodyModel[202].addShapeBox(0F, 0F, 0F, 0, 3, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace 2
		bodyModel[202].setRotationPoint(-36.52F, 2F, 9F);

		bodyModel[203].addShapeBox(0F, 0F, 0F, 0, 1, 1, 0F,0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace1
		bodyModel[203].setRotationPoint(36.52F, 1F, 9.01F);

		bodyModel[204].addShapeBox(0F, 0F, 0F, 0, 3, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace 2
		bodyModel[204].setRotationPoint(36.52F, 2F, 9.01F);

		bodyModel[205].addShapeBox(-3F, 0F, 0F, 3, 2, 0, 0F,0F, 0F, -0.1F, 0F, -2F, -0.5F, 0F, -2F, 0.5F, 0F, 0F, 0.1F, 0F, 0F, -0.1F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.1F); // end brace3
		bodyModel[205].setRotationPoint(-32.57F, 1F, 11.02F);

		bodyModel[206].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, -0.75F, 0F, -0.5F, -0.75F, 0F, -0.5F, -0.75F, -0.25F, 0F, -0.75F, -0.25F); // thing (I seriously have no clue wtf this is)
		bodyModel[206].setRotationPoint(36.52F, 1.75F, 10.26F);

		bodyModel[207].addShapeBox(-3F, 0F, 0F, 3, 2, 0, 0F,0F, -2F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.5F); // end brace3
		bodyModel[207].setRotationPoint(35.52F, 1F, 11.01F);

		bodyModel[208].addShapeBox(0F, 0F, 0F, 1, 2, 1, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, -0.25F, 0F, 0F, -0.25F, 0F, -0.75F, 0F, -0.5F, -0.75F, 0F, -0.5F, -0.75F, -0.25F, 0F, -0.75F, -0.25F); // thing (I seriously have no clue wtf this is)
		bodyModel[208].setRotationPoint(36.52F, 1.75F, -11F);

		bodyModel[209].addShapeBox(0F, 0F, 0F, 0, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace1
		bodyModel[209].setRotationPoint(36.51F, 1F, -10F);

		bodyModel[210].addShapeBox(0F, 0F, 0F, 0, 3, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // end brace 2
		bodyModel[210].setRotationPoint(36.52F, 2F, -11F);

		bodyModel[211].addShapeBox(-3F, 0F, 0F, 3, 2, 0, 0F,0F, -2F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F); // end brace3
		bodyModel[211].setRotationPoint(35.51F, 1F, -11.02F);

		bodyModel[212].addBox(-1F, 0F, 0F, 1, 1, 1, 0F); // Box 352
		bodyModel[212].setRotationPoint(-36.25F, 0F, 3F);

		bodyModel[213].addShapeBox(0F, 0F, 0F, 5, 1, 0, 0F,0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, -0.5F, -0.5F, 0F, -0.5F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 0
		bodyModel[213].setRotationPoint(-37.25F, 1.5F, 3.5F);

		bodyModel[214].addShapeBox(0F, -1F, -1F, 2, 2, 3, 0F,0.5F, 0.5F, -0.25F, -0.5F, 0.5F, -0.25F, -0.625F, 0.375F, 0F, 0.375F, 0.375F, 0F, 0.5F, -0.5F, -0.25F, -0.5F, -0.5F, -0.25F, -0.625F, -0.625F, 0F, 0.375F, -0.625F, 0F); // Box 286
		bodyModel[214].setRotationPoint(-30.9F, 2.25F, -6.85F);
		bodyModel[214].rotateAngleZ = 0.78539816F;

		bodyModel[215].addShapeBox(0F, -1F, -1F, 2, 2, 3, 0F,0.375F, 0.375F, 0F, -0.625F, 0.375F, 0F, -0.5F, 0.5F, -0.25F, 0.5F, 0.5F, -0.25F, 0.375F, -0.625F, 0F, -0.625F, -0.625F, 0F, -0.5F, -0.5F, -0.25F, 0.5F, -0.5F, -0.25F); // Box 286
		bodyModel[215].setRotationPoint(-30.9F, 2.25F, -9.35F);
		bodyModel[215].rotateAngleZ = 0.78539816F;

		bodyModel[216].addShapeBox(0F, -1F, -1F, 2, 1, 1, 0F,0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F); // Box 118
		bodyModel[216].setRotationPoint(-31.5F, 2.5F, 3.5F);
		bodyModel[216].rotateAngleX = -0.78539816F;

		bodyModel[217].addShapeBox(0F, -1F, -1F, 1, 1, 1, 0F,0.25F, -0.125F, -0.125F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0.25F, -0.125F, -0.125F, 0.25F, -0.125F, -0.125F, 0F, 0.25F, 0.25F, 0F, 0.25F, 0.25F, 0.25F, -0.125F, -0.125F); // Box 119
		bodyModel[217].setRotationPoint(-32.5F, 2.5F, 3.5F);
		bodyModel[217].rotateAngleX = -0.78539816F;

		bodyModel[218].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 491
		bodyModel[218].setRotationPoint(-33F, 0.25F, -2F);

		bodyModel[219].addShapeBox(0F, 0F, 0F, 2, 3, 2, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 93
		bodyModel[219].setRotationPoint(-33F, 0.5F, -1F);

		bodyModel[220].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 94
		bodyModel[220].setRotationPoint(-33F, 0.25F, 1F);

		bodyModel[221].addBox(0F, 0F, 0F, 1, 3, 0, 0F); // Box 80
		bodyModel[221].setRotationPoint(-34.55F, 2.01F, 0.52F);
		bodyModel[221].rotateAngleX = 1.6406095F;

		bodyModel[222].addBox(0F, 0F, 0F, 5, 1, 0, 0F); // Box 80
		bodyModel[222].setRotationPoint(-34.5F, 2F, -0.5F);
		bodyModel[222].rotateAngleX = 1.57079633F;

		bodyModel[223].addBox(0F, 0F, 0F, 2, 0, 11, 0F); // brake lines
		bodyModel[223].setRotationPoint(-34F, 1.5F, -9.5F);

		bodyModel[224].addShapeBox(0F, 0F, 0F, 59, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 23.2F, 0F, 0F, 23.2F, 0F, 0.03F, 0F, 0F, 0.03F, 0F, 0F, 0.03F, 23.2F, 0F, 0.03F, 23.2F); // rando cull shid (don't color the ends)
		bodyModel[224].setRotationPoint(-29.5F, -14.01F, -12.01F);

		bodyModel[225].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F); // Box 236 cull
		bodyModel[225].setRotationPoint(-36.5F, 5F, 10F);

		bodyModel[226].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 28
		bodyModel[226].setRotationPoint(27.5F, 4.75F, -1.5F);

		bodyModel[227].addShapeBox(0F, 0F, 0F, 3, 1, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F); // Box 28
		bodyModel[227].setRotationPoint(-30.5F, 4.75F, -1.5F);

		bodyModel[228].addBox(0F, 0F, 0F, 2, 0, 24, 0F); // Box 384
		bodyModel[228].setRotationPoint(28F, 3.01F, -12F);

		bodyModel[229].addBox(0F, 0F, 0F, 2, 0, 24, 0F); // Box 384
		bodyModel[229].setRotationPoint(-30F, 3.01F, -12F);

		bodyModel[230].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 0F, -0.01F, 0F, 0F, -0.01F, 0F); // Box 0
		bodyModel[230].setRotationPoint(-29.5F, 3F, -5F);

		bodyModel[231].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.01F, 0F, 0F, -0.01F, 0F, 0F, -2F, 0F, 0F, -2F, 0F); // Box 232
		bodyModel[231].setRotationPoint(-29.5F, 3F, 2F);

		bodyModel[232].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -2F, 0F, 0F, -2F, 0F, 0F, -0.01F, 0F, 0F, -0.01F, 0F); // Box 0
		bodyModel[232].setRotationPoint(28.5F, 3F, -5F);

		bodyModel[233].addShapeBox(0F, 0F, 0F, 1, 2, 3, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.01F, 0F, 0F, -0.01F, 0F, 0F, -2F, 0F, 0F, -2F, 0F); // Box 232
		bodyModel[233].setRotationPoint(28.5F, 3F, 2F);

		bodyModel[234].addBox(0F, 0F, 0F, 1, 1, 21, 0F); // Box 216
		bodyModel[234].setRotationPoint(35.5F, 2F, -10.5F);

		bodyModel[235].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.14F, 0F, 0F, -0.5F, 0F, 0F, 0.5F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, -2F, 0F, -0.14F, -2F, 0F, 0.14F, 0F, 0F, 0.14F); // Box 375
		bodyModel[235].setRotationPoint(-34.5F, 3F, 10F);

		bodyModel[236].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 0
		bodyModel[236].setRotationPoint(2.05F, 1F, -10.5F);

		bodyModel[237].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 214
		bodyModel[237].setRotationPoint(2.05F, 1F, 9.5F);

		bodyModel[238].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 0
		bodyModel[238].setRotationPoint(-11.95F, 1F, -10.5F);

		bodyModel[239].addShapeBox(0F, 0F, 0F, 10, 1, 1, 0F,0F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.2F, 0F, 0F, -0.4F, 0F, 0F, -0.4F, 0F, 0F, -0.2F, 0F, 0F); // Box 214
		bodyModel[239].setRotationPoint(-11.95F, 1F, 9.5F);

		bodyModel[240].addShapeBox(0F, 0F, 0F, 13, 10, 1, 0F,0F, -1F, 6F, 0.5F, -1F, 6F, 0.5F, 0F, -7F, 0F, 0F, -7F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F); // Box 259 cull
		bodyModel[240].setRotationPoint(-13.5F, -10F, -4F);

		bodyModel[241].addShapeBox(0F, 0F, 0F, 13, 10, 1, 0F,0F, 0F, -7F, 0.5F, 0F, -7F, 0.5F, -1F, 6F, 0F, -1F, 6F, 0F, 0F, 0F, 0.5F, 0F, 0F, 0.5F, 0F, 0F, 0F, 0F, 0F); // Box 259 cull
		bodyModel[241].setRotationPoint(-13.5F, -10F, 3F);

		bodyModel[242].addShapeBox(0F, 0F, 0F, 11, 1, 20, 0F,0F, 0F, 0F, -0.35F, -9.15F, 0F, -0.35F, -9.15F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.35F, 9.15F, 0F, -0.35F, 9.15F, 0F, 0F, 0F, 0F); // Box 0
		bodyModel[242].setRotationPoint(-35.5F, -8.15F, -10F);

		bodyModel[243].addShapeBox(0F, -1F, -1F, 3, 1, 6, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.45F, 0F, 0F, -0.45F, 0F, -1F, 0F, 0F, 0F, 0F, 0F, 0F, -0.45F, 0F, -1F, -0.45F); // Box 255 cull
		bodyModel[243].setRotationPoint(-32F, 2.5F, -9.37F);

		bodyModel[244].addShapeBox(0F, 0F, 0F, 0, 5, 5, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -1F, 0F, 0F, -1F, 0F, -1F, 0F, 0F, -1F, 0F, 0F, -1F, -1F, 0F, -1F, -1F); // Box 72
		bodyModel[244].setRotationPoint(-38.25F, -12.2F, 1.5F);

		bodyModel[245].addBox(0F, 0F, 0F, 1, 1, 1, 0F); // Box 87
		bodyModel[245].setRotationPoint(-38.25F, -10.7F, 3F);

		bodyModel[246].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F); // Box 236 cull
		bodyModel[246].setRotationPoint(-36.5F, 3F, -11F);

		bodyModel[247].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F); // Box 236 cull
		bodyModel[247].setRotationPoint(-36.5F, 5F, -11F);

		bodyModel[248].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0.14F, 0F, 0F, 0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, -2F, 0F, 0.14F, -2F, 0F, -0.14F, 0F, 0F, -0.14F); // Box 375
		bodyModel[248].setRotationPoint(-34.5F, 3F, -11F);

		bodyModel[249].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F); // Box 236 cull
		bodyModel[249].setRotationPoint(34.5F, 3F, -11F);

		bodyModel[250].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.14F); // Box 236 cull
		bodyModel[250].setRotationPoint(34.5F, 5F, -11F);

		bodyModel[251].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, 0.5F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, -0.5F, -2F, 0F, 0.14F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, -2F, 0F, -0.14F); // Box 375
		bodyModel[251].setRotationPoint(32.5F, 3F, -11F);

		bodyModel[252].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F); // Box 236 cull
		bodyModel[252].setRotationPoint(34.5F, 3F, 10F);

		bodyModel[253].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F, 0F, 0F, -0.14F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0.14F); // Box 236 cull
		bodyModel[253].setRotationPoint(34.5F, 5F, 10F);

		bodyModel[254].addShapeBox(0F, 0F, 0F, 2, 2, 1, 0F,0F, 0F, -0.5F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, 0F, 0F, 0.5F, -2F, 0F, -0.14F, 0F, 0F, -0.14F, 0F, 0F, 0.14F, -2F, 0F, 0.14F); // Box 375
		bodyModel[254].setRotationPoint(32.5F, 3F, 10F);

		bodyModel[255].addShapeBox(0F, 0F, 0F, 1, 17, 1, 0F,0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F, 0F, -0.5F, 0F); // Box 133
		bodyModel[255].setRotationPoint(-20F, -13.75F, -12F);

		bodyModel[256].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 8F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 8F); // Box 268 cull
		bodyModel[256].setRotationPoint(29.5F, 1.99F, -9.8F);

		bodyModel[257].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, 8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -8F, 0F, 0F, 8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -8F); // Box 268 cull
		bodyModel[257].setRotationPoint(29.5F, 1.99F, 8.75F);

		bodyModel[258].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, 8F, 0F, 0F, -8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 8F, 0F, 0F, -8F, 0F, 0F, 0F); // Box 268 cull
		bodyModel[258].setRotationPoint(-35.5F, 1.99F, 8.8F);

		bodyModel[259].addShapeBox(0F, 0F, 0F, 6, 1, 1, 0F,0F, 0F, 0F, 0F, 0F, -8F, 0F, 0F, 8F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, 0F, -8F, 0F, 0F, 8F, 0F, 0F, 0F); // Box 268 cull
		bodyModel[259].setRotationPoint(-35.5F, 1.99F, -9.75F);
	}
	Model70TonTruck2 bogie2 = new Model70TonTruck2();
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		ModelRenderHelper.renderModelWithStandardFreightRollingStock(bodyModel, entity, f5);

		if(((AbstractTrains) entity).getColor() == 2135 || ((AbstractTrains) entity).getColor() == 1623){
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/70Ton_Greyish.png"));
		} else {
			Tessellator.bindTexture(new ResourceLocation(Info.resourceLocation, "textures/trains/70Ton_Black.png"));
		}
		GL11.glPushMatrix();
		GL11.glTranslated(-1.78,-0.0,-0.0);
		bogie2.render(entity,f,f1,f2,f3,f4,f5);

		GL11.glTranslated(3.56,-0.0,0.00);
		bogie2.render(entity,f,f1,f2,f3,f4,f5);
		GL11.glPopMatrix();

		((AbstractTrains) entity).getCargoManager().renderCargo((AbstractTrains) entity, f, f1, f2, f3, f4, f5);
	}
}