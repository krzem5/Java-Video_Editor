package com.krzem.video_editor;



import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;



public class Constants{
	public static final int DISPLAY_ID=0;
	public static final GraphicsDevice SCREEN=GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[DISPLAY_ID];
	public static final Rectangle WINDOW_SIZE=SCREEN.getDefaultConfiguration().getBounds();
	public static final int MAX_FPS=60;

	public static final Color BG_COLOR=Constants._hex_color("#ff0000");

	public static final class FRAME{
		public static final Color BACKGROUND_COLOR=Constants._hex_color("#282524");
		public static final Color BORDER_COLOR=Constants._hex_color("#423e3c");
		public static final int BORDER_SIZE=5;

		public static final class SCROLL{
			public static final int BUFFOR=30;
			public static final	int SIZE=20;

			public static final Color BG_COLOR=new Color(66,62,60,220);
			public static final Color FG_COLOR=Constants._hex_color("#2c2522");
		}

		public static final class RESOURCE_FRAME{
			public static final String RESOURCE_DIR="D:\\K\\Project\\project2\\OUT\\";
			public static final List<String> RESOURCE_EXTENSION_LIST=new ArrayList<String>(){{
				this.add("png");
				this.add("jpg");
				this.add("mov");
				this.add("mp4");
			}};
			public static final String RESOURCE_DIR_PREFIX="resources/";
			public static final String EFFECTS_DIR="com/krzem/video_editor/effects/";
			public static final String EFFECT_EXTENSION="class";
			public static final String EFFECTS_DIR_PREFIX="effects/";

			public static final Color TEXT_COLOR=Constants._hex_color("#e4e4e4");
			public static final Font TEXT_FONT=new Font("courier new",Font.PLAIN,30);
			public static final int TEXT_X_OFFSET=30;
			public static final int TEXT_Y_OFFSET=5;
			public static final int INITIAL_X_OFFSET=25;
			public static final int INITIAL_Y_OFFSET=15;

			public static final int TRIANGLE_OFFSET=5;
			public static final int TRIANGLE_WIDTH=8;
			public static final int TRIANGLE_HEIGHT=10;
		}

		public static final class VIDEO_FRAME{
			public static final Color BACKGROUND_COLOR=Constants._hex_color("#000000");
		}
	}

	public static final int EFFECT_PANEL_WIDTH=WINDOW_SIZE.width/4;
	public static final int TIMELINE_PANEL_HEIGHT=WINDOW_SIZE.height/4;
	public static final int RESOURCE_PANEL_WIDTH=WINDOW_SIZE.width/4;
	public static final int RESOURCE_PANEL_HEIGHT=WINDOW_SIZE.height/4;




	private static Color _hex_color(String h){
		return new Color(Integer.parseInt(h.substring(1,3),16),Integer.parseInt(h.substring(3,5),16),Integer.parseInt(h.substring(5,7),16));
	}
}