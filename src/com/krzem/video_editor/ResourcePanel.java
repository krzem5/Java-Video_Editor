package com.krzem.video_editor;



import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;



public class ResourcePanel extends Panel{
	public Map<String,Boolean> ts;
	public Map<String,List<String>> t;
	private boolean _md=false;




	public ResourcePanel(Main cls,int x,int y,int w,int h){
		super(cls,x,y,w,h);
		this.ts=new HashMap<String,Boolean>();
		this.t=new HashMap<String,List<String>>();
		this._load_effects_zip();
		this._load_resource(FRAME.RESOURCE_FRAME.RESOURCE_DIR);
	}



	public void update(){
		FontMetrics fm=this.cls.canvas.getFontMetrics(FRAME.RESOURCE_FRAME.TEXT_FONT);
		super.update();
		if (this.cls.MOUSE==1&&this.cls.MOUSE_BUTTON==1&&this.cls.MOUSE_COUNT==1){
			if (this._md==false){
				this._check_click(fm,"",this.x+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_X_OFFSET,this.y+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_Y_OFFSET);
			}
			this._md=true;
		}
		else if (this.cls.MOUSE==0){
			this._md=false;
		}
		int[] sz=this._calcluate_size(fm,"",this.x+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_X_OFFSET,this.y+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_Y_OFFSET,0,0);
		super.scroll(sz[0]-this.x-FRAME.BORDER_SIZE-FRAME.RESOURCE_FRAME.INITIAL_X_OFFSET,sz[1]-this.y-FRAME.BORDER_SIZE-FRAME.RESOURCE_FRAME.INITIAL_Y_OFFSET);
		super.update();
	}



	public void draw(Graphics2D g){
		super.draw(g);
		g.setColor(FRAME.RESOURCE_FRAME.TEXT_COLOR);
		g.setFont(FRAME.RESOURCE_FRAME.TEXT_FONT);
		FontMetrics fm=g.getFontMetrics();
		this._draw_folder(g,fm,"",this.x+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_X_OFFSET,this.y+FRAME.BORDER_SIZE+FRAME.RESOURCE_FRAME.INITIAL_Y_OFFSET);
		super.draw_scroll(g);
	}



	private int _check_click(FontMetrics fm,String k,int ox,int oy){
		if (k.length()>0){
			String nm=k.split("/")[k.split("/").length-1];
			int w=fm.stringWidth(nm);
			int h=fm.getHeight();
			if (this.t.get(k)!=null&&ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET-FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH<=this.cls.MOUSE_POS.x&&this.cls.MOUSE_POS.x<=ox+w&&oy-h/2<=this.cls.MOUSE_POS.y&&this.cls.MOUSE_POS.y<=oy+h/2){
				this.ts.put(k,!this.ts.get(k));
				return -1;
			}
			else if (this.t.get(k)==null&&ox<=this.cls.MOUSE_POS.x&&this.cls.MOUSE_POS.x<=ox+w&&oy-h/2<=this.cls.MOUSE_POS.y&&this.cls.MOUSE_POS.y<=oy+h/2){
				this._add(k);
				return -1;
			}
			oy+=h;
		}
		if (this.t.get(k)!=null&&(k.length()==0||this.ts.get(k)==true)){
			for (String sk:this.t.get(k)){
				oy=this._check_click(fm,sk,ox+(k.length()==0?0:FRAME.RESOURCE_FRAME.TEXT_X_OFFSET),oy+FRAME.RESOURCE_FRAME.TEXT_Y_OFFSET);
				if (oy==-1){
					return -1;
				}
			}
		}
		return oy;
	}



	private void _add(String fp){
		System.out.println(fp);
	}



	private int[] _calcluate_size(FontMetrics fm,String k,int ox,int oy,int mw,int mh){
		if (k.length()>0){
			String nm=k.split("/")[k.split("/").length-1];
			mw=Math.max(mw,ox+fm.stringWidth(nm));
			oy+=fm.getHeight();
		}
		if (this.t.get(k)!=null&&(k.length()==0||this.ts.get(k)==true)){
			for (String sk:this.t.get(k)){
				int[] dt=this._calcluate_size(fm,sk,ox+(k.length()==0?0:FRAME.RESOURCE_FRAME.TEXT_X_OFFSET),oy+FRAME.RESOURCE_FRAME.TEXT_Y_OFFSET,mw,mh);
				mw=Math.max(mw,dt[0]);
				mh=Math.max(mh,dt[1]);
				oy=dt[2];
			}
		}
		return new int[]{mw,Math.max(mh,oy),oy};
	}



	private int _draw_folder(Graphics2D g,FontMetrics fm,String k,int ox,int oy){
		if (k.length()>0){
			String nm=k.split("/")[k.split("/").length-1];
			int w=fm.stringWidth(nm);
			int h=fm.getHeight();
			g.drawString(nm,ox,oy+h/2-fm.getDescent());
			if (this.t.get(k)!=null){
				if (this.ts.get(k)==false){
					g.fillPolygon(new int[]{ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET,ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET-FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH,ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET-FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH},new int[]{oy,oy-FRAME.RESOURCE_FRAME.TRIANGLE_HEIGHT/2,oy+FRAME.RESOURCE_FRAME.TRIANGLE_HEIGHT/2},3);
				}
				else{
					g.fillPolygon(new int[]{ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET,ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET-FRAME.RESOURCE_FRAME.TRIANGLE_HEIGHT/2,ox-FRAME.RESOURCE_FRAME.TRIANGLE_OFFSET-FRAME.RESOURCE_FRAME.TRIANGLE_HEIGHT},new int[]{oy-FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH/2,oy+FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH/2,oy-FRAME.RESOURCE_FRAME.TRIANGLE_WIDTH/2},3);
				}
			}
			oy+=h;
		}
		if (this.t.get(k)!=null&&(k.length()==0||this.ts.get(k)==true)){
			for (String sk:this.t.get(k)){
				oy=this._draw_folder(g,fm,sk,ox+(k.length()==0?0:FRAME.RESOURCE_FRAME.TEXT_X_OFFSET),oy+FRAME.RESOURCE_FRAME.TEXT_Y_OFFSET);
			}
		}
		return oy;
	}



	private void _load_effects_zip(){
		try{
			ZipInputStream zf=new ZipInputStream(ResourcePanel.class.getProtectionDomain().getCodeSource().getLocation().openStream());
			while(true){
				ZipEntry e=zf.getNextEntry();
				if (e==null){
					break;
				}
				if (e.getName().startsWith(FRAME.RESOURCE_FRAME.EFFECTS_DIR)&&e.getName().split("\\.").length==2&&e.getName().split("\\.")[1].equals(FRAME.RESOURCE_FRAME.EFFECT_EXTENSION)){
					this._add_split_dir(FRAME.RESOURCE_FRAME.EFFECTS_DIR_PREFIX+e.getName().replace(FRAME.RESOURCE_FRAME.EFFECTS_DIR,"").split("\\.")[0]);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	private void _load_resource(String d){
		this._load_resource(d,d);
	}



	private void _load_resource(String d,String bp){
		for (File f:new File(d).listFiles()){
			if (f.isDirectory()){
				this._load_resource(f.getAbsolutePath(),bp);
			}
			else{
				if (f.getName().split("\\.").length==2&&FRAME.RESOURCE_FRAME.RESOURCE_EXTENSION_LIST.contains(f.getName().split("\\.")[1])){
					this._add_split_dir(FRAME.RESOURCE_FRAME.RESOURCE_DIR_PREFIX+f.getAbsolutePath().replace(bp,""));
				}
			}
		}
	}



	private void _add_split_dir(String sd){
		String l="";
		for (String s:sd.replace("\\","/").split("/")){
			if (s.length()==0){
				continue;
			}
			String ll=l+"";
			l+=(l.length()>0?"/":"")+s;
			if (!this.ts.containsKey(l)){
				if (this.t.get(ll)==null){
					this.t.put(ll,new ArrayList<String>());
				}
				this.t.get(ll).add(l);
				this.ts.put(l,false);
			}
		}
	}
}