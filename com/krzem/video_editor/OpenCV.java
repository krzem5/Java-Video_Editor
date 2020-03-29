package com.krzem.video_editor;



import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.Exception;
import org.opencv.core.Core;



public class OpenCV{
	static{
		try{
			InputStream is=OpenCV.class.getResourceAsStream("/com/krzem/video_editor/modules/opencv_java420.dll");
			byte[] bf=new byte[1024];
			int t=-1;
			File t_f=File.createTempFile("opencv_java420.dll","");
			FileOutputStream os=new FileOutputStream(t_f);
			while ((t=is.read(bf))!=-1){
				os.write(bf,0,t);
			}
			os.close();
			is.close();
			System.load(t_f.getAbsolutePath());
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}