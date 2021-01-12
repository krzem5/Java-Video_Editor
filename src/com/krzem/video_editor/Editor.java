package com.krzem.video_editor;



import java.awt.Graphics2D;



public class Editor extends Constants{
	public Main cls;
	public Panel e_p;
	public ResourcePanel r_p;
	public Panel t_p;
	public VideoPanel v_p;



	public Editor(Main cls){
		this.cls=cls;
		this.e_p=new Panel(this.cls,0,0,EFFECT_PANEL_WIDTH,WINDOW_SIZE.height-RESOURCE_PANEL_HEIGHT);
		this.r_p=new ResourcePanel(this.cls,0,WINDOW_SIZE.height-RESOURCE_PANEL_HEIGHT,RESOURCE_PANEL_WIDTH,RESOURCE_PANEL_HEIGHT);
		this.t_p=new Panel(this.cls,RESOURCE_PANEL_WIDTH,WINDOW_SIZE.height-TIMELINE_PANEL_HEIGHT,WINDOW_SIZE.width-RESOURCE_PANEL_WIDTH,TIMELINE_PANEL_HEIGHT);
		this.v_p=new VideoPanel(this.cls,EFFECT_PANEL_WIDTH,0,WINDOW_SIZE.width-EFFECT_PANEL_WIDTH,WINDOW_SIZE.height-TIMELINE_PANEL_HEIGHT);
		// https://docs.oracle.com/javase/8/docs/api/java/awt/Cursor.html
	}



	public void update(){
		this.e_p.update();
		this.r_p.update();
		this.t_p.update();
		this.v_p.update();
	}



	public void draw(Graphics2D g){
		g.setColor(BG_COLOR);
		g.fillRect(0,0,WINDOW_SIZE.width,WINDOW_SIZE.height);
		this.e_p.draw(g);
		this.r_p.draw(g);
		this.t_p.draw(g);
		this.v_p.draw(g);
	}
}