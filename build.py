import ntpath
import os
import subprocess
import sys
import zipfile



def make_dir(d):
	bp=os.getcwd()
	td=""
	for k in d.replace("\\","/").split("/"):
		if (k=="." or k==""):
			continue
		ot=td+""
		if (len(td)==0):
			td=k
		else:
			td+="/"+k
		if (not ntpath.exists(td)):
			if (ot==""):
				ot="./"
			os.system(f"cd {ot}&&mkdir {k}&&cd {bp}")



def error(cmd,dc=""):
	p=subprocess.run(cmd,stdout=subprocess.PIPE,stderr=subprocess.PIPE)
	if (len(p.stderr)!=0):
		print(str(p.stderr)[2:-1].replace("\\r\\n","\n").replace("\\t","\t").replace("\\'","'").replace("\\\\","\\"))
		os.system(dc)
		quit()



def jar(nm,mp,*dl):
	def _copy_jar(zf,jfp,wf):
		with zipfile.ZipFile(jfp,"r") as jf:
			for nm in jf.namelist():
				if (nm.upper()!="META-INF/MANIFEST.MF" and len(jf.read(nm))!=0 and nm not in wf):
					wf+=[nm]
					print(nm)
					zf.writestr(nm,jf.read(nm))
		return wf
	with zipfile.ZipFile(nm+".jar","w") as zf:
		wf=[]
		for d in dl:
			bp=(ntpath.abspath(d)+"\\" if len(d.split("|"))==1 else ntpath.abspath(d.split("|")[0])+"\\").replace("\\","/")
			if (len(d.split("|"))>1):
				d=d.split("|")[1]
			for r,_,fl in os.walk(d):
				for f in fl:
					if (f.endswith(".jar")):
						wf=_copy_jar(zf,ntpath.abspath(ntpath.join(r,f)),wf)
					elif (not ntpath.abspath(ntpath.join(r,f)).replace("\\","/").replace(bp,"") in wf):
						zf.write(ntpath.abspath(ntpath.join(r,f)),arcname=ntpath.abspath(ntpath.join(r,f)).replace("\\","/").replace(bp,""))
						wf+=[ntpath.abspath(ntpath.join(r,f)).replace("\\","/").replace(bp,"")]
						print(ntpath.abspath(ntpath.join(r,f)).replace("\\","/").replace(bp,""))
		zf.write(mp,arcname="META-INF/MANIFEST.MF")



os.system("rm -rf build&&mkdir build")
error(["javac","-cp","./build/;./com/krzem/video_editor/modules/opencv-420.jar;","-d","build","com/krzem/video_editor/*.java"])
for r,_,fl in os.walk("./com/krzem/video_editor/effects/"):
	for f in fl:
		make_dir(f"build/{r}")
		error(["javac","-cp","./build/;./com/krzem/video_editor/modules/opencv-420.jar;","-d",f"build/{r}",ntpath.join(r,f)])
with open("./_tmp_m.tmp","w") as f:
	f.write("Manifest-Version: 1.0\nCreated-By: Krzem\nMain-Class: com.krzem.video_editor.Main\n")
jar("video_editor","./_tmp_m.tmp","build",".|com/krzem/video_editor/modules")
os.system("del _tmp_m.tmp&&rm -rf build&&mkdir build&&copy video_editor.jar build&&del video_editor.jar&&cls")
os.system("cd build&&java -jar video_editor.jar")
os.system("cd ../")
