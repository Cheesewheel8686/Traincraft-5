package train.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

/**
 * Small reusable text button with Traincraft's dark shaded GUI style.
 *
 * Use NORMAL for routine actions and DANGER for destructive or reset-style actions that should be
 * visually separated from the normal workflow.
 */
public class GuiShadedButton extends GuiButton {
	private static final int WHITE = opaque(255, 255, 255);
	private static final int DISABLED_TEXT = opaque(160, 160, 160);
	private static final int NORMAL_BORDER = opaque(26, 26, 26);
	private static final int DISABLED_BORDER = opaque(48, 48, 48);
	private static final int NORMAL_TOP = opaque(119, 119, 119);
	private static final int NORMAL_BOTTOM = opaque(74, 74, 74);
	private static final int NORMAL_HOVER_TOP = opaque(154, 154, 154);
	private static final int NORMAL_HOVER_BOTTOM = opaque(103, 103, 103);
	private static final int DANGER_BORDER = opaque(42, 16, 16);
	private static final int DANGER_BACKGROUND = opaque(112, 48, 48);
	private static final int DANGER_HOVER_BACKGROUND = opaque(153, 64, 64);
	private static final int TOP_HIGHLIGHT = alphaRgb(102, 255, 255, 255);
	private static final int BOTTOM_SHADOW = alphaRgb(102, 0, 0, 0);

	public enum Style {
		NORMAL,
		DANGER,
		CUSTOM
	}

	private final Style style;
	private final int customBackground;
	private final int customHoverBackground;

	public GuiShadedButton(int id, int x, int y, int width, int height, String label) {
		this(id, x, y, width, height, label, Style.NORMAL);
	}

	public GuiShadedButton(int id, int x, int y, int width, int height, String label, Style style) {
		super(id, x, y, width, height, label);
		this.style = style;
		this.customBackground = NORMAL_BOTTOM;
		this.customHoverBackground = NORMAL_HOVER_BOTTOM;
	}

	public GuiShadedButton(int id, int x, int y, int width, int height, String label, int background, int hoverBackground) {
		super(id, x, y, width, height, label);
		this.style = Style.CUSTOM;
		this.customBackground = background;
		this.customHoverBackground = hoverBackground;
	}

	@Override
	public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
		if (!visible) {
			return;
		}

		field_146123_n = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
		if (style == Style.DANGER) {
			drawDangerButton(minecraft, field_146123_n);
		} else if (style == Style.CUSTOM) {
			drawCustomButton(minecraft, field_146123_n);
		} else {
			drawNormalButton(minecraft, field_146123_n);
		}
	}

	private void drawNormalButton(Minecraft minecraft, boolean hovered) {
		int top = hovered ? NORMAL_HOVER_TOP : NORMAL_TOP;
		int bottom = hovered ? NORMAL_HOVER_BOTTOM : NORMAL_BOTTOM;
		int border = enabled ? NORMAL_BORDER : DISABLED_BORDER;
		drawRect(xPosition, yPosition, xPosition + width, yPosition + height, border);
		drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + height / 2, top);
		drawRect(xPosition + 1, yPosition + height / 2, xPosition + width - 1, yPosition + height - 1, bottom);
		drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + 2, TOP_HIGHLIGHT);
		drawRect(xPosition + 1, yPosition + height - 2, xPosition + width - 1, yPosition + height - 1, BOTTOM_SHADOW);
		drawCenteredString(minecraft.fontRenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, getTextColor());
	}

	private void drawDangerButton(Minecraft minecraft, boolean hovered) {
		int background = hovered ? DANGER_HOVER_BACKGROUND : DANGER_BACKGROUND;
		drawRect(xPosition, yPosition, xPosition + width, yPosition + height, DANGER_BORDER);
		drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + height - 1, background);
		drawCenteredString(minecraft.fontRenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, getTextColor());
	}

	private void drawCustomButton(Minecraft minecraft, boolean hovered) {
		int background = hovered ? customHoverBackground : customBackground;
		drawRect(xPosition, yPosition, xPosition + width, yPosition + height, NORMAL_BORDER);
		drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + height - 1, enabled ? background : DISABLED_BORDER);
		drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + 2, TOP_HIGHLIGHT);
		drawRect(xPosition + 1, yPosition + height - 2, xPosition + width - 1, yPosition + height - 1, BOTTOM_SHADOW);
		drawCenteredString(minecraft.fontRenderer, displayString, xPosition + width / 2, yPosition + (height - 8) / 2, getTextColor());
	}

	private int getTextColor() {
		if (!enabled) {
			return DISABLED_TEXT;
		}
		return packedFGColour != 0 ? packedFGColour : WHITE;
	}

	private static int opaque(int red, int green, int blue) {
		return alphaRgb(255, red, green, blue);
	}

	private static int alphaRgb(int alpha, int red, int green, int blue) {
		return alpha << 24 | red << 16 | green << 8 | blue;
	}
}
