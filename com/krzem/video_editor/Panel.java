package com.krzem.video_editor;



import java.awt.Graphics2D;



public class Panel extends Constants{
	public Main cls;
	public int x;
	public int y;
	public int w;
	public int h;
	public int sw=-1;
	public int sh=-1;
	public int sx=0;
	public int sy=0;




	public Panel(Main cls,int x,int y,int w,int h){
		this.cls=cls;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}



	public void update(){
		if (this.sw==-1){
			this.sx=0;
		}
		else{
			////
		}
		if (this.sh==-1){
			this.sy=0;
		}
		else{
			////
		}
	}



	public void scroll(int w,int h){
		if (w>=this.w){
			this.sw=w+FRAME.SCROLL.BUFFOR;
		}
		else{
			this.sw=-1;
		}
		if (h>=this.h){
			this.sh=h+FRAME.SCROLL.BUFFOR;
		}
		else{
			this.sh=-1;
		}
	}



	public void draw(Graphics2D g){
		g.setColor(FRAME.BORDER_COLOR);
		g.fillRect(this.x,this.y,this.w,this.h);
		g.setColor(FRAME.BACKGROUND_COLOR);
		g.fillRect(this.x+FRAME.BORDER_SIZE,this.y+FRAME.BORDER_SIZE,this.w-FRAME.BORDER_SIZE*2,this.h-FRAME.BORDER_SIZE*2);
		g.translate(-this.sx,-this.sy);
	}



	public void draw_scroll(Graphics2D g){
		g.translate(this.sx,this.sy);
		g.setColor(FRAME.SCROLL.BG_COLOR);
		if (this.sw!=-1){
			g.fillRect(this.x+FRAME.BORDER_SIZE+FRAME.SCROLL.SIZE,this.y+this.h-FRAME.BORDER_SIZE-FRAME.SCROLL.SIZE,this.w-FRAME.BORDER_SIZE*2-FRAME.SCROLL.SIZE*2,FRAME.SCROLL.SIZE);
		}
		if (this.sh!=-1){
			g.fillRect(this.x+this.w-FRAME.BORDER_SIZE-FRAME.SCROLL.SIZE,this.y+FRAME.BORDER_SIZE+FRAME.SCROLL.SIZE,FRAME.SCROLL.SIZE,this.h-FRAME.BORDER_SIZE*2-FRAME.SCROLL.SIZE*2);
		}
	}
}