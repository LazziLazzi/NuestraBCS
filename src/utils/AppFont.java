package utils;

import java.awt.Font;

public class AppFont {
	private static Font base;
	
	static {
		try {
			base = Font.createFont(Font.TRUETYPE_FONT, AppFont.class.getResourceAsStream("/fuentes/DancingScript.ttf"));
		} catch(Exception e) {
			base = new Font("Arial", Font.BOLD, 18);
		}
	}
	
	public static Font normal() {
		return base.deriveFont(18f);
	}
	
	public static Font small() {
		return base.deriveFont(12f);
	}
	
	public static Font title() {
		return base.deriveFont(60f);
	}
	
}
