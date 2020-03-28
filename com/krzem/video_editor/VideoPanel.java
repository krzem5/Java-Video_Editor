package com.krzem.video_editor;



import java.awt.Graphics2D;



public class VideoPanel extends Panel{
	public VideoPanel(Main cls,int x,int y,int w,int h){
		super(cls,x,y,w,h);
	}



	public void update(){
		super.update();
	}



	public void draw(Graphics2D g){
		g.setColor(FRAME.BORDER_COLOR);
		g.fillRect(this.x,this.y,this.w,this.h);
		g.setColor(FRAME.VIDEO_FRAME.BACKGROUND_COLOR);
		g.fillRect(this.x+FRAME.BORDER_SIZE,this.y+FRAME.BORDER_SIZE,this.w-FRAME.BORDER_SIZE*2,this.h-FRAME.BORDER_SIZE*2);
	}
}