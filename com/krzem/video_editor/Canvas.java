package com.krzem.video_editor;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Map;
import javax.swing.JComponent;



public class Canvas extends JComponent{
	public Main cls;
	private Map<?,?> rh;



	public Canvas(Main cls){
		this.cls=cls;
		this.rh=(Map<?,?>)Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints");
	}



	public void paintComponent(Graphics g){
		this.cls.draw(this.wrap(g));
	}



	public Graphics2D wrap(Graphics _g){
		Graphics2D g=(Graphics2D)_g;
		g.setRenderingHints(this.rh);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		return g;
	}



	public void addNotify(){
		super.addNotify();
		this.requestFocus();
	}
}